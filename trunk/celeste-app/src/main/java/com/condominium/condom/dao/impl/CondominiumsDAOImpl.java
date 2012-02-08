package com.condominium.condom.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.condom.dao.CondominiumsDAO;
import com.condominium.condom.dto.CondominiumsDTO;
import com.condominium.user.dto.UserDTO;
import com.condominium.user.exception.UserException;
/**
 * 
 * @author rioslore
 *
 */
@Repository
public class CondominiumsDAOImpl extends GenericDAO implements CondominiumsDAO {

	public List<CondominiumsDTO> getCondominiumsList() throws UserException {
		List<CondominiumsDTO> condominiumsList = null;
		StringBuilder sql = new StringBuilder(0);
		sql.append("SELECT C.COND_ID, C.COND_NUM_CASA, C.CON_ID, C.USR_ID, T.TC_DESCRIPCION, U.USR_ID, U.USR_NOMBRE, U.USR_APATERNO, U.USR_AMATERNO, U.USR_EMAIL, U.USR_TEL_CASA, U.USR_TEL_CELULAR FROM CONDOMINOS C, TIPO_CONDOMINO T, USUARIO U ");
		sql.append(" WHERE C.TC_ID = T.TC_ID AND C.USR_ID = U.USR_ID");
		try{
			
			condominiumsList = this.jdbcTemplate.query(sql.toString(), new RowMapper<CondominiumsDTO>() {

				public CondominiumsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					CondominiumsDTO dto = new CondominiumsDTO();
					dto.setId(rs.getInt("C.COND_ID"));					
					dto.setHouseNumber(rs.getInt("C.COND_NUM_CASA"));
					dto.setCondominiumId(rs.getInt("C.CON_ID"));
					dto.setTypeDescription(rs.getString("T.TC_DESCRIPCION"));
					UserDTO userDTO = new UserDTO();
					userDTO.setUserId(rs.getInt("U.USR_ID"));
					userDTO.setNombre(rs.getString("U.USR_NOMBRE"));
					userDTO.setApaterno((rs.getString("U.USR_APATERNO")));
					userDTO.setAmaterno((rs.getString("U.USR_AMATERNO")));										
					userDTO.setEmail((rs.getString("U.USR_EMAIL")));
					userDTO.setTelCasa(rs.getString("U.USR_TEL_CASA"));
					userDTO.setTelCelular(rs.getString("U.USR_TEL_CELULAR"));
					dto.setUserDTO(userDTO);
					return dto;
				}
			});
			
		}catch(Exception exception){
			UserException userException = new UserException(exception, UserException.LAYER_DAO, UserException.ACTION_SELECT);
			throw userException;
		}			
		return condominiumsList;
	}

}
