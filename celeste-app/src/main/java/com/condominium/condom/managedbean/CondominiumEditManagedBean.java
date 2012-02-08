package com.condominium.condom.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;

import com.condominium.common.utils.JSFUtil;
import com.condominium.user.exception.UserException;
import com.condominium.user.service.UserService;
import com.condominium.user.view.UserView;

@ManagedBean(name="condominiumEditBean")
@ViewScoped
public class CondominiumEditManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -956231363355662495L;
	
	@ManagedProperty("#{userService}")
	private UserService userService;
	private UserView userView;
	private List<SelectItem> rolList;
		
	public void prefill(ComponentSystemEvent event){
		String userId = JSFUtil.getParam("id");
		if(userId != null){
			try {
				userView = userService.getUserById(Integer.parseInt(userId));
				rolList = userService.getRoles();
			} catch (NumberFormatException e) {
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "El Id de usuario es incorrecto.", "El Id de usuario es incorrecto.");
			} catch (UserException e) {
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getExceptionCode(), e.getExceptionCode());
			}
		}
	}
	
	public String editAction(){		
		try{
			this.userService.editUser(userView);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El usuario " + userView.getUsername()+ " se ha modificado exitosamente" , "El usuario " + userView.getUsername()+ " se ha modificado exitosamente");
		} catch (UserException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getExceptionCode(), e.getExceptionCode());
		}		
		return "listado_condominos";
	}
		
	
	public List<SelectItem> getRolList() {
		return rolList;
	}

	public void setRolList(List<SelectItem> rolList) {
		this.rolList = rolList;
	}

	public String goBackAction(){
		return "listado_condominos";
	}
	
	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
		
}
