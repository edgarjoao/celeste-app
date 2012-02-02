package com.condominium.receipts.view;

import java.io.Serializable;

public class ReceiptsReportView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2615154118553495779L;
	private String monthName;
	private String amount;
	private String description;
	
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
}
