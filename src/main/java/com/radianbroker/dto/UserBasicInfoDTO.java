package com.radianbroker.dto;

import java.util.List;
import java.util.Map;

import com.radianbroker.entity.Role;
import com.radianbroker.entity.User.TwoFactorAuthType;

public class UserBasicInfoDTO {

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private boolean emailHide;
	private String phoneNumber;
	private boolean phoneNumberHide;
	private Map<String, Object> groupInfo;
	private List<Role> roles;
	private boolean status;
	private TwoFactorAuthType twoFactorAuthType;
	private String sessionRole;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailHide() {
		return emailHide;
	}

	public void setEmailHide(boolean emailHide) {
		this.emailHide = emailHide;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isPhoneNumberHide() {
		return phoneNumberHide;
	}

	public void setPhoneNumberHide(boolean phoneNumberHide) {
		this.phoneNumberHide = phoneNumberHide;
	}

	public Map<String, Object> getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(Map<String, Object> groupInfo) {
		this.groupInfo = groupInfo;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public TwoFactorAuthType getTwoFactorAuthType() {
		return twoFactorAuthType;
	}

	public void setTwoFactorAuthType(TwoFactorAuthType twoFactorAuthType) {
		this.twoFactorAuthType = twoFactorAuthType;
	}

	public String getSessionRole() {
		return sessionRole;
	}

	public void setSessionRole(String sessionRole) {
		this.sessionRole = sessionRole;
	}

}
