package com.nagarro.assignment.accountstatements.exception;

import java.io.Serializable;

public class ServiceException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private ErrorCodes errorCode;
	
	public ServiceException(ErrorCodes error) {
		this.errorCode = error;
	}
	
	public ErrorCodes getErrorCode() {
		return errorCode;
	}	
}
