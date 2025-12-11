package com.radianbroker.service.impl;

import org.springframework.stereotype.Service;

import com.radianbroker.dto.RedisUser;
import com.radianbroker.service.RedisUserService;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Service
public class RedisUserServiceImpl implements RedisUserService {

	private final String USERS_CACHE = "USERS";

	private HashOperations<String, String, RedisUser> hashOperations;
	
	public RedisUserServiceImpl(RedisTemplate<String, Object> redisTemplate) {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public RedisUser findById(String id) {
		return (RedisUser) hashOperations.get(USERS_CACHE, id);
	}

	@Override
	public Map<String, RedisUser> findAllUsers() {
		return hashOperations.entries(USERS_CACHE);
	}

	@Override
	public void saveUser(RedisUser user) {
		String hashKey = getHaskKey(user.getEmail());
		hashOperations.put(USERS_CACHE, hashKey, user);
	}

	@Override
	public void updateUser(RedisUser user) {
		String hashKey = getHaskKey(user.getEmail());
		hashOperations.put(USERS_CACHE, hashKey, user);
	}

	@Override
	public void deleteById(String id) {
		hashOperations.delete(USERS_CACHE, id);
	}

	@Override
	public String getHaskKey(String email) {
		String hashKey = email;
		return hashKey;
	}
}
