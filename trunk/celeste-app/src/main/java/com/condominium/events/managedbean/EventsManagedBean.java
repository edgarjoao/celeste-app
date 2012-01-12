package com.condominium.events.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;

import com.condominium.common.utils.JSFUtil;
import com.condominium.common.utils.Month;
import com.condominium.common.utils.StringUtils;
import com.condominium.common.utils.TimeUtils;
import com.condominium.condom.service.CondominiumsService;
import com.condominium.events.view.EventsView;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.service.ReceiptsService;
import com.condominium.user.exception.UserException;

@ManagedBean(name="eventsBean")
@ViewScoped
public class EventsManagedBean implements Serializable {

	@ManagedProperty(value="#{receiptsService}")
	private ReceiptsService receiptsService;
	@ManagedProperty(value="#{condominiumsService}")
	private CondominiumsService condominiumsService;

	private String month;
	private String year;
	private String htmlCalendar;
	private String date;
	private String condId;
	private String calendarHeader;
	private String description;
	private String hiddenValue;
	private boolean showAddPanel = false;
	private boolean showEditPanel = false;
	
	private String hiddenDay;
	private String hiddenMonth;
	private String hiddenYear;
	
	private EventsView editEventsView;
	
	
	public void clean(){
		this.condId = "";
		this.description = "";
		this.hiddenValue = "";
		this.editEventsView = null;
		this.date = "";		
	}

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
	
	public String viewReservationDetailAction(){			
		try {
			editEventsView = receiptsService.getEventEventsViewById(Integer.parseInt(hiddenValue));
			this.date = TimeUtils.buildStringDate(String.valueOf(editEventsView.getDay()), String.valueOf(editEventsView.getMonth()), String.valueOf(editEventsView.getYear()));
			this.showEditPanel = true;
			this.showAddPanel = false;
		} catch (ReceiptsException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}		
		return null;
	}
	
	public String addReservationAction(){
		Calendar cal = Calendar.getInstance();
		EventsView view = new EventsView();
		try {
			view.setCondId(Integer.parseInt(condId));
			view.setDescription(description);
			//Apartado
			Date d = TimeUtils.convertStringToDate(date, TimeUtils.DATE_PATTERN_DDMMYYYY);
			cal.setTime(d);
			view.setYear(cal.get(Calendar.YEAR));
			view.setMonth(cal.get(Calendar.MONTH));
			view.setDay(cal.get(Calendar.DATE));			
			view.setStatus("A");			
			this.receiptsService.insertEventView(view);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Se ha registrado la reservacion exitosamente.", "Se ha registrado la reservacion exitosamente.");
			this.showAddPanel = false;
		} catch (ReceiptsException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getErrorCode(), e.getErrorCode());
		}
		return null;
	}
	
	public String deleteReservationAction(){
		try{						
			receiptsService.deleteEventView(editEventsView.getId());
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "La reservacion se ha eliminado exitosamente.", "La reservacion se ha eliminado exitosamente.");
			this.showEditPanel = false;
		} catch (ReceiptsException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getErrorCode(), e.getErrorCode());
		}
		return null;
	}
	
	public String editReservationAction(){
		try{						
			Calendar cal = Calendar.getInstance();
			Date d = TimeUtils.convertStringToDate(date, TimeUtils.DATE_PATTERN_DDMMYYYY);
			cal.setTime(d);
			editEventsView.setYear(cal.get(Calendar.YEAR));
			editEventsView.setMonth(cal.get(Calendar.MONTH));
			editEventsView.setDay(cal.get(Calendar.DATE));			
			receiptsService.editEventView(editEventsView);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "La reservacion se ha modificado exitosamente.", "La reservacion se ha modificado exitosamente.");
			this.showEditPanel = false;
		} catch (ReceiptsException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getErrorCode(), e.getErrorCode());
		}
		return null;
	}
	
	public String cancelAction(){
		this.clean();
		this.showAddPanel = false;
		this.showEditPanel = false;
		return null;
	}
	
	
	public String searchDateAction(){
		List<EventsView> eventsList = new ArrayList<EventsView>(0);
		try {
			eventsList = receiptsService.getEventsList(Integer.parseInt(month), Integer.parseInt(year));
		} catch (ReceiptsException expensesException) { 			
		}
		this.showAddPanel = false;
		this.showEditPanel = false;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.DATE, 1);
		
		this.htmlCalendar = this.buildCalendar(eventsList, cal);
		return null;
	}
	
	
	private String buildCalendar(List<EventsView> eventsList, Calendar cal){				
		StringBuilder html = new StringBuilder(0);		
		html.append("<table width=\"100%\" align=\"center\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\" bgcolor=\"#CCCCC0\">");
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
						html.append("<td height=\"35\" class=\"etiqueta_reservado\" title=\"Reservado por "+datos.toString()+" \" > " + var + " <a href=\"#\" style=\"text-decoration:none\" onclick=\"detalleReservacion("+ view.getId()+");\" > Detalle </a> </td>");
					}else{
						html.append("<td height=\"35\" class=\"etiqueta_campo\">" + var + " <a href=\"#\" style=\"text-decoration:none\" onclick=\"nuevaReservacion('"+var+"','"+(aMonth.getMonth() + 1) +"','"+ aMonth.getYear()+"');\" > Reservar </a>  </td>");	
					}						
				}
			}
			html.append("</tr>");
		}			  	
		
		html.append("</tbody></table>");		
		return html.toString();
	}
	
	public String showAddButtonAction(){
		this.showAddPanel = true;
		this.showEditPanel = false;
		this.clean();
		this.date = TimeUtils.buildStringDate(hiddenDay, hiddenMonth, hiddenYear);			
		return null;
	}
	
	public List<SelectItem> getCondominiumsList(){
		List<SelectItem> list = null;
		try {
			list = condominiumsService.getCondominiumsItems();
		} catch (UserException e) {
			e.printStackTrace();
		}
		return list;
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

	public void setReceiptsService(ReceiptsService receiptsService) {
		this.receiptsService = receiptsService;
	}	

	public void setCondominiumsService(CondominiumsService condominiumsService) {
		this.condominiumsService = condominiumsService;
	}

	public String getHtmlCalendar() {
		return htmlCalendar;
	}

	public void setHtmlCalendar(String htmlCalendar) {
		this.htmlCalendar = htmlCalendar;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCondId() {
		return condId;
	}

	public void setCondId(String condId) {
		this.condId = condId;
	}

	public String getCalendarHeader() {
		return calendarHeader;
	}

	public void setCalendarHeader(String calendarHeader) {
		this.calendarHeader = calendarHeader;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isShowAddPanel() {
		return showAddPanel;
	}

	public void setShowAddPanel(boolean showAddPanel) {
		this.showAddPanel = showAddPanel;
	}

	public String getHiddenValue() {
		return hiddenValue;
	}

	public void setHiddenValue(String hiddenValue) {
		this.hiddenValue = hiddenValue;
	}

	public String getHiddenDay() {
		return hiddenDay;
	}

	public void setHiddenDay(String hiddenDay) {
		this.hiddenDay = hiddenDay;
	}

	public String getHiddenMonth() {
		return hiddenMonth;
	}

	public void setHiddenMonth(String hiddenMonth) {
		this.hiddenMonth = hiddenMonth;
	}

	public String getHiddenYear() {
		return hiddenYear;
	}

	public void setHiddenYear(String hiddenYear) {
		this.hiddenYear = hiddenYear;
	}

	public boolean isShowEditPanel() {
		return showEditPanel;
	}

	public void setShowEditPanel(boolean showEditPanel) {
		this.showEditPanel = showEditPanel;
	}

	public EventsView getEditEventsView() {
		return editEventsView;
	}

	public void setEditEventsView(EventsView editEventsView) {
		this.editEventsView = editEventsView;
	}
	
		
}
