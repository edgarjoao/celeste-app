package com.condominium.receipts.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.receipts.dao.ReceiptsDAO;
import com.condominium.receipts.dto.ReceiptsDTO;
import com.condominium.receipts.dto.ReceiptsItemDTO;
import com.condominium.receipts.exception.ReceiptsException;
/**
 * 
 * @author rioslore
 *
 */
@Repository
public class ReceiptsDAOImpl extends GenericDAO implements ReceiptsDAO {

	public List<ReceiptsDTO> getReceiptsDTOList() throws ReceiptsException {
		List<ReceiptsDTO> receiptsDTOList = null;
		try{
			receiptsDTOList = this.jdbcTemplate.query("SELECT ING_ID, ING_FECHA_INGRESO, ING_IMPORTE, ING_DESCUENTO, ING_COMENTARIOS FROM INGRESOS ORDER BY ING_FECHA_INGRESO DESC", 
															new RowMapper<ReceiptsDTO>() {
	
				public ReceiptsDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ReceiptsDTO dto = new ReceiptsDTO();
					dto.setId(rs.getInt("ING_ID"));
					dto.setDate(rs.getTimestamp("ING_FECHA_INGRESO"));
					dto.setAmount(rs.getDouble("ING_IMPORTE"));
					dto.setDescount(rs.getDouble("ING_DESCUENTO"));
					dto.setComments(rs.getString("ING_COMENTARIOS"));					
					return dto;
				}
			});
		}catch(Exception exception){
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
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_SELECT);
			throw receiptsException;
		}
		
		return itemDTOList;
	}
	
	public void insertReceiptDTO(ReceiptsDTO dto) throws ReceiptsException{
		try{
			this.jdbcTemplate.update("INSERT INTO INGRESOS (ING_FECHA_INGRESO, ING_IMPORTE, ING_DESCUENTO, ING_COMENTARIOS, CATI_ID, COND_ID) VALUES (?,?,?,?,?,?)", 
								new Object[]{dto.getDate(), dto.getAmount(), dto.getDescount(), dto.getComments(), dto.getCatId(), dto.getCondId()});
		}catch(DataAccessException exception){
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_INSERT);
			throw receiptsException;
		}		
	}
	
	public void updateReceiptDTO(ReceiptsDTO dto) throws ReceiptsException{
		try{
			this.jdbcTemplate.update("UPDATE INGRESOS set ING_FECHA_INGRESO = ?, ING_IMPORTE = ? , ING_DESCUENTO = ?, ING_COMENTARIOS = ?, CATI_ID = ?, COND_ID = ?) WHERE ING_ID = ?", 
								new Object[]{dto.getDate(), dto.getAmount(), dto.getDescount(), dto.getComments(), dto.getCatId(), dto.getCondId(), dto.getId()});
		}catch(DataAccessException exception){
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_UPDATE);
			throw receiptsException;
		}		
	}
	
	public void deleteReceiptDTO(int id) throws ReceiptsException{
		try{
			this.jdbcTemplate.update("DELETE FROM INGRESOS WHERE ING_ID = ?", new Object[]{id});
		}catch(DataAccessException exception){
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_DELETE);
			throw receiptsException;
		}	
	}

	public void insertReceiptItemDTO(ReceiptsItemDTO dto) throws ReceiptsException {		
		try{
			this.jdbcTemplate.update("INSERT INTO CATALOGO_INGRESOS (CATI_DESCRIPCION) VALUES (?)", new Object[]{dto.getDescription()});
		}catch(DataAccessException exception){
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_INSERT);
			throw receiptsException;
		}	
	}

	public void updateReceiptItemDTO(ReceiptsItemDTO dto) throws ReceiptsException {
		try{
			this.jdbcTemplate.update("INSERT INTO CATALOGO_INGRESOS (CATI_DESCRIPCION) VALUES (?)", new Object[]{dto.getDescription()});
		}catch(DataAccessException exception){
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_UPDATE);
			throw receiptsException;
		}
	}

	public void deleteReceiptItemDTO(int id) throws ReceiptsException {
		try{
			this.jdbcTemplate.update("DELETE FROM CATALOGO_INGRESOS WHERE CATI_ID = ?", new Object[]{id});
		}catch(DataAccessException exception){
			ReceiptsException receiptsException = new ReceiptsException(exception, ReceiptsException.LAYER_DAO, ReceiptsException.ACTION_DELETE);
			throw receiptsException;
		}
	}

}
