package com.radianbroker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import com.radianbroker.exceptions.CustomExceptionHandler;
import com.radianbroker.exceptions.ResourceNotFoundException;
import com.radianbroker.payload.request.ChangePasswordRequest;
import com.radianbroker.service.UserService;
import com.radianbroker.utils.CookieUtil;

@RestController
@RequestMapping("/api")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	private CookieUtil cookieUtil;

	@PreAuthorize("hasAnyRole('ROLE_RADIANADMIN','ROLE_MEMBER', 'ROLE_GROUPADMIN')")
	@GetMapping("/my-profile")
	public ResponseEntity<?> getUserProfile() {
		try {
			return new ResponseEntity<>(userService.getUserProfile(), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			logger.error("Error found: {}", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error found: {}", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/{user-id}/set-session-role")
	public ResponseEntity<?> setSessionRole(@PathVariable(value = "user-id", required = true) Long userId,
			@RequestParam(value = "sessionRole", required = true) String sessionRole) {
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			long maxAge = -1;
			httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createSessionRoleCookie(sessionRole, maxAge).toString());
			return ResponseEntity.ok().headers(httpHeaders).body(true);
		} catch (Exception e) {
			logger.error("Error found: {}", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/user/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) throws Exception {
		try {
			return new ResponseEntity<>(userService.changePassword(request.getEmail(), request.getCurrentPassword(),
					request.getNewPassword()), HttpStatus.OK);
		} catch (ServerErrorException ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(
					new CustomExceptionHandler("Provided new password should be different than current password.\n  "),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ResourceNotFoundException ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(new CustomExceptionHandler("Provided current password is incorrect.\n"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new CustomExceptionHandler(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
