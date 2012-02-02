package com.condominium.receipts.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.condom.dto.CondominiumsDTO;
import com.condominium.events.dto.EventsDTO;
import com.condominium.receipts.dao.ReceiptsDAO;
import com.condominium.receipts.dto.ReceiptsDTO;
import com.condominium.receipts.dto.ReceiptsItemDTO;
import com.condominium.receipts.dto.ReceiptsReportDTO;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.user.dto.UserDTO;
/**
 * 
 * @author rioslore
 *
 */
@Repository
public class ReceiptsDAOImpl extends GenericDAO implements ReceiptsDAO {
	
	private static final Logger log = Logger.getLogger(ReceiptsDAOImpl.class);

	public List<ReceiptsDTO> getReceiptsDTOList() throws ReceiptsException {
		List<ReceiptsDTO> receiptsDTOList = null;
		StringBuilder sql = new StringBuilder(0);
		try{
			sql.append("SELECT I.ING_ID, I.ING_FECHA_INGRESO, I.ING_IMPORTE, I.ING_DESCUENTO, I.ING_COMENTARIOS, C.COND_NUM_CASA, CI.CATI_DESCRIPCION ");
			sql.append("FROM INGRESOS I, CONDOMINOS C, CATALOGO_INGRESOS CI WHERE I.COND_ID = C.COND_ID AND CI.CATI_ID = I.CATI_ID ");
			receiptsDTOList = this.jdbcTemplate.query(sql.toString(), new RowMapper<ReceiptsDTO>() {
	
				public ReceiptsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ReceiptsDTO dto = new ReceiptsDTO();
					dto.setId(rs.getInt("I.ING_ID"));
					dto.setDate(rs.getTimestamp("I.ING_FECHA_INGRESO"));
					dto.setAmount(rs.getDouble("I.ING_IMPORTE"));
					dto.setDescount(rs.getDouble("I.ING_DESCUENTO"));
					dto.setComments(rs.getString("I.ING_COMENTARIOS"));
					CondominiumsDTO condDTO = new CondominiumsDTO();
					condDTO.setHouseNumber(rs.getInt("C.COND_NUM_CASA"));
					ReceiptsItemDTO itemDTO = new ReceiptsItemDTO();
					itemDTO.setDescription(rs.getString("CI.CATI_DESCRIPCION"));
					dto.setReceiptsItemDTO(itemDTO);
					dto.setCondominiumsDTO(condDTO);
					return dto;
				}
			});
		}catch(Exception exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_SELECT);
			throw receiptsException;
		}		
		return receiptsDTOList;
	}

	public List<ReceiptsItemDTO> getReceiptsItemDTOList() throws ReceiptsException {
		List<ReceiptsItemDTO> itemDTOList = null;		
		try{
			itemDTOList = this.jdbcTemplate.query("SELECT CATI_ID, CATI_DESCRIPCION FROM CATALOGO_INGRESOS", new RowMapper<ReceiptsItemDTO>() {
	
				public ReceiptsItemDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ReceiptsItemDTO dto = new ReceiptsItemDTO();
					dto.setId(rs.getInt("CATI_ID"));
					dto.setDescription(rs.getString("CATI_DESCRIPCION"));
					return dto;
				}
			});
		}catch (Exception exception) {
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_SELECT);
			throw receiptsException;
		}
		
		return itemDTOList;
	}
	
	public void insertReceiptDTO(ReceiptsDTO dto) throws ReceiptsException{
		try{
			this.jdbcTemplate.update("INSERT INTO INGRESOS (ING_FECHA_ALTA, ING_FECHA_INGRESO, ING_IMPORTE, ING_DESCUENTO, ING_COMENTARIOS, CATI_ID, COND_ID, USR_ID) VALUES (NOW(),?,?,?,?,?,?,?)", 
								new Object[]{dto.getDate(), dto.getAmount(), dto.getDescount(), dto.getComments(), dto.getCatId(), dto.getCondId(), dto.getUserId()});
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_INSERT);
			throw receiptsException;
		}		
	}
	
	public void updateReceiptDTO(ReceiptsDTO dto) throws ReceiptsException{
		try{
			this.jdbcTemplate.update("UPDATE INGRESOS set ING_FECHA_INGRESO = ?, ING_IMPORTE = ? , ING_DESCUENTO = ?, ING_COMENTARIOS = ?, CATI_ID = ?, COND_ID = ? WHERE ING_ID = ?", 
								new Object[]{dto.getDate(), dto.getAmount(), dto.getDescount(), dto.getComments(), dto.getCatId(), dto.getCondId(), dto.getId()});
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_UPDATE);
			throw receiptsException;
		}		
	}
	
	public void deleteReceiptDTO(int id) throws ReceiptsException{
		try{
			this.jdbcTemplate.update("DELETE FROM INGRESOS WHERE ING_ID = ?", new Object[]{id});
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_DELETE);
			throw receiptsException;
		}	
	}

	public void insertReceiptItemDTO(ReceiptsItemDTO dto) throws ReceiptsException {		
		try{
			this.jdbcTemplate.update("INSERT INTO CATALOGO_INGRESOS (CATI_DESCRIPCION) VALUES (?)", new Object[]{dto.getDescription()});
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_INSERT);
			throw receiptsException;
		}	
	}

	public void updateReceiptItemDTO(ReceiptsItemDTO dto) throws ReceiptsException {
		try{
			this.jdbcTemplate.update("INSERT INTO CATALOGO_INGRESOS (CATI_DESCRIPCION) VALUES (?)", new Object[]{dto.getDescription()});
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_UPDATE);
			throw receiptsException;
		}
	}

	public void deleteReceiptItemDTO(int id) throws ReceiptsException {
		try{
			this.jdbcTemplate.update("DELETE FROM CATALOGO_INGRESOS WHERE CATI_ID = ?", new Object[]{id});
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_DELETE);
			throw receiptsException;
		}
	}

	public List<ReceiptsDTO> getSearchReceiptsDTOList(int month, int year, int catId, int condId) throws ReceiptsException {
		List<ReceiptsDTO> receiptsDTOList = null;
		StringBuilder sql = new StringBuilder(0);			
		try{			
			sql.append("SELECT I.ING_ID, I.ING_FECHA_INGRESO, I.ING_IMPORTE, I.ING_DESCUENTO, I.ING_COMENTARIOS, C.COND_ID, C.COND_NUM_CASA, CI.CATI_DESCRIPCION, CI.CATI_ID ");
			sql.append("FROM INGRESOS I, CATALOGO_INGRESOS CI, CONDOMINOS C ");
			sql.append("WHERE I.COND_ID = C.COND_ID AND CI.CATI_ID = I.CATI_ID ");
			sql.append("AND MONTH(ING_FECHA_INGRESO) = ? AND YEAR(ING_FECHA_INGRESO)= ? ");			
			
			Object[] parameters = null;
			if(( catId < 0 ) && ( condId < 0 )){
				parameters =  new Object[2];
				parameters[0] = month;
				parameters[1] = year;
			}
			if(( catId > 0 ) && ( condId > 0 )){
				parameters =  new Object[4];
				parameters[0] = month;
				parameters[1] = year;
				parameters[2] = catId;
				parameters[3] = condId;
				sql.append("AND I.CATI_ID = ? ");
				sql.append("AND C.COND_ID = ? ");
			}
			if(( catId > 0 ) && ( condId < 0 )){
				parameters =  new Object[3];
				parameters[0] = month;
				parameters[1] = year;
				parameters[2] = catId;
				sql.append("AND I.CATI_ID = ? ");
			}
			if(( catId < 0 ) && ( condId > 0 )){
				parameters =  new Object[3];
				parameters[0] = month;
				parameters[1] = year;				
				parameters[2] = condId;				
				sql.append("AND C.COND_ID = ? ");
			}
					
			sql.append(" ORDER BY C.COND_NUM_CASA DESC ");
			receiptsDTOList = this.jdbcTemplate.query(sql.toString(), parameters, new RowMapper<ReceiptsDTO>() {
	
				public ReceiptsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ReceiptsDTO dto = new ReceiptsDTO();
					dto.setId(rs.getInt("I.ING_ID"));
					dto.setDate(rs.getTimestamp("I.ING_FECHA_INGRESO"));
					dto.setAmount(rs.getDouble("I.ING_IMPORTE"));
					dto.setDescount(rs.getDouble("I.ING_DESCUENTO"));
					dto.setComments(rs.getString("I.ING_COMENTARIOS"));
					CondominiumsDTO condDTO = new CondominiumsDTO();
					condDTO.setId(rs.getInt("C.COND_ID"));
					condDTO.setHouseNumber(rs.getInt("C.COND_NUM_CASA"));
					ReceiptsItemDTO itemDTO = new ReceiptsItemDTO();
					itemDTO.setDescription(rs.getString("CI.CATI_DESCRIPCION"));
					itemDTO.setId(rs.getInt("CI.CATI_ID"));
					dto.setReceiptsItemDTO(itemDTO);
					dto.setCondominiumsDTO(condDTO);
					return dto;
				}
			});
		}catch(Exception exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_SELECT);
			throw receiptsException;
		}		
		return receiptsDTOList;
		
	}

	public boolean verifyIfExistBeforeInsert(ReceiptsDTO dto) throws ReceiptsException {		
		boolean result = false;
		String sql = "SELECT COUNT(*) FROM INGRESOS WHERE COND_ID = :condId AND CATI_ID = :catId AND ING_FECHA_INGRESO = :fechaAlta";
		Map parameters = new HashMap();
		parameters.put("condId", dto.getCondId());
		parameters.put("catId", dto.getCatId());
		parameters.put("fechaAlta", dto.getDate());
		int count = this.namedParameterJdbcTemplate.queryForInt(sql, parameters);
		if(count > 0){
			result = true;
		}
		return result;
	}

	public List<ReceiptsReportDTO> receiptsReportByMonth(int month, int year) throws ReceiptsException {
		List<ReceiptsReportDTO> list = null;
		StringBuilder sql = new StringBuilder(0);
		try{
			sql.append("SELECT MONTH(I.ING_FECHA_INGRESO) AS MES, CI.CATI_DESCRIPCION, SUM(I.ING_IMPORTE) AS IMPORTE FROM INGRESOS I  ");
			sql.append("INNER JOIN CATALOGO_INGRESOS CI ON I.CATI_ID = CI.CATI_ID ");
			sql.append("WHERE MONTH(ING_FECHA_ALTA) = ? AND YEAR(ING_FECHA_ALTA) = ? ");
			sql.append("GROUP BY MONTH(ING_FECHA_INGRESO), CI.CATI_ID ");
			list = this.jdbcTemplate.query(sql.toString(), new Object[]{month, year}, new RowMapper<ReceiptsReportDTO>() {				
				public ReceiptsReportDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ReceiptsReportDTO dto = new ReceiptsReportDTO();
					dto.setMonth(rs.getInt("MES"));
					dto.setDescription(rs.getString("CI.CATI_DESCRIPCION"));
					dto.setAmount(rs.getDouble("IMPORTE"));
					return dto;
				}
			});			
		}catch(Exception exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_SELECT);
			throw receiptsException;
		}		
		return list;
	}

	public double totalPrevious(int month, int year) throws ReceiptsException {		
		try{
			/*StringBuilder receiptsSQL = new StringBuilder(0);
			receiptsSQL.append("SELECT SUM(ING_IMPORTE) AS IMPORTE_INGRESOS FROM INGRESOS ");
			receiptsSQL.append("WHERE MONTH(ING_FECHA_ALTA) = ? AND YEAR(ING_FECHA_ALTA) = ? ");	
			Long receipt = this.jdbcTemplate.queryForLong(receiptsSQL.toString(), new Object[]{month, year});
			double ingreso = receipt.doubleValue();
			
			StringBuilder expensesSQL = new StringBuilder(0);
			expensesSQL.append("SELECT SUM(EGR_IMPORTE) FROM EGRESOS WHERE ");
			expensesSQL.append("MONTH(EGR_FECHA_EGRESO) = ? AND YEAR(EGR_FECHA_EGRESO) = ? ");			
			Long expenses = this.jdbcTemplate.queryForLong(expensesSQL.toString(), new Object[]{month, year});
			double egreso = expenses.doubleValue();			
			return ingreso - egreso;*/		
			String totalSQL = "SELECT AC_MONTO FROM ACUMULADO WHERE AC_MES = ? AND AC_ANIO = ?";
			Long expenses = this.jdbcTemplate.queryForLong(totalSQL, new Object[]{month, year});
			return expenses.doubleValue();
		}catch(EmptyResultDataAccessException erdae){
			//No se encontraron resultados
			return 0;
		}catch(Exception exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_SELECT);
			throw receiptsException;
		}		
	}

	public List<CondominiumsDTO> getDebtsOfTheMonth(int month, int year) throws ReceiptsException {
		List<CondominiumsDTO> list = null;
		StringBuilder sql = new StringBuilder(0);
		try{
			sql.append("SELECT C.COND_NUM_CASA, USR_NOMBRE, USR_APATERNO, USR_AMATERNO, ING_IMPORTE, CATI_DESCRIPCION  ");
			sql.append("FROM CONDOMINOS C LEFT JOIN ( SELECT ING_IMPORTE, CATI_DESCRIPCION, I.COND_ID  ");
			sql.append("FROM INGRESOS I INNER JOIN CATALOGO_INGRESOS CI ON I.CATI_ID = CI.CATI_ID  ");
			sql.append("WHERE MONTH(ING_FECHA_INGRESO) = ? AND YEAR(ING_FECHA_INGRESO) = ? )  ");
			sql.append("I USING (COND_ID) JOIN USUARIO USING (USR_ID)  ");
			sql.append("ORDER BY COND_NUM_CASA ASC  ");
			
			list = this.jdbcTemplate.query(sql.toString(), new Object[]{month, year}, new RowMapper<CondominiumsDTO>() {				
				public CondominiumsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					CondominiumsDTO dto = new CondominiumsDTO();
					dto.setHouseNumber(rs.getInt("C.COND_NUM_CASA"));
					UserDTO userDTO = new UserDTO();
					userDTO.setNombre(rs.getString("USR_NOMBRE"));
					userDTO.setApaterno(rs.getString("USR_APATERNO"));
					userDTO.setAmaterno(rs.getString("USR_APATERNO"));
					dto.setUserDTO(userDTO);
					dto.setDebt(rs.getDouble("ING_IMPORTE"));
					dto.setDebDescription(rs.getString("CATI_DESCRIPCION"));
					return dto;
				}
			});			
		}catch(Exception exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_SELECT);
			throw receiptsException;
		}		
		return list;
	}
	
	public List<EventsDTO> getEventsDTOs(int month, int year) throws ReceiptsException {
		List<EventsDTO> list = null;
		StringBuilder sql = new StringBuilder(0);
		try{
			sql.append("SELECT T.TER_ID, T.TER_DESCRIPCION, T.TER_FECHA, T.TER_STATUS, T.COND_ID, C.COND_NUM_CASA, U.USR_NOMBRE, U.USR_APATERNO, U.USR_AMATERNO, U.USR_TEL_CASA, U.USR_TEL_CELULAR, U.USR_EMAIL FROM TERRAZA T ");
			sql.append("INNER JOIN CONDOMINOS C ON T.COND_ID = C.COND_ID ");
			sql.append("INNER JOIN USUARIO U ON U.USR_ID = C.USR_ID ");
			sql.append("WHERE MONTH(T.TER_FECHA) = ? AND YEAR(T.TER_FECHA) = ? ");
			sql.append("ORDER BY T.TER_FECHA DESC ");
			list = this.jdbcTemplate.query(sql.toString(), new Object[]{month, year}, new RowMapper<EventsDTO>() {
				
				public EventsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					EventsDTO dto = new EventsDTO();
					dto.setId(rs.getInt("T.TER_ID"));
					dto.setDescription(rs.getString("T.TER_DESCRIPCION"));
					dto.setDate(rs.getDate("T.TER_FECHA"));
					dto.setStatus(rs.getString("T.TER_STATUS"));
					CondominiumsDTO condominiumsDTO = new CondominiumsDTO();
					condominiumsDTO.setCondominiumId(rs.getInt("T.COND_ID"));
					condominiumsDTO.setHouseNumber(rs.getInt("C.COND_NUM_CASA"));
					dto.setCondominiumsDTO(condominiumsDTO);
					UserDTO userDTO = new UserDTO();
					userDTO.setNombre(rs.getString("U.USR_NOMBRE"));
					userDTO.setApaterno(rs.getString("U.USR_APATERNO"));
					userDTO.setAmaterno(rs.getString("U.USR_AMATERNO"));
					userDTO.setTelCasa(rs.getInt("U.USR_TEL_CASA"));
					userDTO.setTelCelular(rs.getInt("U.USR_TEL_CELULAR"));
					dto.setUserDTO(userDTO);					
					return dto;
				}
			});			
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException expensesException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_SELECT);
			throw expensesException;
		}		
		return list;
	}

	public void insertEventDTO(EventsDTO dto) throws ReceiptsException {
		try{
			this.jdbcTemplate.update("INSERT INTO TERRAZA (TER_DESCRIPCION, TER_FECHA, TER_STATUS, COND_ID) VALUES (?,?,?,?)", 
						new Object[]{dto.getDescription(), dto.getDate(), dto.getStatus(), dto.getCondominiumsDTO().getCondominiumId()});
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_INSERT);
			throw receiptsException;
		}		
	}

	public void deleteEventDTO(int id) throws ReceiptsException {
		try{
			this.jdbcTemplate.update("DELETE FROM TERRAZA WHERE TER_ID = ?", new Object[]{id});
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_DELETE);
			throw receiptsException;
		}		
	}

	public void editEventDTO(EventsDTO dto) throws ReceiptsException {
		try{
			this.jdbcTemplate.update("UPDATE TERRAZA SET TER_DESCRIPCION = ?, TER_FECHA = ?, TER_STATUS = ?, COND_ID = ? WHERE TER_ID = ?", 
					new Object[]{dto.getDescription(), dto.getDate(), dto.getStatus(), dto.getCondominiumsDTO().getCondominiumId(), dto.getId()});
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_UPDATE);
			throw receiptsException;
		}
		
	}

	public EventsDTO getEventDTOById(int id) throws ReceiptsException {
		EventsDTO dto = null;
		try{
			StringBuilder sql = new StringBuilder(0);
			sql.append("SELECT T.TER_ID, T.TER_DESCRIPCION, T.TER_FECHA, T.TER_STATUS, T.COND_ID, C.COND_NUM_CASA ");
			sql.append("FROM TERRAZA T INNER JOIN CONDOMINOS C ON T.COND_ID = C.COND_ID ");
			sql.append("WHERE T.TER_ID = ? ");			
			dto = this.jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new RowMapper<EventsDTO>() {
				public EventsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					EventsDTO dto = new EventsDTO();
					dto.setId(rs.getInt("T.TER_ID"));
					dto.setDescription(rs.getString("T.TER_DESCRIPCION"));
					dto.setDate(rs.getDate("T.TER_FECHA"));
					dto.setStatus(rs.getString("T.TER_STATUS"));
					CondominiumsDTO condominiumsDTO = new CondominiumsDTO();
					condominiumsDTO.setCondominiumId(rs.getInt("T.COND_ID"));
					condominiumsDTO.setHouseNumber(rs.getInt("C.COND_NUM_CASA"));
					dto.setCondominiumsDTO(condominiumsDTO);										
					return dto;
				}
			});		
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_DELETE);
			throw receiptsException;
		}
		return dto;
	}

	public boolean validateBeforeReserv(int day, int month, int year) throws ReceiptsException {
		boolean result = false;
		try{
			int count = this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM TERRAZA WHERE DAY(TER_FECHA) = ? AND MONTH(TER_FECHA) = ? AND YEAR(TER_FECHA) = ?", new Object[]{day, month, year});
			if(count > 0){
				result = true;
			}
		}catch(DataAccessException exception){
			log.error(exception);
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_UPDATE);
			throw receiptsException;
		}
		return result;
	}

}
