package com.condominium.common.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Edgar Joao
 *
 */
public class JSFUtil{

    private static HttpSession getWebSession(){
        return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static <T> T getSessionAttribute(Class<T> clase, String nombreParametro){
        return clase.cast(getWebSession().getAttribute(nombreParametro));
    }

    public static void setSessionAttribute(Object parametro, String nombreParametro){
        getWebSession().setAttribute(nombreParametro, parametro);
    }

    public static void closeSession() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
    }

    public static void writeMessage(Severity severity, String title, String message){
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(severity, title, message));
    }
    
/*	@SuppressWarnings("unchecked")
	public static Object getManagedBean(String name, Class managedBeanClass){
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{" + name + "}", managedBeanClass).getValue(context.getELContext());
    }   */
    
    public static void redirect(String view){
    	try{
    		FacesContext.getCurrentInstance().getExternalContext().redirect(view);
    	}catch(Exception e){
    		e.printStackTrace(System.err);
    	}
    }
}