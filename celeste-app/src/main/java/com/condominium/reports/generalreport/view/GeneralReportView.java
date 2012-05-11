package com.condominium.reports.generalreport.view;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author rioslore
 *
 */
public class GeneralReportView implements Serializable {
	
	private String rangeDate;	
	private List<Double> amount;
	private String totalAmount;
		
	public String getRangeDate() {
		return rangeDate;
	}
	public void setRangeDate(String rangeDate) {
		this.rangeDate = rangeDate;
	}	
	public List<Double> getAmount() {
		return amount;
	}
	public void setAmount(List<Double> amount) {
		this.amount = amount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}		
}
