package com.condominium.user.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
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

	private static final Logger log = Logger.getLogger(UserDAOImpl.class);
	
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
					dto.setTelCasa(rs.getString("USR_TEL_CASA"));
					dto.setTelCelular(rs.getString("USR_TEL_CELULAR"));
					dto.setEmail(rs.getString("USR_EMAIL"));				
					return dto;
				}
			});
		}catch (Exception exception) {
			UserException userException = new  UserException(exception, UserException.LAYER_DAO, UserException.ACTION_SELECT);
			log.info(exception);
			throw userException;
		}
		return userDTO;
	}

	public void insertUser(UserDTO dto) throws UserException {		
		try{
			this.jdbcTemplate.update("INSERT INTO USUARIO () VALUES(?)", new Object[]{dto.getUsername()});
		}catch(Exception exception){
			UserException userException = new  UserException(exception, UserException.LAYER_DAO, UserException.ACTION_SELECT);
			log.info(exception);
			throw userException;
		}			
	}

	public UserDTO getUserById(int userId) throws UserException {
		UserDTO userDTO = null;
		try{
			userDTO = this.jdbcTemplate.queryForObject("SELECT * FROM USUARIO u, ROL r WHERE u.ROL_ID = r.ROL_ID AND USR_ID = ? ", new Object[]{userId}, new RowMapper<UserDTO>() {
	
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
					dto.setTelCasa(rs.getString("USR_TEL_CASA"));
					dto.setTelCelular(rs.getString("USR_TEL_CELULAR"));
					dto.setEmail(rs.getString("USR_EMAIL"));				
					return dto;
				}
			});
		}catch (Exception exception) {
			UserException userException = new  UserException(exception, UserException.LAYER_DAO, UserException.ACTION_SELECT);
			log.info(exception);
			throw userException;
		}
		return userDTO;
	}

	public void editUser(UserDTO userDTO) throws UserException {
		try{
			this.jdbcTemplate.update("UPDATE USUARIO SET USR_PASSWORD = ?, USR_NOMBRE = ?, USR_APATERNO = ?, USR_AMATERNO = ?, USR_TEL_CASA = ?, USR_TEL_CELULAR = ?, USR_EMAIL = ?, ROL_ID = ? WHERE USR_ID = ?", 
									new Object[]{userDTO.getPassword(), userDTO.getNombre(), userDTO.getApaterno(), userDTO.getAmaterno(), userDTO.getTelCasa(), userDTO.getTelCelular(), userDTO.getEmail(), userDTO.getRoleId(), userDTO.getUserId()});
		}catch(DataAccessException dataAccessException){
			UserException userException = new UserException(dataAccessException, UserException.LAYER_DAO, UserException.ACTION_UPDATE);
			throw userException;
		}		
	}
	
	public List<SelectItem> getRoles() throws UserException {
		List<SelectItem> roles = new ArrayList<SelectItem>(0);
		try{
			roles = this.jdbcTemplate.query("SELECT ROL_ID, ROL_DESCRIPCION FROM ROL", new RowMapper<SelectItem>() {
				public SelectItem mapRow(ResultSet rs, int rowNum) throws SQLException {
					SelectItem item = new SelectItem(rs.getString("ROL_ID"), rs.getString("ROL_DESCRIPCION"));
					return item;
				}
			});			
		}catch(DataAccessException dataAccessException){
			UserException userException = new UserException(dataAccessException, UserException.LAYER_DAO, UserException.ACTION_SELECT);
			throw userException;
		}		
		return roles;
	}

}
