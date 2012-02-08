package com.condominium.permissions.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import org.apache.log4j.Logger;

import com.condominium.common.utils.JSFUtil;
import com.condominium.permissions.exception.PermissionsException;
import com.condominium.permissions.service.PermissionsService;
import com.condominium.permissions.view.PermissionView;
import com.condominium.user.exception.UserException;
import com.condominium.user.service.UserService;
import com.condominium.user.view.UserView;

@ManagedBean(name="permissionBean")
@ViewScoped
public class PermissionsManagedBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9211108943948787458L;

	private static final Logger log = Logger.getLogger(PermissionsManagedBean.class);

	@ManagedProperty("#{permissionsService}")
	private PermissionsService permissionsService;
	@ManagedProperty(value="#{userService}")
	private UserService userService;
	private UserView userView;
	private List<PermissionView> permissionViews;

	public void fillTable(ComponentSystemEvent event){
		String userId = JSFUtil.getParam("id");
		try {
			userView = userService.getUserById(Integer.parseInt(userId));
			permissionViews = permissionsService.permissionList(Integer.parseInt(userId));
		} catch (NumberFormatException e) {
			log.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "El Id de usuario es incorrecto.", "El Id de usuario es incorrecto.");
		} catch (PermissionsException e) {
			log.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getExceptionCode(), e.getExceptionCode());
		} catch (UserException e) {
			log.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getExceptionCode(), e.getExceptionCode());
		}		
	}
	
	public String savePermissionsAction(){		
		try {
			permissionsService.savePermissions(permissionViews, userView.getUserId());			
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "Los permisos para el usuario "+ userView.getUsername()+" se han modificado correctamente.", "Los permisos para el usuario "+ userView.getUsername()+" se han modificado correctamente.");		
		} catch (PermissionsException e) {
			log.error(e);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getExceptionCode(), e.getExceptionCode());
		}
		
		return "listado_condominos";
	}
	
	public String backAction(){
		return "listado_condominos";
	}
	
	public void setPermissionsService(PermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}

	public List<PermissionView> getPermissionViews() {
		return permissionViews;
	}

	public void setPermissionViews(List<PermissionView> permissionViews) {
		this.permissionViews = permissionViews;
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
