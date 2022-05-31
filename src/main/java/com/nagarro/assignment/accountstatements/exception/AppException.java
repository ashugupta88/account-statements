package com.nagarro.assignment.accountstatements.exception;

import java.io.Serializable;
import java.util.List;

public class AppException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private ErrorCodes errorCode;
	private List<String> errorDetails;
	
	public AppException(ErrorCodes error) {
		this.errorCode = error;
	}
	
	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public List<String> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<String> errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
}
