package com.radianbroker.service;

import com.radianbroker.dto.UserBasicInfoDTO;
import com.radianbroker.dto.UserProfile;
import com.radianbroker.entity.User;

public interface UserService {

	UserProfile getUserProfile();

	UserBasicInfoDTO getUserBasicInfo(User user);

	public boolean changePassword(String email, String currentPassword, String newPassword) throws Exception;
}
