package com.nagarro.assignment.accountstatements.controller.dtos;

import java.io.Serializable;

import com.nagarro.assignment.accountstatements.exception.ErrorCodes;

public class APIResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private ErrorInfo errorInfo;
	
	public APIResponse() {
		
	}

	public APIResponse(ErrorCodes errorCode) {
		this.errorInfo = new ErrorInfo(errorCode.getCode(), errorCode.getDesc());
	}
	
	public void setFailure(ErrorCodes errorCode) {
		this.errorInfo = new ErrorInfo(errorCode.getCode(), errorCode.getDesc());
	}
	
	public void setFailure(String code, String desc) {
		this.errorInfo = new ErrorInfo(code, desc);
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	
}
