package com.altimetrik.rally.exception;

public class AltiRallyApplicationException extends Exception {

	private String errorMessage;
	
	public AltiRallyApplicationException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
