package com.condominium.security.tags;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributeException;
import javax.faces.view.facelets.TagHandler;

import org.apache.log4j.Logger;

import com.condominium.common.utils.JSFUtil;
import com.condominium.common.utils.SpringContextUtil;
import com.condominium.security.dao.SecurityDAO;
import com.condominium.security.dao.impl.SecurityDAOImpl;
import com.condominium.security.exception.SecurityAuthorizationException;
import com.condominium.user.view.UserView;
/**
 * Modulo de seguridad
 * 
 * <sec:submodulo modulo="INGRESOS" submodulo="DEL"> html </sec:submodulo>
 * 
 * 
 * @author rioslore
 *
 */
public class SecuritySubModuleTag extends TagHandler {

	private static final Logger log = Logger.getLogger(SecuritySubModuleTag.class);
	private final TagAttribute modulo;
	private final TagAttribute submodulo;
		
	public void apply(FaceletContext faceletContext, UIComponent uiComponent) throws IOException, FacesException, FaceletException, ELException {
		if (this.modulo == null){
			throw new TagAttributeException(this.modulo,
					"The 'modulo' attribute has to be specified.");
		}
		if (this.submodulo == null){
			throw new TagAttributeException(this.modulo,
					"The 'submodulo' attribute has to be specified.");
		}
		String moduloArg = this.modulo.getValue(faceletContext);
		String submoduloArg = this.submodulo.getValue(faceletContext);
		UserView userView = JSFUtil.getSessionAttribute(UserView.class, "userView");
		if(userView != null){
			SecurityDAO securityDAO = (SecurityDAO) SpringContextUtil.findBean(faceletContext, SecurityDAOImpl.class);							
			try {
				boolean isAutorized = securityDAO.isSubModuleAutorized(userView.getUserId(), moduloArg, submoduloArg);
				if(isAutorized){
					this.nextHandler.apply(faceletContext, uiComponent);					
				}
			} catch (SecurityAuthorizationException e) {
				log.error(e);
			}					
		}		
	}
		
	public SecuritySubModuleTag(ComponentConfig componentConfig) {
		super(componentConfig);
		this.modulo =  getRequiredAttribute("modulo");
		this.submodulo = getRequiredAttribute("submodulo");
		if (this.modulo == null){
			throw new TagAttributeException(this.modulo,
					"The 'modulo' attribute has to be specified.");
		}
		if (this.submodulo == null){
			throw new TagAttributeException(this.submodulo,
					"The 'submodulo' attribute has to be specified.");
		}
	}
}
