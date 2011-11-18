package com.condominium.condom.view;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class VehiclesView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7483456553629042637L;
	private String id;
	private String description;
	private String model;
	private String plates;
	private String condominiumId;
	
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPlates() {
		return plates;
	}
	public void setPlates(String plates) {
		this.plates = plates;
	}
	public String getCondominiumId() {
		return condominiumId;
	}
	public void setCondominiumId(String condominiumId) {
		this.condominiumId = condominiumId;
	}
	
}
