package com.condominium.receipts.dto;

import java.io.Serializable;

public class ReceiptsReportDTO implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9094831096951496487L;
	private int month;
	private double amount;
	private String description;
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
		
}
