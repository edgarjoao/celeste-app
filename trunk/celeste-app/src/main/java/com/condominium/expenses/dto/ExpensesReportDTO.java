package com.condominium.expenses.dto;

import java.io.Serializable;

public class ExpensesReportDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1835764237443460660L;
	private String description;
	private double amount;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}	

}
