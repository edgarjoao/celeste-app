package com.condominium.user.managedbean;

import java.io.Serializable;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.condominium.common.utils.JSFUtil;
import com.condominium.user.exception.UserException;
import com.condominium.user.service.UserService;
import com.condominium.user.view.UserView;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginManagedBean implements Serializable {
	
	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(LoginManagedBean.class);
	private static final long serialVersionUID = 1892423706846603202L;
	private static final String VIEW_HOME = "home.jsf";
	private static final String VIEW_OUT = "index.jsf";

	@ManagedProperty(value="#{userService}")
	private UserService userService;
	
	private String username;
	private String password;
	private UserView userView;	
	
	public String loginAction(){		
		try {
			HttpSession session = null;
			UserView userView = userService.loginUser(username, password);									
			if(userView != null){
				session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("userView", userView);
				this.userView = userView;
			}
			JSFUtil.redirect(VIEW_HOME);
		} catch (UserException userException) {
			log.info(userException);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Password incorrecto", "Login Error");
		}		
		return null;
	}	
	
	public void logOutAction(){
		HttpSession session = null;
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("userView", null);		
		JSFUtil.redirect(VIEW_OUT);		
	}
	
	 public void isAdmin(ComponentSystemEvent event){
		UserView userSessionView = JSFUtil.getSessionAttribute(UserView.class, "userView");
		FacesContext fc = FacesContext.getCurrentInstance();		 
		if (!userSessionView.getRolDescription().equals("Administrador")){		 
			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();		 
			nav.performNavigation("access-denied");
		}		
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

	public UserView getUserView() {
		return userView;
	}
	
	public void setUserView(UserView userView) {
		this.userView = userView;
	}

}
