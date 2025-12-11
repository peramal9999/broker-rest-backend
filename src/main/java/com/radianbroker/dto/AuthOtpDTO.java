package com.radianbroker.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuthOtpDTO implements Serializable {

	private static final long serialVersionUID = -6333785580662445047L;

	private String email;
	private String otp;
	private Timestamp createdAt;

	public AuthOtpDTO(String email, String otp, Timestamp createdAt) {
		super();
		this.email = email;
		this.otp = otp;
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
