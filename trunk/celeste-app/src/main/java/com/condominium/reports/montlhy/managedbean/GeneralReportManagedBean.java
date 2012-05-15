package com.condominium.reports.montlhy.managedbean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import com.condominium.common.utils.JSFUtil;
import com.condominium.common.utils.TimeUtils;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.service.ReceiptsService;
import com.condominium.reports.generalreport.view.GeneralReceiptsReportView;

@ManagedBean(name="generalReportBean")
@ViewScoped
public class GeneralReportManagedBean implements Serializable{

	@ManagedProperty(value="#{receiptsService}")
	private ReceiptsService receiptsService;
	private String initMonth;
	private String initYear;
	private String endMonth;
	private String endYear;
	private String category;
	private GeneralReceiptsReportView gReceiptsReportView;
	private String receiptId = "";
	private boolean showReport;
	
	public void fillDropDowns(ComponentSystemEvent event){
		Calendar cal = Calendar.getInstance();
		if(initMonth == null && initYear == null){					
			this.initMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
			this.initYear = String.valueOf(cal.get(Calendar.YEAR));
			this.receiptId = "-1";
			//Adding one month to end date
			String date = TimeUtils.buildStringDate("01", initMonth, initYear);
			Calendar ncal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				ncal.setTime(sdf.parse(date));
				ncal.add(Calendar.MONTH, 1);
			} catch (ParseException e) {				
			}
			endMonth = String.valueOf(ncal.get(Calendar.MONTH) + 1);
			endYear = String.valueOf(ncal.get(Calendar.YEAR));
		}else if(initMonth != null && initYear != null){
			if(initMonth.equals("") && initYear.equals("")){
				this.initMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
				this.initYear = String.valueOf(cal.get(Calendar.YEAR));
				this.receiptId = "-1";
				//Adding one month to end date
				String date = TimeUtils.buildStringDate("01", initMonth, initYear);
				Calendar ncal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					ncal.setTime(sdf.parse(date));
					ncal.add(Calendar.MONTH, 1);
				} catch (ParseException e) {				
				}
				endMonth = String.valueOf(ncal.get(Calendar.MONTH) + 1);
				endYear = String.valueOf(ncal.get(Calendar.YEAR));
			}
		}
	}
	
	public String searchAction(){		
		try {
			
			Date initDate = new Date("01/"+initMonth+"/"+initYear);
			Date endDate = new Date("01/"+endMonth+"/"+endYear);
			
			if(endDate.before(initDate)){
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "La fecha final debe ser mayor que la fina inicial.", "La fecha final debe ser mayor que la fina inicial.");
				return null;
			}
			if(receiptId.equals("-1")){
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Selecciona un tipo de Ingreso.", "Selecciona un tipo de Ingreso.");
				return null;
			}
			
			gReceiptsReportView = receiptsService.getGeneralReport(initMonth, initYear, endMonth, endYear, receiptId);
			if(!gReceiptsReportView.getGeneralReportViews().isEmpty()){
				this.showReport = true;	
			}			
		} catch (ReceiptsException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getCustomError(), e.getErrorCode());
		}		
		return null;
	}	
				
	public boolean isShowReport() {
		return showReport;
	}

	public void setShowReport(boolean showReport) {
		this.showReport = showReport;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public GeneralReceiptsReportView getgReceiptsReportView() {
		return gReceiptsReportView;
	}

	public void setgReceiptsReportView(GeneralReceiptsReportView gReceiptsReportView) {
		this.gReceiptsReportView = gReceiptsReportView;
	}

	public String getInitMonth() {
		return initMonth;
	}

	public void setInitMonth(String initMonth) {
		this.initMonth = initMonth;
	}

	public String getInitYear() {
		return initYear;
	}

	public void setInitYear(String initYear) {
		this.initYear = initYear;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setReceiptsService(ReceiptsService receiptsService) {
		this.receiptsService = receiptsService;
	}			
}
