package com.condominium.notices.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ComponentSystemEvent;

import org.apache.log4j.Logger;

import com.condominium.notices.exception.NoticesException;
import com.condominium.notices.service.NoticesService;
import com.condominium.notices.view.NoticesView;
/**
 * 
 * @author rioslore
 *
 */
@ManagedBean(name="tickerBean")
public class TickerComponent implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 9008793097343261270L;

private static final Logger log = Logger.getLogger(TickerComponent.class);
	
	@ManagedProperty(value="#{noticesService}")
	private NoticesService noticesService;
	private List<NoticesView> noticesList;
	
	public void fillList(ComponentSystemEvent event){		
		try {
			noticesList = noticesService.getNoticesList();
		} catch (NoticesException e) {
			log.equals(e);
		}
	}
	
	
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	public List<NoticesView> getNoticesList() {
		return noticesList;
	}

	public void setNoticesList(List<NoticesView> noticesList) {
		this.noticesList = noticesList;
	}
		
	
}
