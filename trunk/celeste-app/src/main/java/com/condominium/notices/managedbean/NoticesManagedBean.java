package com.condominium.notices.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.condominium.common.utils.JSFUtil;
import com.condominium.notices.exception.NoticesException;
import com.condominium.notices.service.NoticesService;
import com.condominium.notices.view.NoticesView;
/**
 * 
 * @author rioslore
 *
 */
@ManagedBean(name="noticesBean")
@SessionScoped
public class NoticesManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8149924151117025327L;

	private static final Logger log = Logger.getLogger(NoticesManagedBean.class);
	
	@ManagedProperty(value="#{noticesService}")
	private NoticesService noticesService;	
	private List<SelectItem> noticesCatalogList;
	private List<NoticesView> noticesList;
	private NoticesView view = new NoticesView();
	private NoticesView editView = new NoticesView();
	
	
	public void clean(){
		this.view = new NoticesView();
		this.editView = null;		
	}
		
	public void fillList(ComponentSystemEvent event){		
		try {
			noticesList = noticesService.getNoticesList();
		} catch (NoticesException e) {
			log.equals(e);
		}
	}
	
	public String goAddNotice(){		
		return "registrar_aviso";
	}	
	
	public String goEditNotice(){
		String id = JSFUtil.getParam("id");		
		try{
			this.editView = noticesService.getNoticeById(Integer.parseInt(id));
		} catch (NoticesException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Error al obtener aviso.", "Error al obtener aviso.");
			log.error(e);		
		}
		return "editar_aviso";
	}
	
	public String goBackAction(){
		return "listado_avisos?faces-redirect=true";
	}
	
	public String addNotices(){		
		try {
			noticesService.addNotices(view);
			clean();
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El aviso se ha agregado exitosamente.", "El aviso se ha agregado exitosamente.");
		} catch (NoticesException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Error al agregar un aviso.", "Error al agregar un aviso.");
			log.error(e);		
		}
		return "listado_avisos?faces-redirect=true";
	}
	
	public String editNotices(){
		try {			
			noticesService.editNotices(editView);
			clean();
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El aviso se ha editado exitosamente.", "El aviso se ha editado exitosamente.");			
		} catch (NoticesException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Error al editar un aviso.", "Error al editar un aviso.");
			log.error(e);		
		}
		return "listado_avisos?faces-redirect=true";
	}
	
	public String deleteNoticesAction(){
		try{
			String id = JSFUtil.getParam("id");								
			noticesService.deleteNotices(Integer.parseInt(id));			
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El aviso se ha eliminado con exito.", "El aviso se ha eliminado con exito.");			
		} catch (NoticesException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error de Ingresos");
			log.error(e);
			return null;
		}
		return "listado_avisos";
	}

	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}


	public List<SelectItem> getNoticesCatalogList() {		
		try {
			noticesCatalogList = noticesService.getNoticesCatalogList();
		} catch (NoticesException e) { 
			noticesCatalogList = new ArrayList<SelectItem>(0);
			log.error(e);
		}		
		return noticesCatalogList;
	}

	public NoticesView getView() {
		return view;
	}

	public void setView(NoticesView view) {
		this.view = view;
	}

	public NoticesView getEditView() {
		return editView;
	}

	public void setEditView(NoticesView editView) {
		this.editView = editView;
	}

	public List<NoticesView> getNoticesList() {
		return noticesList;
	}

	public void setNoticesList(List<NoticesView> noticesList) {
		this.noticesList = noticesList;
	}
		
}
