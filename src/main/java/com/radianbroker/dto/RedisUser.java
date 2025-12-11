package com.radianbroker.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RedisUser implements Serializable {

	private static final long serialVersionUID = -6558620150190987488L;

	private String email;
	private String ipAddress;
	private String accessToken;
	private String refreshToken;
	private Date loginDate;
	private Date logoutDate;
	private Date activeAccessTokenCreatedDate;
	private List<String> blacklistedJwtTokens;

	public RedisUser(String email, String ipAddress, String accessToken, String refreshToken, Date loginDate,
			List<String> blacklistedJwtTokens) {
		super();
		this.email = email;
		this.ipAddress = ipAddress;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.loginDate = loginDate;
		this.blacklistedJwtTokens = blacklistedJwtTokens;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public Date getActiveAccessTokenCreatedDate() {
		return activeAccessTokenCreatedDate;
	}

	public void setActiveAccessTokenCreatedDate(Date activeAccessTokenCreatedDate) {
		this.activeAccessTokenCreatedDate = activeAccessTokenCreatedDate;
	}

	public List<String> getBlacklistedJwtTokens() {
		return blacklistedJwtTokens;
	}

	public void setBlacklistedJwtTokens(List<String> blacklistedJwtTokens) {
		this.blacklistedJwtTokens = blacklistedJwtTokens;
	}

}