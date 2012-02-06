package com.condominium.security.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.security.dao.SecurityDAO;
import com.condominium.security.exception.SecurityAuthorizationException;

@Repository
public class SecurityDAOImpl extends GenericDAO implements SecurityDAO {

	/**
	 * @param userId Id del usuario a validar.
	 * @param module Nombre del modulo ejemplo: TERRAZA, INGRESOS, EGRESOS
	 */
	public boolean isModuleAutorized(int userId, String module) throws SecurityAuthorizationException {
		boolean result = false;
		StringBuilder sql = new StringBuilder(0);
		sql.append("SELECT COUNT(*) FROM PERMISO_USUARIO PU ");
		sql.append("INNER JOIN PERMISO P ON P.PER_ID = PU.PER_ID ");
		sql.append("INNER JOIN TIPO_PERMISO TP ON TP.TP_ID = P.TP_ID ");
		sql.append("WHERE PU.USR_ID = ? AND TP.TP_DESC = ? AND P.PER_DESC = 'MOD' ");
		try{
			int count =	jdbcTemplate.queryForInt(sql.toString(), new Object[]{userId, module});
			if(count > 0){
				result = true;
			}
		}catch(DataAccessException dae){
			SecurityAuthorizationException sException = new SecurityAuthorizationException(dae, SecurityAuthorizationException.LAYER_DAO, SecurityAuthorizationException.ACTION_SELECT);
			throw sException;
		}		
		return result;
	}

	/**
	 * @param userId Id del usuario a validar.
	 * @param module Nombre del modulo INGRESOS, EGRESOS, TERRAZA
	 * @param subModule Nombre del sub Modulo DEL, EDT, ADD
	 */
	public boolean isSubModuleAutorized(int userId, String module, String subModule) throws SecurityAuthorizationException {
		boolean result = false;
		StringBuilder sql = new StringBuilder(0);
		sql.append("SELECT COUNT(*) FROM PERMISO_USUARIO PU ");
		sql.append("INNER JOIN PERMISO P ON P.PER_ID = PU.PER_ID ");
		sql.append("INNER JOIN TIPO_PERMISO TP ON TP.TP_ID = P.TP_ID ");
		sql.append("WHERE PU.USR_ID = ? AND TP.TP_DESC = ? AND P.PER_DESC = ? ");
		try{
			int count =	jdbcTemplate.queryForInt(sql.toString(), new Object[]{userId, module, subModule});
			if(count > 0){
				result = true;
			}
		}catch(DataAccessException dae){
			SecurityAuthorizationException sException = new SecurityAuthorizationException(dae, SecurityAuthorizationException.LAYER_DAO, SecurityAuthorizationException.ACTION_SELECT);
			throw sException;
		}		
		return result;
	}

	public boolean isEntityAutorized(int userId, String subModule,
			String permission) throws SecurityAuthorizationException {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
