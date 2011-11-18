package com.condominium.condom.dto;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class VehiclesDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7735896652659716766L;
	private int id;
	private String description;
	private String plates;
	private int condominiumId;
	
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
	public String getPlates() {
		return plates;
	}
	public void setPlates(String plates) {
		this.plates = plates;
	}
	public int getCondominiumId() {
		return condominiumId;
	}
	public void setCondominiumId(int condominiumId) {
		this.condominiumId = condominiumId;
	}
		
}
