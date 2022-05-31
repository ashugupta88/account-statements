package com.nagarro.assignment.accountstatements.controller.dtos;

public class AccountStatementRequest {

	private String accountNumber;
	private String fromDate;
	private String toDate;
	private String fromAmount;
	private String toAmount;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(String fromAmount) {
		this.fromAmount = fromAmount;
	}
	public String getToAmount() {
		return toAmount;
	}
	public void setToAmount(String toAmount) {
		this.toAmount = toAmount;
	}
	
}
