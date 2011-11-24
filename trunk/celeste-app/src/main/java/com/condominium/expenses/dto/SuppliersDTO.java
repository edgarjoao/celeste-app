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
	private int phoneNumer;
	private int cellPhoneNumber;
	
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
	public int getPhoneNumer() {
		return phoneNumer;
	}
	public void setPhoneNumer(int phoneNumer) {
		this.phoneNumer = phoneNumer;
	}
	public int getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(int cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
		
}
