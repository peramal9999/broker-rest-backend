package com.radianbroker.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1898373646387325736L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

}