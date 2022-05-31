package com.nagarro.assignment.accountstatements.exception;

public enum ErrorCodes {

	SYS_INVALID_REQUEST("Invalid request, please try with a valid input"),
	SYS_TECHNICAL_ERROR("Some error happened, please try again"),
	SYS_UNAUTHORIZED("Request not allowed"),
	SYS_TOKEN_ERROR("Not able to find token, please try again"),
	SYS_INVALID_CRED("Invalid Credentials"),
	SYS_INVALID_USER("Invalid username"),
	SYS_USER_DISABLED("User disabled"),
	SYS_NO_RECORDS_FOUND("No Records Found");
	
	private String code;
	private String desc;
	
	private ErrorCodes(String desc) {
		this.code = name();
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}
}

