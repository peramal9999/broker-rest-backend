package com.radianbroker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 4990404300174196685L;

	public CustomExceptionHandler() {
		super();
	}

	public CustomExceptionHandler(final String message) {
		super(message);
	}
}