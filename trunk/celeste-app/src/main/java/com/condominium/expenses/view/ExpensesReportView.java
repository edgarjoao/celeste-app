package com.condominium.expenses.view;

import java.io.Serializable;

public class ExpensesReportView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -126858079366901460L;
	
	private String description;
	private String amount;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}	
	
}
