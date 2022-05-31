package com.nagarro.assignment.accountstatements.controller.dtos;

import java.io.Serializable;

public class ErrorInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String desc;
	
	public ErrorInfo(String code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
