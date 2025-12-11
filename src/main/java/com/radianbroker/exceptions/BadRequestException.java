package com.radianbroker.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -8990316234061167515L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(final String message) {
		super(message);
	}
}
