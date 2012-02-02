package com.condominium.cumulative.dto;

import java.io.Serializable;

/**
 * 
 * @author rioslore
 *
 */
public class CumulativeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2231087877643864228L;
	
	private int id;
	private int month;
	private int year;
	private double amount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
		
}
