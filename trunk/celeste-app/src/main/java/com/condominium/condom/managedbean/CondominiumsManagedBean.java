package com.condominium.condom.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.condominium.condom.service.CondominiumsService;
import com.condominium.condom.view.CondominiumsView;
import com.condominium.user.exception.UserException;

@ManagedBean(name="condominiumsBean")
@ViewScoped
public class CondominiumsManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6168183502798380463L;
	
	@ManagedProperty(value="#{condominiumsService}")
	private CondominiumsService condominiumsService;

	public String goPermissionsAction(){
		return "permisos";
	}
	
	public String goEditAction(){
		return "editar_condomino";
	}
	
	public List<CondominiumsView> getCondominiumsList(){
		List<CondominiumsView> list = new ArrayList<CondominiumsView>(0);		
		try {
			list = condominiumsService.getCondominiumsList();
		} catch (UserException e) {
			list = new ArrayList<CondominiumsView>(0);			
		}				
		return list;
	}
	
	
	public CondominiumsService getCondominiumsService() {
		return condominiumsService;
	}

	public void setCondominiumsService(CondominiumsService condominiumsService) {
		this.condominiumsService = condominiumsService;
	}	
	
}