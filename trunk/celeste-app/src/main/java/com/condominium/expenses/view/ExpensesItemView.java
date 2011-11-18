package com.condominium.expenses.view;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class ExpensesItemView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6327080225614009639L;
	private String id;
	private String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
}
