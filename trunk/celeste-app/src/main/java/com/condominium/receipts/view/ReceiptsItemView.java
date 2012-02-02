package com.condominium.receipts.view;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class ReceiptsItemView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6231761879992857590L;
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
