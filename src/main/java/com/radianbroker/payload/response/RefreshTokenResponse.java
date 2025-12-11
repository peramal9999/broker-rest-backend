package com.radianbroker.payload.response;

public class RefreshTokenResponse {

	private SuccessFailure status;
	private String message;

	public enum SuccessFailure {
		SUCCESS, FAILURE
	}

	public RefreshTokenResponse(SuccessFailure status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public SuccessFailure getStatus() {
		return status;
	}

	public void setStatus(SuccessFailure status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
