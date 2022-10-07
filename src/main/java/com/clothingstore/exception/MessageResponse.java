package com.clothingstore.exception;

import org.springframework.http.HttpStatus;

public class MessageResponse<T> {
	private Integer code;
	private HttpStatus status;
	private String message;
	private T object;
	public MessageResponse() {}
	
	public MessageResponse(Integer code,HttpStatus status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}
	public MessageResponse(Integer code,HttpStatus status, String message,T object) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
		this.object = object;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
	
}
