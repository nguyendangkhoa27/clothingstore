package com.clothingstore.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5831916831243974746L;

	public NotFoundException(String message) {
		super(message);
	}
}
