package com.condominium.expenses.dto;

import java.io.Serializable;

/**
 * 
 * @author rioslore
 *
 */
public class ExpensesDetailReportDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7530415310878793783L;
	
	private String comments;
	private double amount;
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}	
}
