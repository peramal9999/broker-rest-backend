package com.radianbroker.service;


public interface MailService {

	void send2faOtpEmail(Long userId, String email, String otp);

}