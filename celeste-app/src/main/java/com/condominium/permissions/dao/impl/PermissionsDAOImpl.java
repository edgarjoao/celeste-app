package com.condominium.permissions.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.permissions.dao.PermissionsDAO;
import com.condominium.permissions.dto.PermissionsDTO;
import com.condominium.permissions.dto.PermissionsModuleDTO;
import com.condominium.permissions.exception.PermissionsException;
/**
 * 
 * @author rioslore
 *
 */
@Repository
public class PermissionsDAOImpl extends GenericDAO implements PermissionsDAO {

	private static final Logger log = Logger.getLogger(PermissionsDAOImpl.class);
	
	public List<PermissionsModuleDTO> getPermissionsModuleList() throws PermissionsException {
		List<PermissionsModuleDTO> dtosList = null;		
		try{
			dtosList = this.jdbcTemplate.query("SELECT TP_ID, TP_DESCRIPCION, TP_DESC FROM TIPO_PERMISO", new RowMapper<PermissionsModuleDTO>() {	
				public PermissionsModuleDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					PermissionsModuleDTO dto = new PermissionsModuleDTO();
					dto.setTpId(rs.getInt("TP_ID"));
					dto.setTpDescription(rs.getString("TP_DESCRIPCION"));
					dto.setShortDesc(rs.getString("TP_DESC"));
					return dto;
				}
			});	
		}catch(Exception exception){
			log.error(exception);
			PermissionsException permissionsException = new PermissionsException(exception, PermissionsException.LAYER_DAO, PermissionsException.ACTION_SELECT);
			throw permissionsException;
		}		
		return dtosList;
	}
	
	public List<PermissionsDTO> getPermissionsDTOList(int tpId) throws PermissionsException {
		List<PermissionsDTO> dtosList = null;
		StringBuilder sql = new StringBuilder(0);
		sql.append("SELECT P.PER_ID, P.PER_DESCRIPCION, P.PER_DESC, P.TP_ID FROM PERMISO P ");
		sql.append("INNER JOIN TIPO_PERMISO TP ");
		sql.append("ON TP.TP_ID = P.TP_ID ");
		sql.append("WHERE TP.TP_ID = ?");
		try{
			dtosList = this.jdbcTemplate.query(sql.toString(), new Object[]{tpId}, new RowMapper<PermissionsDTO>() {	
				public PermissionsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					PermissionsDTO dto = new PermissionsDTO();
					dto.setPerId(rs.getInt("P.PER_ID"));
					dto.setPerDescription(rs.getString("P.PER_DESCRIPCION"));
					dto.setShortName(rs.getString("P.PER_DESC"));
					dto.setTpId(rs.getInt("P.TP_ID"));
					return dto;
				}
			});	
		}catch(Exception exception){
			log.error(exception);
			PermissionsException permissionsException = new PermissionsException(exception, PermissionsException.LAYER_DAO, PermissionsException.ACTION_SELECT);
			throw permissionsException;
		}		
		return dtosList;
	}
	
	public boolean hasPermission(int usrId, int perId) throws PermissionsException {
		boolean res = false;
		try{
		 int count = this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PERMISO_USUARIO WHERE USR_ID = ? AND PER_ID = ?", new Object[]{usrId, perId});
		 if(count > 0){
			 res = true;
		 }
		}catch(DataAccessException dataAccessException){
			res = false;
		}
		return res;
	}	

	public void addUserPermissions(int userId, int perId) throws PermissionsException {			
		try{
			this.jdbcTemplate.update("INSERT INTO PERMISO_USUARIO (USR_ID, PER_ID, PU_FECHA) VALUES(?,?,NOW())", new Object[]{userId, perId});
		}catch(DataAccessException dataAccessException){
			log.error(dataAccessException);
			PermissionsException permissionsException = new PermissionsException(dataAccessException, PermissionsException.LAYER_DAO, PermissionsException.ACTION_INSERT);
			throw permissionsException;
		}
	}

	public void deletePermissions(int userId) throws PermissionsException {
		try{
			this.jdbcTemplate.update("DELETE FROM PERMISO_USUARIO WHERE USR_ID = ?", new Object[]{userId});
		}catch(DataAccessException dataAccessException){
			log.error(dataAccessException);
			PermissionsException permissionsException = new PermissionsException(dataAccessException, PermissionsException.LAYER_DAO, PermissionsException.ACTION_DELETE);
			throw permissionsException;
		}
	}
	
}
