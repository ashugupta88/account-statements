package com.nagarro.assignment.accountstatements.controller.dtos;

import java.util.Date;

public class AccountStatement {

	private Date date;
	private Double amount;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "AccountStatement [date=" + date + ", amount=" + amount + "]";
	}
	
	
}
