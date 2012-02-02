package com.condominium.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.condominium.user.view.UserView;

public class AuthenticationFilter implements Filter {
	
	/** Nombre de variable de session para el usuario */
	private static final String USER_SESSION_NAME = "userView";
	/** Accion de logout */
	private static final String LOGOUT_ACTION = "/index.jsf";
	/** Accion de login */
	private static final String MAIN_ACTION = "/home.jsf";
	/** Accion de error */
	private static final String ERROR_ACTION = "/error.xhtml";
	/** Accion de error */
	private static final String ERROR_404_ACTION = "/error_404.xhtml";
	/** Accion de error */
	private static final String ERROR_500_ACTION = "/error_500.xhtml";
	/** Accion de login */
	private static final String LOGIN_ACTION = "/loginUser.xhtml";
	/** Security actions exception */
	private Collection<String> securityActionsException;
		

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		UserView userView = null;		
		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;
		HttpSession httpSession =  null;
		String requestUri = null;		
		String contextPath = null;
		
		httpServletRequest = (HttpServletRequest)request;
		httpServletResponse = (HttpServletResponse)response;
		contextPath = httpServletRequest.getContextPath();		
		httpSession =  httpServletRequest.getSession();
		userView = (UserView)httpSession.getAttribute(USER_SESSION_NAME);
		requestUri= httpServletRequest.getServletPath();
		if(requestUri.startsWith("/javax.faces.resource")){
			requestUri = "/javax.faces.resource";
		}
		if( !securityActionsException.contains(requestUri) ){
			if(  userView == null ){
				httpServletResponse.sendRedirect(new StringBuffer(contextPath).append(LOGOUT_ACTION).toString());
			}else{				
				chain.doFilter(request, response);
			}
		}else{
			if( requestUri.equals(MAIN_ACTION) ){
				if(  userView == null ){
					httpServletResponse.sendRedirect(new StringBuffer(contextPath).append(LOGOUT_ACTION).toString());
				}				
			}
			chain.doFilter(request, response);
		}

	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		securityActionsException = new ArrayList<String>(7);
		securityActionsException.add(LOGIN_ACTION);
		securityActionsException.add(LOGOUT_ACTION);
		securityActionsException.add(ERROR_ACTION);
		securityActionsException.add(ERROR_404_ACTION);
		securityActionsException.add(ERROR_500_ACTION);
		securityActionsException.add(MAIN_ACTION);
		securityActionsException.add("/javax.faces.resource");

	}

	public void destroy() {

	}

}
