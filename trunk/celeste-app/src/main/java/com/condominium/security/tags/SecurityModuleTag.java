package com.condominium.security.tags;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributeException;
import javax.faces.view.facelets.TagHandler;
/**
 * Security Module Tag
 * @author joao
 *
 */
public class SecurityModuleTag extends TagHandler{

	private final TagAttribute module;
	private final TagAttribute permission;
	
	public SecurityModuleTag(ComponentConfig componentConfig) {
		super(componentConfig);
		this.module = getAttribute("modulo");
		this.permission = getAttribute("permiso");
		if (this.module == null){
			throw new TagAttributeException(this.module,
					"The 'module' attribute has to be specified!");
		}
		if(this.permission == null){
			throw new TagAttributeException(this.permission,
					"The 'permissions' must be specified!");
		}
	}
	

	public void apply(FaceletContext faceletContext, UIComponent uiComponent) throws IOException {
		if (this.module == null){
			throw new TagAttributeException(this.module,
					"The 'module' attribute has to be specified!");
		}
		if(this.permission == null){
			throw new TagAttributeException(this.permission,
					"The 'permissions' must be specified!");
		}
		
		String modulo = this.module.getValue(faceletContext);
		String permiso = this.module.getValue(faceletContext);
		
		
		this.nextHandler.apply(faceletContext, uiComponent);
	}

}
