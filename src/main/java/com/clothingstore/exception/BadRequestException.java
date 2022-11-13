package com.clothingstore.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object object;
	public BadRequestException(String message) {
		super(message);
	}
	public BadRequestException(String message,Object object) {
		super(message);
		this.object =object;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
}
