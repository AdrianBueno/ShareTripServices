package com.sdi.infrastructure.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 7371788689980451102L;

	public BusinessException(String string) {
		super(string);
	}

}
