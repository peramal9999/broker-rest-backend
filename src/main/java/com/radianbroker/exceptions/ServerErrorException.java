package com.radianbroker.exceptions;

public class ServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 4141530106718147978L;

	public ServerErrorException() {
		super();
	}

	public ServerErrorException(final String message) {
		super(message);
	}
}
