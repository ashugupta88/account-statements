package com.nagarro.assignment.accountstatements.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCodes errorCode;
	
	public UnauthorizedException(ErrorCodes error) {
		this.errorCode = error;
	}
	
	public ErrorCodes getErrorCode() {
		return errorCode;
	}

}
