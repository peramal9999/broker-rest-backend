package com.radianbroker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

	@Value("${auth.jwt.accessTokenCookieName}")
	private String accessTokenCookieName;

	@Value("${auth.jwt.refreshTokenCookieName}")
	private String refreshTokenCookieName;

	@Value("${auth.jwt.refreshTokenExpirationSec}")
	private int refreshTokenExpirationSec;

	@Value("${auth.jwt.tokenExpirationSec}")
	private int tokenExpirationSec;

	@Value("${auth.jwt.tokenSecret}")
	private String tokenSecret;

	@Value("${auth.jwt.tokenPrefix}")
	private String tokenPrefix;

	@Value("${auth.jwt.authHeader}")
	private String authHeader;
	
	public String getAccessTokenCookieName() {
		return accessTokenCookieName;
	}

	public void setAccessTokenCookieName(String accessTokenCookieName) {
		this.accessTokenCookieName = accessTokenCookieName;
	}

	public String getRefreshTokenCookieName() {
		return refreshTokenCookieName;
	}

	public void setRefreshTokenCookieName(String refreshTokenCookieName) {
		this.refreshTokenCookieName = refreshTokenCookieName;
	}

	public int getRefreshTokenExpirationSec() {
		return refreshTokenExpirationSec;
	}

	public void setRefreshTokenExpirationSec(int refreshTokenExpirationSec) {
		this.refreshTokenExpirationSec = refreshTokenExpirationSec;
	}

	public int getTokenExpirationSec() {
		return tokenExpirationSec;
	}

	public void setTokenExpirationSec(int tokenExpirationSec) {
		this.tokenExpirationSec = tokenExpirationSec;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	public String getTokenPrefix() {
		return tokenPrefix;
	}

	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

	public String getAuthHeader() {
		return authHeader;
	}

	public void setAuthHeader(String authHeader) {
		this.authHeader = authHeader;
	}

}