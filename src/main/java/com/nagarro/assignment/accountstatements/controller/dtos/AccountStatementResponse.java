package com.nagarro.assignment.accountstatements.controller.dtos;

import java.util.List;

public class AccountStatementResponse {

	private String accountNumber;
	private String accountType;
	private List<AccountStatement> statement;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public List<AccountStatement> getStatement() {
		return statement;
	}
	public void setStatement(List<AccountStatement> statement) {
		this.statement = statement;
	}
	
	@Override
	public String toString() {
		return "AccountStatementResponse [accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", statement=" + statement + "]";
	}
	
}
