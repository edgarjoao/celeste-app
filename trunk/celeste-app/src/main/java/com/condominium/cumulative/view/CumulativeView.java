package com.condominium.cumulative.view;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class CumulativeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5185736180231714683L;
	private String id;
	private String month;
	private String year;
	private String amount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}	
}
