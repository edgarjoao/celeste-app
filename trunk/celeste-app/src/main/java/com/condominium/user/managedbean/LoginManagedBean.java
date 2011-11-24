package com.condominium.user.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.condominium.common.utils.JSFUtil;
import com.condominium.user.exception.UserException;
import com.condominium.user.service.UserService;
import com.condominium.user.view.UserView;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginManagedBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1892423706846603202L;
	private static final String VIEW_HOME = "home.jsf";

	@ManagedProperty(value="#{userService}")
	private UserService userService;
	
	private String username;
	private String password;
	
	public String loginAction(){		
		try {
			HttpSession session = null;
			UserView userView = userService.loginUser(username, password);									
			if(userView != null){
				session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("userView", userView);
			}
			JSFUtil.redirect(VIEW_HOME);
		} catch (UserException userException) {			
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Password incorrecto", "Login Error");
		}		
		return null;
	}	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
