package com.radianbroker.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.radianbroker.dto.AuthOtpDTO;

@Service
public interface RedisAuthOtpService {
	AuthOtpDTO findById(String id);

	Map<String, AuthOtpDTO> findAllAuthOtp();

	void saveAuthOtp(AuthOtpDTO authOtp);

	void updateAuthOtp(AuthOtpDTO authOtp);

	void deleteById(String id);

	String getHashKey(String email);
}
