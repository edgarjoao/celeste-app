package com.condominium.cumulative.managedbean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import com.condominium.common.utils.JSFUtil;
import com.condominium.cumulative.exception.CumulativeException;
import com.condominium.cumulative.service.CumulativeService;
import com.condominium.cumulative.view.CumulativeView;

@ManagedBean(name="cumulativeBean")
@ViewScoped
public class CumulativeManagedBean implements Serializable {
	
	@ManagedProperty(value="#{cumulativeService}")
	private CumulativeService cumulativeService;

	private String month;
	private String year;	
	private List<CumulativeView> cumulativeList;
	
	public void fillDropDowns(ComponentSystemEvent event){
		Calendar cal = Calendar.getInstance();
		if(month == null && year == null){			
			this.month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			this.year = String.valueOf(cal.get(Calendar.YEAR));			
		}else if(month != null && year != null){
			if(month.equals("") && year.equals("")){
				this.month = String.valueOf(cal.get(Calendar.MONTH) + 1);
				this.year = String.valueOf(cal.get(Calendar.YEAR));	
			}
		}
		try{
			this.cumulativeList = cumulativeService.getCumulativeList();
		}catch(CumulativeException c){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, c.getCustomError(), c.getCustomError());
		}
	}
	
	public String generateCumulativeAction(){
		try{
			boolean result = cumulativeService.existCumulative(Integer.parseInt(month), Integer.parseInt(year));
			if(result){				
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "El acumulado que intenta generar ya existe.", "El acumulado que intenta generar ya existe.");
			}else{
				cumulativeService.generateCumulativeMonth(Integer.parseInt(month), Integer.parseInt(year));
				JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El Acumulado se a generado exitosamente.", "El Acumulado se a generado exitosamente.");
			}
		}catch(CumulativeException c){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, c.getExceptionCode(), c.getExceptionCode());
		}
		return null;
	}
	
	public String deleteAction(){
		try {
			String id = JSFUtil.getParam("id");
			cumulativeService.deleteCumulative(Integer.parseInt(id));
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El Acumulado se a eliminado exitosamente.", "El Acumulado se a eliminado exitosamente.");
		} catch (CumulativeException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getExceptionCode(), e.getExceptionCode());
			e.printStackTrace();
			return null;
		}	
		return null;
	}
	
	public List<CumulativeView> getCumulativeList() {
		return cumulativeList;
	}
	public void setCumulativeList(List<CumulativeView> cumulativeList) {
		this.cumulativeList = cumulativeList;
	}
	public void setCumulativeService(CumulativeService cumulativeService) {
		this.cumulativeService = cumulativeService;
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
	
}
