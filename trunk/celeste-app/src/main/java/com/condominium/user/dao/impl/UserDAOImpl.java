package com.condominium.user.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.user.dao.UserDAO;
import com.condominium.user.dto.UserDTO;
import com.condominium.user.exception.UserException;
/**
 * 
 * @author rioslore
 *
 */
@Repository
public class UserDAOImpl extends GenericDAO implements UserDAO {

	public UserDTO loginUser(String username, String password) throws UserException {
		UserDTO userDTO = null;
		try{
			userDTO = this.jdbcTemplate.queryForObject("SELECT * FROM USUARIO u, ROL r WHERE u.ROL_ID = r.ROL_ID AND USR_USERNAME = ? AND USR_PASSWORD = ?", new Object[]{username, password}, new RowMapper<UserDTO>() {
	
				public UserDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					UserDTO dto = new UserDTO();
					dto.setUserId(rs.getInt("USR_ID"));
					dto.setUsername(rs.getString("USR_USERNAME"));
					dto.setPassword(rs.getString("USR_PASSWORD"));
					dto.setNombre(rs.getString("USR_NOMBRE"));
					dto.setApaterno(rs.getString("USR_APATERNO"));
					dto.setAmaterno(rs.getString("USR_AMATERNO"));
					dto.setRoleId(rs.getInt("ROL_ID"));
					dto.setRolDescription(rs.getString("ROL_DESCRIPCION"));
					dto.setTelCasa(rs.getInt("USR_TEL_CASA"));
					dto.setTelCelular(rs.getInt("USR_TEL_CELULAR"));
					dto.setEmail(rs.getString("USR_EMAIL"));				
					return dto;
				}
			});
		}catch (Exception exception) {
			UserException userException = new  UserException(exception, UserException.LAYER_DAO, UserException.ACTION_SELECT);
			throw userException;
		}
		return userDTO;
	}

	public void insertUser(UserDTO dto) throws UserException {		
		try{
			this.jdbcTemplate.update("INSERT INTO USUARIO () VALUES(?)", new Object[]{dto.getUsername()});
		}catch(Exception exception){
			UserException userException = new  UserException(exception, UserException.LAYER_DAO, UserException.ACTION_SELECT);
			throw userException;
		}			
	}

}
