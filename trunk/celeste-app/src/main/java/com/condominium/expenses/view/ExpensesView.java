package com.condominium.expenses.view;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class ExpensesView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5442461643890421999L;
	private String id;
	private String date;
	private String amount;
	private String comments;
	private ExpensesItemView expensesItemView = new ExpensesItemView();
	private SuppliersView suppliersView = new SuppliersView();
	private int userId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ExpensesItemView getExpensesItemView() {
		return expensesItemView;
	}
	public void setExpensesItemView(ExpensesItemView expensesItemView) {
		this.expensesItemView = expensesItemView;
	}
	public SuppliersView getSuppliersView() {
		return suppliersView;
	}
	public void setSuppliersView(SuppliersView suppliersView) {
		this.suppliersView = suppliersView;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}	
}
