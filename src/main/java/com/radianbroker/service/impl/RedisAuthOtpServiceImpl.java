package com.radianbroker.service.impl;

import java.util.Map;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.radianbroker.dto.AuthOtpDTO;
import com.radianbroker.service.RedisAuthOtpService;
@Service
public class RedisAuthOtpServiceImpl implements RedisAuthOtpService {

	private final String AUTH_OTP_CACHE = "AUTH_OTP";

	private HashOperations<String, String, AuthOtpDTO> hashOperations;

	public RedisAuthOtpServiceImpl(RedisTemplate<String, Object> redisTemplate) {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public AuthOtpDTO findById(String id) {
		return (AuthOtpDTO) hashOperations.get(AUTH_OTP_CACHE, id);
	}

	@Override
	public Map<String, AuthOtpDTO> findAllAuthOtp() {
		return hashOperations.entries(AUTH_OTP_CACHE);
	}

	@Override
	public void saveAuthOtp(AuthOtpDTO authOtp) {
		String hashKey = getHashKey(authOtp.getEmail());
		hashOperations.put(AUTH_OTP_CACHE, hashKey, authOtp);
	}

	@Override
	public void updateAuthOtp(AuthOtpDTO authOtp) {
		String hashKey = getHashKey(authOtp.getEmail());
		hashOperations.put(AUTH_OTP_CACHE, hashKey, authOtp);
	}

	@Override
	public void deleteById(String id) {
		hashOperations.delete(AUTH_OTP_CACHE, id);
	}

	@Override
	public String getHashKey(String email) {
		String hashKey = email;
		return hashKey;
	}
}
