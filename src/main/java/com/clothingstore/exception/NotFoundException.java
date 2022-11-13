package com.clothingstore.exception;

import Message.message;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}
}
