package com.radianbroker.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import com.radianbroker.config.JwtConfig;

@Component
public class CookieUtil {
	
	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@Autowired
	JwtConfig jwtConfig;

    public HttpCookie createAccessTokenCookie(String token, Long duration) {
        String encryptedToken = SecurityCipher.encrypt(token);
        boolean isSecure = makeCookieSecure();
        return ResponseCookie.from(jwtConfig.getAccessTokenCookieName(), encryptedToken)
                .maxAge(duration)
                .httpOnly(true)
                .secure(isSecure)
                .path("/")
                .build();
    }

    public HttpCookie createRefreshTokenCookie(String token, Long duration) {
        String encryptedToken = SecurityCipher.encrypt(token);
        boolean isSecure = makeCookieSecure();
        return ResponseCookie.from(jwtConfig.getRefreshTokenCookieName(), encryptedToken)
                .maxAge(duration)
                .httpOnly(true)
                .secure(isSecure)
                .path("/")
                .build();
    }
    
	public HttpCookie createSessionRoleCookie(String sessionRole, Long duration) {
		 boolean isSecure = makeCookieSecure();
		return ResponseCookie.from("SESSIONROLE", sessionRole)
				 .maxAge(duration)
				 .httpOnly(true)
				 .secure(isSecure)
				 .path("/")
				 .build();
	}
    
    public boolean makeCookieSecure() {
    	if(activeProfile.equals("prod") || activeProfile.equals("proddr")) {
    		return true;
    	}
    	return false;
    }

}
