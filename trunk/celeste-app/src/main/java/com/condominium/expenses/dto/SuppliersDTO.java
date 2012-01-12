package com.condominium.expenses.dto;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class SuppliersDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1306548056979102544L;
	private int provId;
	private String name;
	private String address;
	private String phoneNumber;
	private String cellPhoneNumber;
	
	public int getProvId() {
		return provId;
	}
	public void setProvId(int provId) {
		this.provId = provId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}		
		
}
