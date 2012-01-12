package com.condominium.common.listener.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.condominium.log.dao.LogDAO;
import com.condominium.log.exception.LogException;
import com.condominium.user.view.UserView;
/**
 * Este Listener se utiliza para registrar cuando un usuario ingresa al sistema.
 * @author rioslore
 *
 */
public class CondominiumSessionAttributeListener implements HttpSessionAttributeListener {

	ServletContext servletContext = null;
	ApplicationContext context  = null;
	
	/**
	 * Registra cuando ingresa un usuario al sistema.
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {		
		servletContext = se.getSession().getServletContext();
		context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		UserView userView = (UserView) se.getSession().getAttribute("userView");		
		if(userView != null){
			LogDAO logDAO = (LogDAO) context.getBean("LogDAOImpl");
			try {
				logDAO.addSystemEntry(userView);
			} catch (LogException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Registra cuando un usuario sale del sistema
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		servletContext = se.getSession().getServletContext();
		context = WebApplicationContextUtils.getWebApplicationContext(servletContext);	
		if(se.getValue() instanceof UserView){
			UserView userView = (UserView) se.getValue();
			if(userView != null){
				LogDAO logDAO = (LogDAO) context.getBean("LogDAOImpl");
				try {
					logDAO.addSystemExit(userView);
				} catch (LogException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {

	}

}
