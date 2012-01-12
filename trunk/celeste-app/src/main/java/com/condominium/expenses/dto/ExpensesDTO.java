package com.condominium.expenses.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author rioslore
 *
 */
public class ExpensesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5064165897083801291L;
	private int id;
	private Date date;
	private double amount;
	private String comments;	
	private ExpensesItemDTO expensesItemDTO;
	private SuppliersDTO suppliersDTO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ExpensesItemDTO getExpensesItemDTO() {
		return expensesItemDTO;
	}
	public void setExpensesItemDTO(ExpensesItemDTO expensesItemDTO) {
		this.expensesItemDTO = expensesItemDTO;
	}
	public SuppliersDTO getSuppliersDTO() {
		return suppliersDTO;
	}
	public void setSuppliersDTO(SuppliersDTO suppliersDTO) {
		this.suppliersDTO = suppliersDTO;
	}
			
}
