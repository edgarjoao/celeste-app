package com.condominium.expenses.view;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class ExpensesDetailReportView implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8462207770855937049L;
	
	private String comments;
	private String amount;
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}	
	
}
