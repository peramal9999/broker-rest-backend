package com.radianbroker.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.radianbroker.dto.AuthOtpDTO;
import com.radianbroker.dto.RedisUser;
import com.radianbroker.dto.Token;
import com.radianbroker.dto.UserBasicInfoDTO;
import com.radianbroker.entity.Role;
import com.radianbroker.entity.User;
import com.radianbroker.entity.User.TwoFactorAuthType;
import com.radianbroker.exceptions.BadRequestException;
import com.radianbroker.exceptions.ResourceNotFoundException;
import com.radianbroker.repository.RoleRepository;
import com.radianbroker.repository.UserRepository;
import com.radianbroker.security.UserDetailsImpl;
import com.radianbroker.service.MailService;
import com.radianbroker.service.RedisAuthOtpService;
import com.radianbroker.service.RedisUserService;
import com.radianbroker.service.TotpManager;
import com.radianbroker.service.UserService;
import com.radianbroker.utils.JwtUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	RedisUserService redisUserService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	TotpManager totpManager;

	@Autowired
	MailService mailService;

	@Autowired
	RedisAuthOtpService redisAuthOtpService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${app.server.2faOtpExpirationSec}")
	private long OTP_VALID_DURATION;
	
	@Value("${radianapp.session.jwttoken.secret}")
	private String SESSION_JWT_SECRET;

	@Autowired
	UserDetailsServiceImpl(@Lazy AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndStatus(username, true);
		if (user != null) {
			if (user.isStatus()) {
				List<Role> roles = roleRepository.findByUserId(user.getUserId());
				return UserDetailsImpl.build(user, roles);
			} else {
				if (!user.isStatus()) {
					throw new BadRequestException("Your account has been disabled!");
				} else {
					throw new BadRequestException(
							"Your account has been locked due to 3 failed attempts. Please contact your Administrator!");
				}
			}
		} else {
			throw new UsernameNotFoundException("User not found with email: " + username);
		}
	}

	public UserDetailsImpl getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			return userDetails;
		} else {
			throw new BadRequestException("Unauthorized user");
		}
	}

	public UserBasicInfoDTO loginUser(String email, String password) {
		// TODO Auto-generated method stub
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with email: " + email);
		}
		if (user.getTwoFactorAuthType().equals(TwoFactorAuthType.EMAIL)) {
			// send otp via email
			AuthOtpDTO authOtpDTO = redisAuthOtpService.findById(email);
			String otp = generateOtp();
			if (authOtpDTO == null) {
				authOtpDTO = new AuthOtpDTO(email, otp, new Timestamp(System.currentTimeMillis()));
				redisAuthOtpService.saveAuthOtp(authOtpDTO);
			} else {
				authOtpDTO.setOtp(otp);
				authOtpDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				redisAuthOtpService.updateAuthOtp(authOtpDTO);
			}

			mailService.send2faOtpEmail(user.getUserId(), email, otp);
		}
		return userService.getUserBasicInfo(user);
	}

	public boolean validateUserPassword(Long userId, String password) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Error: User is not found for id: " + userId));

		String encodedPasswordFromDb = user.getPasswordHash();
		String decodedPass = new String(Base64.getDecoder().decode(password));
		String decodedPassword = new String(decodedPass);
		boolean isPasswordMatched = passwordEncoder.matches(decodedPassword, encodedPasswordFromDb);
		if(!isPasswordMatched) {
			throw new BadRequestException("Password is incorrect!");
		}
		return isPasswordMatched;
	}
	
	public String generateOtp() {
		Supplier<String> otpSupplier = () -> {
			StringBuilder otp = new StringBuilder();
			Random random = new Random();
			for (int i = 0; i < 6; i++) {
				int randomNumber = random.nextInt(9);
				otp.append(randomNumber);
			}
			return otp.toString();
		};

		return otpSupplier.get();
	}

	public UserBasicInfoDTO verify(String email, String code) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with email: " + email);
		}
		boolean validOtp = false;
		if (user.getTwoFactorAuthType().equals(TwoFactorAuthType.AUTHENTICATORAPP)) {
			validOtp = totpManager.verifyCode(code, user.getSecret());
		} else {
			AuthOtpDTO authOtpDTO = redisAuthOtpService.findById(email);
			if (authOtpDTO != null) {
				if (code.equals(authOtpDTO.getOtp())) {
					long currentTimeInMillis = System.currentTimeMillis();
					long otpRequestedTimeInMillis = authOtpDTO.getCreatedAt().getTime();

					if (otpRequestedTimeInMillis + (OTP_VALID_DURATION * 1000) < currentTimeInMillis) {
						validOtp = false;
						redisAuthOtpService.deleteById(email);
						throw new BadRequestException("Sorry OTP is expired, regenerate new");
					}
					validOtp = true;
					redisAuthOtpService.deleteById(email);
				}
			} else {
				throw new BadRequestException("AuthOtp not found in db");
			}

		}
		if (!validOtp) {
			throw new BadRequestException("Code is incorrect");
		}
		return userService.getUserBasicInfo(user);
	}

	public RedisUser createRedisUser(String email, String accessToken, String refreshToken, String userRemoteAddr) {
		String hashKey = redisUserService.getHaskKey(email);
		RedisUser redisUser = redisUserService.findById(hashKey);
		if (redisUser != null && redisUser.getAccessToken() != null) {
			List<String> blacklistedJwtTokens = redisUser.getBlacklistedJwtTokens();
			if (blacklistedJwtTokens != null && blacklistedJwtTokens.size() > 0) {
				blacklistedJwtTokens = this.getNonExpiredBlacklistedJwtTokens(blacklistedJwtTokens);
			}
			boolean accessTokenValid = jwtUtils.validateToken(redisUser.getAccessToken());
			if (accessTokenValid && !blacklistedJwtTokens.contains(redisUser.getAccessToken())) {
				blacklistedJwtTokens.add(redisUser.getAccessToken());
			}
			boolean refreshTokenValid = jwtUtils.validateToken(redisUser.getAccessToken());
			if (refreshTokenValid && !blacklistedJwtTokens.contains(redisUser.getRefreshToken())) {
				blacklistedJwtTokens.add(redisUser.getRefreshToken());
			}
			redisUser.setIpAddress(userRemoteAddr);
			redisUser.setAccessToken(accessToken);
			redisUser.setRefreshToken(refreshToken);
			redisUser.setLoginDate(new Date());
			redisUser.setLogoutDate(null);
			redisUser.setActiveAccessTokenCreatedDate(null);
			redisUser.setBlacklistedJwtTokens(blacklistedJwtTokens);
			redisUserService.updateUser(redisUser);
			return redisUser;
		} else {
			List<String> blacklistedJwtTokens = new ArrayList<>();
			RedisUser user = new RedisUser(email, userRemoteAddr, accessToken, refreshToken, new Date(),
					blacklistedJwtTokens);
			redisUserService.saveUser(user);
			return user;
		}
	}

	public boolean isBlacklistedJwtToken(String email, String accessToken) {
		String hashKey = redisUserService.getHaskKey(email);
		RedisUser user = redisUserService.findById(hashKey);
		if (user != null) {
			List<String> blacklistedJwtTokens = user.getBlacklistedJwtTokens();
			return blacklistedJwtTokens.contains(accessToken);
		}
		return true;
	}

	public List<String> getNonExpiredBlacklistedJwtTokens(List<String> blacklistedJwtTokens) {
		List<String> nonExpiredBlacklistedJwtTokens = new ArrayList<>();
		for (int index = 0; index < blacklistedJwtTokens.size(); index++) {
			String blacklistedJwtToken = blacklistedJwtTokens.get(index);
			boolean accessTokenValid = jwtUtils.validateToken(blacklistedJwtToken);
			if (accessTokenValid) {
				nonExpiredBlacklistedJwtTokens.add(blacklistedJwtToken);
			}
		}
		return nonExpiredBlacklistedJwtTokens;
	}

	public Token refreshToken(String accessToken, String refreshToken) throws Exception {

		Boolean refreshTokenValid = jwtUtils.validateToken(refreshToken);
		if (!refreshTokenValid) {
			throw new IllegalArgumentException("Refresh Token is invalid!");
		}

		String currentEmail = jwtUtils.getEmailFromToken(refreshToken);
		String hashKey = redisUserService.getHaskKey(currentEmail);
		RedisUser redisUser = redisUserService.findById(hashKey);
		if (redisUser.getRefreshToken().equals(refreshToken)) {
			Token newAccessToken = jwtUtils.generateAccessToken(currentEmail);

			List<String> blacklistedJwtTokens = redisUser.getBlacklistedJwtTokens();
			if (blacklistedJwtTokens != null && blacklistedJwtTokens.size() > 0) {
				blacklistedJwtTokens = this.getNonExpiredBlacklistedJwtTokens(blacklistedJwtTokens);
			}
			boolean accessTokenValid = jwtUtils.validateToken(accessToken);
			if (accessTokenValid && !blacklistedJwtTokens.contains(accessToken)) {
				blacklistedJwtTokens.add(accessToken);
			}

			redisUser.setAccessToken(newAccessToken.getTokenValue());
			redisUser.setRefreshToken(refreshToken);
			redisUser.setBlacklistedJwtTokens(blacklistedJwtTokens);
			redisUser.setActiveAccessTokenCreatedDate(new Date());
			redisUserService.updateUser(redisUser);
			return newAccessToken;
		} else {
			throw new Exception("Unauthorized- RefreshToken not matched");
		}
	}

	public void signOut(String accessToken, String refreshToken) {
		// TODO Auto-generated method stub
		String currentUsername = null;
		try {
			currentUsername = jwtUtils.getEmailFromToken(accessToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (currentUsername == null) {
			return;
		}
		String hashKey = redisUserService.getHaskKey(currentUsername);
		RedisUser redisUser = redisUserService.findById(hashKey);
		if (redisUser != null && redisUser.getAccessToken() != null) {
			List<String> blacklistedJwtTokens = redisUser.getBlacklistedJwtTokens();
			if (blacklistedJwtTokens != null && blacklistedJwtTokens.size() > 0) {
				blacklistedJwtTokens = this.getNonExpiredBlacklistedJwtTokens(blacklistedJwtTokens);
			}
			boolean accessTokenValid = jwtUtils.validateToken(accessToken);
			if (accessTokenValid && !blacklistedJwtTokens.contains(accessToken)) {
				blacklistedJwtTokens.add(accessToken);
			}
			boolean refreshTokenValid = jwtUtils.validateToken(refreshToken);
			if (refreshTokenValid && !blacklistedJwtTokens.contains(refreshToken)) {
				blacklistedJwtTokens.add(refreshToken);
			}
			if (blacklistedJwtTokens.size() > 0) {
				redisUser.setLogoutDate(new Date());
				redisUser.setBlacklistedJwtTokens(blacklistedJwtTokens);
				redisUserService.updateUser(redisUser);
			} else {
				redisUserService.deleteById(hashKey);
			}
		}
	}

	public boolean send2faOtpEmail(Long userId, String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with email: " + email);
		}
		if (user.getTwoFactorAuthType().equals(TwoFactorAuthType.EMAIL)) {
			// send otp via email
			AuthOtpDTO authOtpDTO = redisAuthOtpService.findById(email);
			String otp = generateOtp();
			if (authOtpDTO == null) {
				authOtpDTO = new AuthOtpDTO(email, otp, new Timestamp(System.currentTimeMillis()));
				redisAuthOtpService.saveAuthOtp(authOtpDTO);
			} else {
				authOtpDTO.setOtp(otp);
				authOtpDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				redisAuthOtpService.updateAuthOtp(authOtpDTO);
			}
			
			mailService.send2faOtpEmail(user.getUserId(), email, otp);
		}

		return true;
	}

	public UserDetails authenticateLaunchpadUser(String session) {
		// TODO Auto-generated method stub
		if (StringUtils.hasText(session) && jwtUtils.validateToken(session, SESSION_JWT_SECRET)) {
			String email = jwtUtils.getEmailFromToken(session, SESSION_JWT_SECRET);
			return loadUserByUsername(email);
		} else {
			throw new BadRequestException("Invalid session");
		}
	}

}
