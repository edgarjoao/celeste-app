package com.condominium.log.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.log.dao.LogDAO;
import com.condominium.log.exception.LogException;
import com.condominium.user.view.UserView;

@Repository("LogDAOImpl")
public class LogDAOImpl extends GenericDAO implements LogDAO {
	
	
	public void addSystemEntry(UserView userView) throws LogException {		
		try{
			StringBuilder nombre = new StringBuilder(0);
			nombre.append(userView.getNombre()).append(" ");
			nombre.append(userView.getApaterno()).append(" ");
			nombre.append(userView.getAmaterno()).append(".");
			this.jdbcTemplate.update("INSERT INTO LOG_SISTEMA (LOG_NOMBRE_USUARIO, LOG_FECHA, LOG_STATUS) VALUES (?, NOW(),'Ingreso')", new Object[]{nombre.toString()});
		}catch(DataAccessException exception){
			LogException receiptsException = new LogException(exception, LogException.LAYER_DAO, LogException.ACTION_INSERT);
			throw receiptsException;
		}
	}

	public void addSystemExit(UserView userView) throws LogException {
		try{
			StringBuilder nombre = new StringBuilder(0);
			nombre.append(userView.getNombre()).append(" ");
			nombre.append(userView.getApaterno()).append(" ");
			nombre.append(userView.getAmaterno()).append(".");
			this.jdbcTemplate.update("INSERT INTO LOG_SISTEMA (LOG_NOMBRE_USUARIO, LOG_FECHA, LOG_STATUS) VALUES (?, NOW(),'Salida')", new Object[]{nombre.toString()});
		}catch(DataAccessException exception){
			LogException receiptsException = new LogException(exception, LogException.LAYER_DAO, LogException.ACTION_INSERT);
			throw receiptsException;
		}
	}

}
