package com.condominium.log.dao;

import com.condominium.log.exception.LogException;
import com.condominium.user.view.UserView;

public interface LogDAO {

	public void addSystemEntry(UserView userView) throws LogException;
	
	public void addSystemExit(UserView userView) throws LogException;
}
