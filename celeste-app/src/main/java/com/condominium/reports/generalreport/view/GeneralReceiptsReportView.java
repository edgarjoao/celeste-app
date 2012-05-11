package com.condominium.reports.generalreport.view;

import java.io.Serializable;
import java.util.List;

import com.condominium.condom.view.CondominiumsView;
/**
 * 
 * @author rioslore
 *
 */
public class GeneralReceiptsReportView implements Serializable{

	private List<CondominiumsView> condominiumsViews;
	private List<GeneralReportView> generalReportViews;
	private String catalogDescription;
	private String totalAmountPeriod;
		
	public List<CondominiumsView> getCondominiumsViews() {
		return condominiumsViews;
	}
	public void setCondominiumsViews(List<CondominiumsView> condominiumsViews) {
		this.condominiumsViews = condominiumsViews;
	}
	public List<GeneralReportView> getGeneralReportViews() {
		return generalReportViews;
	}
	public void setGeneralReportViews(List<GeneralReportView> generalReportViews) {
		this.generalReportViews = generalReportViews;
	}
	public String getCatalogDescription() {
		return catalogDescription;
	}
	public void setCatalogDescription(String catalogDescription) {
		this.catalogDescription = catalogDescription;
	}
	public String getTotalAmountPeriod() {
		return totalAmountPeriod;
	}
	public void setTotalAmountPeriod(String totalAmountPeriod) {
		this.totalAmountPeriod = totalAmountPeriod;
	}	
			
}
