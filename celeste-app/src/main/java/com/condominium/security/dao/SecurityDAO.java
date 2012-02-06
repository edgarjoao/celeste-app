package com.condominium.security.dao;

import com.condominium.security.exception.SecurityAuthorizationException;

public interface SecurityDAO {

	public boolean isModuleAutorized(int userId, String module) throws SecurityAuthorizationException;
	
	public boolean isSubModuleAutorized(int userId, String module, String subModule) throws SecurityAuthorizationException;
	
	public boolean isEntityAutorized(int userId, String subModule, String permission) throws SecurityAuthorizationException;
}
