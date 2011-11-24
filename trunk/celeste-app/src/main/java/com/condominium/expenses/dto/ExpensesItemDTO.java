package com.condominium.expenses.dto;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class ExpensesItemDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 209715737582101617L;
	private int id;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
