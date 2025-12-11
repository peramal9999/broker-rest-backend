package com.radianbroker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.radianbroker.config.JwtConfig;
import com.radianbroker.service.impl.UserDetailsServiceImpl;
import com.radianbroker.utils.JwtUtils;
import com.radianbroker.utils.SecurityCipher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

	 @Value("${auth.jwt.accessTokenCookieName}")
	    private String accessTokenCookieName;

	    @Value("${auth.jwt.refreshTokenCookieName}")
	    private String refreshTokenCookieName;
	    
		@Autowired
	    JwtConfig jwtConfig;
		
		@Autowired
	    private JwtUtils jwtUtils;

	    @Autowired
	    private UserDetailsServiceImpl userDetailsService;


	    @Override
	    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse HttpServletResponse, FilterChain filterChain)
	            throws ServletException, IOException {

	    	try {
	            String token = getJwtToken(httpServletRequest, true);
	            if (StringUtils.hasText(token) && jwtUtils.validateToken(token)) {
	            	String email = jwtUtils.getEmailFromToken(token);
	                boolean isBlacklistedJwtToken = userDetailsService.isBlacklistedJwtToken(email, token);
	    			if (!isBlacklistedJwtToken) {
	    				
	    				 UserDetails userDetails = userDetailsService.loadUserByUsername(email);
	    	             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	    	             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
	    	             SecurityContextHolder.getContext().setAuthentication(authentication);
	    			}
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
//	        // 1. get the authentication header. Tokens are supposed to be passed in the authentication header
//	        String header = httpServletRequest.getHeader(jwtConfig.getAuthHeader());
	//
//	        // 2. validate the header and check the prefix
//	        if(header == null || !header.startsWith(jwtConfig.getTokenPrefix())) {
//	        	filterChain.doFilter(httpServletRequest, HttpServletResponse);  		// If not valid, go to the next filter.
//	            return;
//	        }
	//
//	        // If there is no token provided and hence the user won't be authenticated.
//	        // It's Ok. Maybe the user accessing a public path or asking for a token.
	//
//	        // All secured paths that needs a token are already defined and secured in config class.
//	        // And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.
	//
//	        // 3. Get the token
//	        String token = header.replace(jwtConfig.getTokenPrefix(), "");
	//
//	        if(jwtUtils.validateToken(token)) {
	//
//	            String username = jwtUtils.getEmailFromToken(token);
	//
//	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//	            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//	            SecurityContextHolder.getContext().setAuthentication(authentication);
	//
	//
//	        } else {
//	            SecurityContextHolder.clearContext();
//	        }

	        // go to the next filter in the filter chain
	        filterChain.doFilter(httpServletRequest, HttpServletResponse);
	    }

	    
		private String getJwtToken(HttpServletRequest request, boolean fromCookie) {
	        if (fromCookie) return getJwtFromCookie(request);

	        return getJwtFromRequest(request);
	    }

		private String getJwtFromCookie(HttpServletRequest request) {
	        Cookie[] cookies = request.getCookies();
	        if(cookies == null) {
	        	return null;
	        }
	        for (Cookie cookie : cookies) {
	            if (accessTokenCookieName.equals(cookie.getName())) {
	                String accessToken = cookie.getValue();
	                if (accessToken == null) return null;

	                return SecurityCipher.decrypt(accessToken);
	            }
	        }
	        return null;
	    }

		private String getJwtFromRequest(HttpServletRequest request) {
	        String bearerToken = request.getHeader("Authorization");
	        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
	            String accessToken = bearerToken.substring(7);
	            if (accessToken == null) return null;
	           return SecurityCipher.decrypt(accessToken);
	        }
	        return null;
	    }
}