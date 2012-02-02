package com.condominium.events.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import com.condominium.common.utils.JSFUtil;
import com.condominium.common.utils.Month;
import com.condominium.common.utils.StringUtils;
import com.condominium.events.view.EventsView;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.service.ReceiptsService;

@ManagedBean(name="reservationBean")
@ViewScoped
public class ReservationsManagedBean implements Serializable {

	@ManagedProperty(value="#{receiptsService}")
	private ReceiptsService receiptsService;	
	
	private String month;
	private String year;
	private String htmlCalendar;
	private String calendarHeader;
	
	public void fillCalendar(ComponentSystemEvent event){
		Calendar cal = Calendar.getInstance();
		calendarHeader = StringUtils.getMonth(cal.get(Calendar.MONTH) + 1) + " " + cal.get(Calendar.YEAR);
		if(month == null && year == null){			
			this.month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			this.year = String.valueOf(cal.get(Calendar.YEAR));			
		}else if(month != null && year != null){
			if(month.equals("") && year.equals("")){
				this.month = String.valueOf(cal.get(Calendar.MONTH) + 1);
				this.year = String.valueOf(cal.get(Calendar.YEAR));	
			}
		}
		cal.set(Calendar.DATE, 1);
		List<EventsView> eventsList = new ArrayList<EventsView>(0);
		try {
			eventsList = receiptsService.getEventsList(Integer.parseInt(month), Integer.parseInt(year));
		} catch (ReceiptsException e) { 			
		}
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		this.htmlCalendar = this.buildCalendar(eventsList, cal);		
	}
	
	private String buildCalendar(List<EventsView> eventsList, Calendar cal){				
		StringBuilder html = new StringBuilder(0);		
		html.append("<table width=\"80%\" align=\"center\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\" bgcolor=\"#CCCCC0\">");
		html.append("<tr> <td width=\"5%\" class=\"titulocolumna\" align=\"center\">Domingo</td>");
		html.append("<td width=\"5%\" class=\"titulocolumna\" align=\"center\">Lunes</td>");
		html.append("<td width=\"5%\" class=\"titulocolumna\" align=\"center\">Martes</td>");
		html.append("<td width=\"5%\" class=\"titulocolumna\" align=\"center\">Miercoles</td>");
		html.append("<td width=\"5%\" class=\"titulocolumna\" align=\"center\">Jueves</td>");
		html.append("<td width=\"5%\" class=\"titulocolumna\" align=\"center\">Vienes</td>");
		html.append("<td width=\"5%\" class=\"titulocolumna\" align=\"center\">Sabado</td></tr>");
							
		Month aMonth = Month.getMonth( cal.get(Calendar.MONTH), cal.get(Calendar.YEAR) );
		int [][] days = aMonth.getDays();
		for( int i=0; i < aMonth.getNumberOfWeeks(); i++ ){
			html.append("<tr>");
			for( int j=0; j<7; j++ ){				
				if( days[i][j] == 0 ){
					html.append("<td height=\"10%\" class=\"etiqueta\"> &nbsp; </td>");
				}else{
					int var = days[i][j];					
					boolean condition = false;
					EventsView view = null;
					for(EventsView v : eventsList){
						if(v.getDay() == var){
							view = v;
							condition = true;
							break;
						}
					}
					if(condition){
						StringBuilder alt = new StringBuilder(0);
						alt.append("Reservado status: " + view.getStatus());
						StringBuilder datos = new StringBuilder(0);
						datos.append(view.getUserView().getNombre() != null ? view.getUserView().getNombre() : "").append(" ");
						datos.append(view.getUserView().getApaterno() != null ? view.getUserView().getApaterno() : "").append(" ");
						datos.append(view.getUserView().getAmaterno() != null ? view.getUserView().getAmaterno() : "");
						datos.append(" Casa " + view.getHouseNumber());
						html.append("<td height=\"35\" class=\"etiqueta_reservado\" title=\"Reservado por "+datos.toString()+" \" > " + var + " </td>");
					}else{
						html.append("<td height=\"35\" class=\"etiqueta_campo\">" + var + " </td>");	
					}						
				}
			}
			html.append("</tr>");
		}
		html.append("</tbody></table>");		
		return html.toString();
	}
	
	public String searchDateAction(){
		List<EventsView> eventsList = new ArrayList<EventsView>(0);
		try {
			eventsList = receiptsService.getEventsList(Integer.parseInt(month), Integer.parseInt(year));
		} catch (ReceiptsException expensesException) { 
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, expensesException.getMessage(), expensesException.getExceptionCode());
		}		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.DATE, 1);		
		this.htmlCalendar = this.buildCalendar(eventsList, cal);
		return null;
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

	public String getHtmlCalendar() {
		return htmlCalendar;
	}

	public void setHtmlCalendar(String htmlCalendar) {
		this.htmlCalendar = htmlCalendar;
	}

	public String getCalendarHeader() {
		return calendarHeader;
	}

	public void setCalendarHeader(String calendarHeader) {
		this.calendarHeader = calendarHeader;
	}

	public void setReceiptsService(ReceiptsService receiptsService) {
		this.receiptsService = receiptsService;
	}
	
}
