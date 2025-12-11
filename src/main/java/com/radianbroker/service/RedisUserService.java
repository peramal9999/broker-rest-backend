package com.radianbroker.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.radianbroker.dto.RedisUser;

@Service
public interface RedisUserService {

	RedisUser findById(String id);

	Map<String, RedisUser> findAllUsers();

	void saveUser(RedisUser user);

	void updateUser(RedisUser user);

	void deleteById(String id);

	String getHaskKey(String email);
}
