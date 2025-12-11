package com.radianbroker.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radianbroker.dto.UserBasicInfoDTO;
import com.radianbroker.dto.UserProfile;
import com.radianbroker.entity.Group;
import com.radianbroker.entity.Role;
import com.radianbroker.entity.User;
import com.radianbroker.entity.UserRole;
import com.radianbroker.enums.Roles;
import com.radianbroker.exceptions.ResourceNotFoundException;
import com.radianbroker.exceptions.ServerErrorException;
import com.radianbroker.repository.GroupRepository;
import com.radianbroker.repository.UserRepository;
import com.radianbroker.repository.UserRoleRepository;
import com.radianbroker.service.AppStorageService;
import com.radianbroker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserServiceImpl(@Lazy UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Value("${user.default-role}")
	private String defaultRoleString;

	UserDetailsServiceImpl userDetailsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AppStorageService appStorageService;

	@Override
	public UserBasicInfoDTO getUserBasicInfo(User user) {
		UserBasicInfoDTO userBasicInfoDTO = new UserBasicInfoDTO();
		userBasicInfoDTO.setUserId(user.getUserId());
		userBasicInfoDTO.setFirstName(user.getFirstName());
		userBasicInfoDTO.setLastName(user.getLastName());
		userBasicInfoDTO.setEmail(user.getEmail());
		userBasicInfoDTO.setEmailHide(user.isEmailHide());
		userBasicInfoDTO.setPhoneNumber(user.getPhoneNumber());
		userBasicInfoDTO.setPhoneNumberHide(user.isPhoneNumberHide());
		userBasicInfoDTO.setStatus(user.isStatus());
		userBasicInfoDTO.setTwoFactorAuthType(user.getTwoFactorAuthType());

		if (user.getGroupIdForAdmin() != null) {
			Optional<Group> group = groupRepository.findById(user.getGroupIdForAdmin());
			if (group.isPresent() && group.get() != null) {
				Map<String, Object> groupInfo = new HashMap<String, Object>();
				groupInfo.put("groupId", group.get().getGroupId());
				groupInfo.put("groupName", group.get().getName());
				userBasicInfoDTO.setGroupInfo(groupInfo);
			}
		}
		Set<String> sessionRoles = Roles.getSessionRoles();
		
		List<UserRole> userRoleList = userRoleRepository.findAllByUserId(user.getUserId());
		List<Role> userRoles = new ArrayList<Role>();
		if (userRoleList != null && !userRoleList.isEmpty()) {
			for (UserRole userRole : userRoleList) {
				if(sessionRoles.contains(userRole.getRole().getName())) {
					Role role = new Role();
					role.setRoleId(userRole.getRole().getRoleId());
					role.setName(userRole.getRole().getName());
					userRoles.add(role);	
				}
			}
		}
		userBasicInfoDTO.setRoles(userRoles);
		return userBasicInfoDTO;
	}

	@Override
	public UserProfile getUserProfile() {
		// TODO Auto-generated method stub
		Long userId = userDetailsService.getCurrentAuditor().getId();
		// TODO Auto-generated method stub
		User userFromDb = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for userId:" + userId));
		Set<String> sessionRoles = Roles.getSessionRoles();
		List<UserRole> userRolesList = userRoleRepository.findAllByUserId(userFromDb.getUserId());
		List<Role> roles = new ArrayList<Role>();
		for (UserRole userRole : userRolesList) {
			if(sessionRoles.contains(userRole.getRole().getName())) {
				Role role = new Role();
				role.setRoleId(userRole.getRole().getRoleId());
				role.setName(userRole.getRole().getName());
				role.setStatus(userRole.getRole().isStatus());
				roles.add(role);
			}
		}
		ModelMapper modelMapper = new ModelMapper();
		UserProfile userProfile = modelMapper.map(userFromDb, UserProfile.class);
		userProfile.setPasswordHash(null);
		userProfile.setRoles(roles);
		if (userProfile.getPhotoFilePath() != null && !userProfile.getPhotoFilePath().isEmpty()) {
			userProfile.setPhotoUrl(appStorageService.getHttpUrl(userProfile.getPhotoFilePath()));
		}
		return userProfile;
	}

	@Override
	public boolean changePassword(String email, String currentPassword, String newPassword) throws Exception {
		User user = userRepository.findByEmail(email);

		String encodedPasswordFromDb = user.getPasswordHash();
		String decodedPass = new String(Base64.getDecoder().decode(currentPassword));
		String decodedCurrentPassword = new String(decodedPass);
		boolean isPasswordMatched = passwordEncoder.matches(decodedCurrentPassword, encodedPasswordFromDb);
		if (isPasswordMatched) {
			String newPass = new String(Base64.getDecoder().decode(newPassword));
			String decodedNewPassword = new String((newPass));
			if (!(decodedNewPassword.equalsIgnoreCase(decodedCurrentPassword))) {
				user.setPasswordHash(passwordEncoder.encode(decodedNewPassword));
				user = userRepository.save(user);
				if (user != null) {
					return true;
				} else {
					throw new ServerErrorException("Error occurred while changing password!");
				}
			} else {
				throw new ServerErrorException("Provided new password should be different than current password.");
			}
		} else {
			throw new ResourceNotFoundException("Provided current password is incorrect.");
		}
	}

}
