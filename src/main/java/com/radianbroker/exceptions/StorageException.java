package com.radianbroker.exceptions;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = -2362015585194801162L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
