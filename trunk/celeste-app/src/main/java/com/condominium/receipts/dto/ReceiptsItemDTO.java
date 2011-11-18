package com.condominium.receipts.dto;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class ReceiptsItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6342297817126109767L;

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
