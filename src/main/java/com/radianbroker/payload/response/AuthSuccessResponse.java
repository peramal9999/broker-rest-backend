package com.radianbroker.payload.response;

public class AuthSuccessResponse {

	private String accessToken;
	private String refreshToken;

	public AuthSuccessResponse(String accessToken, String refreshToken) {
		// TODO Auto-generated constructor stub
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
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

}
