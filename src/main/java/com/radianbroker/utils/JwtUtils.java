package com.radianbroker.utils;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radianbroker.config.JwtConfig;
import com.radianbroker.dto.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

	@Autowired
	JwtConfig jwtConfig;

    public Token generateAccessToken(String subject) {
        Date now = new Date();
        Long duration = now.getTime() + (jwtConfig.getTokenExpirationSec() * 1000);
        Date expiryDate = new Date(duration);
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getTokenSecret())
                .compact();
        return new Token(Token.TokenType.ACCESS, token, duration, LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));
    }
	
    public Token generateRefreshToken(String subject) {
        Date now = new Date();
        Long duration = now.getTime() + (jwtConfig.getRefreshTokenExpirationSec() * 1000);
        Date expiryDate = new Date(duration);
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getTokenSecret())
                .compact();
        return new Token(Token.TokenType.REFRESH, token, duration, LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));
    }

    public Token sessionToken(String subject, String radianApp) {
        Date now = new Date();
        Long duration = now.getTime() + (30 * 1000);
        Date expiryDate = new Date(duration);
        
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getTokenSecret())
                .compact();
        return new Token(Token.TokenType.REFRESH, token, duration, LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));
    }
    
	public String getEmailFromToken(String token) {
		 Claims claims = Jwts.parser().setSigningKey(jwtConfig.getTokenSecret()).parseClaimsJws(token).getBody();
	     return claims.getSubject();
	}
	
	public String getEmailFromToken(String token, String secret) {
		 Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	     return claims.getSubject();
	}
	
	public LocalDateTime getExpiryDateFromToken(String token) {
	        Claims claims = Jwts.parser().setSigningKey(jwtConfig.getTokenSecret()).parseClaimsJws(token).getBody();
	        return LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
	}

	public boolean validateToken(String token) {
		return validateToken(token, jwtConfig.getTokenSecret());
	}
	
	public boolean validateToken(String token, String secret) {
        try {
            Jwts.parser().setSigningKey(secret).parse(token);
            return true;
        } catch (SignatureException ex) {
        	System.out.print(ex.getLocalizedMessage());
        } catch (MalformedJwtException ex) {
        	System.out.print(ex.getLocalizedMessage());
        } catch (ExpiredJwtException ex) {
        	System.out.print(ex.getLocalizedMessage());
        } catch (UnsupportedJwtException ex) {
        	System.out.print(ex.getLocalizedMessage());
        } catch (IllegalArgumentException ex) {
        	System.out.print(ex.getLocalizedMessage());
        }
        return false;
    }

    public Claims getClaimsFromJWT(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getTokenSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
