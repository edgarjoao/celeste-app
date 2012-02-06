package com.condominium.common.utils;

import javax.faces.view.facelets.FaceletContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * Esta clase sirve para obtener el bean del application context de spring
 * @author rioslore
 *
 */
public final class SpringContextUtil {
	
	@SuppressWarnings("static-access")
	public static Object findBean(FaceletContext faceletContext, String name){
		HttpServletRequest request =(HttpServletRequest)
							faceletContext.getFacesContext().getCurrentInstance()
																.getExternalContext().getRequest();
		final ServletContext servletContext = request.getSession().getServletContext();
		final ApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);			
		return springContext.getBean(name);
	}
	
	@SuppressWarnings("static-access")
	public static Object findBean(FaceletContext faceletContext, Class<?> clase){
		HttpServletRequest request =(HttpServletRequest)
							faceletContext.getFacesContext().getCurrentInstance()
																.getExternalContext().getRequest();
		final ServletContext servletContext = request.getSession().getServletContext();
		final ApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);			
		return springContext.getBean(clase);
	}
	
}
