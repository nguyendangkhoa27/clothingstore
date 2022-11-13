package com.clothingstore.exception;

import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;

public class CustomConstainViolationException extends ConstraintViolationException {

	private static final long serialVersionUID = 1L;
	private String messageCustom;
	public CustomConstainViolationException(String message, SQLException root, String sql, String constraintName) {
		super(message, root, sql, constraintName);
	}
	public String getMessageCustom() {
		return messageCustom;
	}
	public void setMessageCustom(String messageCustom) {
		this.messageCustom = messageCustom;
	}

}
