package com.condominium.expenses.view;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class SuppliersView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6891583227595148493L;
	private String provId;
	private String name;
	private String address;
	private String phoneNumer;
	private String cellPhoneNumber;
	
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
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
	public String getPhoneNumer() {
		return phoneNumer;
	}
	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
	}
	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	
	
}
