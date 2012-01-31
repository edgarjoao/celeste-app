package com.condominium.expenses.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.expenses.dao.ExpensesDAO;
import com.condominium.expenses.dto.ExpensesDTO;
import com.condominium.expenses.dto.ExpensesItemDTO;
import com.condominium.expenses.dto.ExpensesReportDTO;
import com.condominium.expenses.dto.SuppliersDTO;
import com.condominium.expenses.exception.ExpensesException;
/**
 * 
 * @author rioslore
 *
 */
@Repository
public class ExpensesDAOImpl extends GenericDAO implements ExpensesDAO {

	private static final Logger log = Logger.getLogger(ExpensesDAOImpl.class);
	
	public List<ExpensesItemDTO> getExpensesItemList() throws ExpensesException {		
		List<ExpensesItemDTO> exItemDTOs = null;		
		try{
			exItemDTOs = this.jdbcTemplate.query("SELECT CATE_ID, CATE_DESCRIPCION FROM CATALOGO_EGRESOS", new RowMapper<ExpensesItemDTO>() {
	
				public ExpensesItemDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ExpensesItemDTO dto = new ExpensesItemDTO();
					dto.setId(rs.getInt("CATE_ID"));
					dto.setDescription(rs.getString("CATE_DESCRIPCION"));
					return dto;
				}
			});	
		}catch(Exception exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_SELECT);
			throw expensesException;
		}		
		return exItemDTOs;
	}

	public List<SuppliersDTO> getSuppliersList() throws ExpensesException {
		List<SuppliersDTO> suppliersList = null;				
		try{
			suppliersList = this.jdbcTemplate.query("SELECT PROV_ID, PROV_NOMBRE, PROV_DOMICILIO, PROV_TEL_CASA, PROV_TEL_CELULAR FROM PROVEEDORES", new RowMapper<SuppliersDTO>() {
				public SuppliersDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					SuppliersDTO dto = new SuppliersDTO();
					dto.setProvId(rs.getInt("PROV_ID"));
					dto.setName(rs.getString("PROV_NOMBRE"));
					dto.setAddress(rs.getString("PROV_DOMICILIO"));
					dto.setPhoneNumber(rs.getString("PROV_TEL_CASA"));
					dto.setCellPhoneNumber(rs.getString("PROV_TEL_CELULAR"));
					return dto;
				}
			});
		}catch(Exception exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_SELECT);
			throw expensesException;
		}		
		return suppliersList;
	}

	public List<ExpensesDTO> getExpensesList() throws ExpensesException {
		List<ExpensesDTO> expensesList = null;
		StringBuilder sql = new StringBuilder(0);
		try{
			sql.append("SELECT E.EGR_ID, E.EGR_FECHA_EGRESO, E.EGR_IMPORTE, E.EGR_COMENTARIOS, CT.CATE_DESCRIPCION, P.PROV_NOMBRE ");
			sql.append("FROM EGRESOS E, CATALOGO_EGRESOS CT, PROVEEDORES P ");
			sql.append("WHERE E.CATE_ID = CT.CATE_ID AND P.PROV_ID = E.PROV_ID ");
			sql.append("ORDER BY EGR_FECHA_EGRESO DESC ");
			expensesList = this.jdbcTemplate.query(sql.toString(), new RowMapper<ExpensesDTO>() {
	
				public ExpensesDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ExpensesDTO dto = new ExpensesDTO();
					dto.setId(rs.getInt("EGR_ID"));
					dto.setDate(rs.getTimestamp("EGR_FECHA_EGRESO"));
					dto.setAmount(rs.getDouble("EGR_IMPORTE"));
					dto.setComments(rs.getString("EGR_COMENTARIOS"));
					ExpensesItemDTO eDto = new ExpensesItemDTO();
					eDto.setDescription(rs.getString("CT.CATE_DESCRIPCION"));
					dto.setExpensesItemDTO(eDto);
					SuppliersDTO sDTO = new SuppliersDTO();
					sDTO.setName(rs.getString("P.PROV_NOMBRE"));
					dto.setSuppliersDTO(sDTO);
					return dto;
				}
			});
		}catch(Exception exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_SELECT);
			throw expensesException;
		}
		
		return expensesList;
	}
	
	public void addSupplier(SuppliersDTO dto) throws ExpensesException{
		try{
			this.jdbcTemplate.update("INSERT INTO PROVEEDORES (PROV_NOMBRE, PROV_DOMICILIO, PROV_TEL_CASA, PROV_TEL_CELULAR) VALUES (?,?,?,?)", 
								new Object[]{dto.getName(), dto.getAddress(), dto.getPhoneNumber(), dto.getCellPhoneNumber()});
		}catch(DataAccessException exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_INSERT);
			throw expensesException;
		}		
	}

	public void addExpensesItem(ExpensesItemDTO dto) throws ExpensesException {
		try{
			this.jdbcTemplate.update("INSERT INTO CATALOGO_EGRESOS (CATE_DESCRIPCION) VALUES (?)", new Object[]{dto.getDescription()});
		}catch(DataAccessException exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_INSERT);
			throw expensesException;
		}			
	}

	public void addExpenses(ExpensesDTO dto) throws ExpensesException {
		try{
			this.jdbcTemplate.update("INSERT INTO EGRESOS (EGR_FECHA_EGRESO, EGR_IMPORTE, EGR_COMENTARIOS, CATE_ID, PROV_ID) VALUES (?,?,?,?,?)", 
							new Object[]{dto.getDate(), dto.getAmount(), dto.getComments(), dto.getExpensesItemDTO().getId(), dto.getSuppliersDTO().getProvId()});
		}catch(DataAccessException exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_INSERT);
			throw expensesException;
		}
		
	}

	public void editSupplierDTO(SuppliersDTO dto) throws ExpensesException {
		try{
			this.jdbcTemplate.update("UPDATE PROVEEDORES SET PROV_NOMBRE = ?, PROV_DOMICILIO = ?, PROV_TEL_CASA = ?, PROV_TEL_CELULAR = ? WHERE PROV_ID = ?", 
								new Object[]{dto.getName(), dto.getAddress(), dto.getPhoneNumber(), dto.getCellPhoneNumber(), dto.getProvId()});
		}catch(DataAccessException exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_UPDATE);
			throw expensesException;
		}			
	}

	public void deleteSupplierDTO(int id) throws ExpensesException {
		try{
			this.jdbcTemplate.update("DELETE FROM PROVEEDORES WHERE PROV_ID = ?", new Object[]{id});
		}catch(DataAccessException exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_DELETE);
			throw expensesException;
		}	
	}

	public List<ExpensesDTO> searchExpensesList(int month, int year, int provId, int cateId) throws ExpensesException {
		List<ExpensesDTO> expensesList = null;
		StringBuilder sql = new StringBuilder(0);		
		try{
			sql.append("SELECT E.EGR_ID, E.EGR_FECHA_EGRESO, E.EGR_IMPORTE, E.EGR_COMENTARIOS, CT.CATE_DESCRIPCION, CT.CATE_ID, P.PROV_ID, P.PROV_NOMBRE ");
			sql.append("FROM EGRESOS E, PROVEEDORES P, CATALOGO_EGRESOS CT ");
			sql.append("WHERE E.PROV_ID = P.PROV_ID AND E.CATE_ID = CT.CATE_ID  ");
			sql.append("AND MONTH(EGR_FECHA_EGRESO) = ? AND YEAR(EGR_FECHA_EGRESO) = ?  ");			
			Object[] parameters = null;									
			if((provId < 0) && (cateId < 0)){
				parameters =  new Object[2];
				parameters[0] = month;
				parameters[1] = year;
			}
			if((provId > 0) && (cateId > 0)){
				sql.append(" AND E.PROV_ID = ? ");
				sql.append(" AND CT.CATE_ID = ? ");
				parameters =  new Object[4];
				parameters[0] = month;
				parameters[1] = year;
				parameters[2] = provId;
				parameters[3] = cateId;
			}
			if((provId > 0) && (cateId < 0)){
				sql.append(" AND E.PROV_ID = ? ");
				parameters =  new Object[3];
				parameters[0] = month;
				parameters[1] = year;
				parameters[2] = provId;
			}			
			if((provId < 0) && (cateId > 0)){
				sql.append(" AND CT.CATE_ID = ? ");
				parameters =  new Object[3];
				parameters[0] = month;
				parameters[1] = year;
				parameters[2] = cateId;
			}			
			sql.append(" ORDER BY EGR_FECHA_EGRESO DESC ");			
										
			expensesList = this.jdbcTemplate.query(sql.toString(), parameters, new RowMapper<ExpensesDTO>() {
	
				public ExpensesDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ExpensesDTO dto = new ExpensesDTO();
					dto.setId(rs.getInt("EGR_ID"));
					dto.setDate(rs.getTimestamp("EGR_FECHA_EGRESO"));
					dto.setAmount(rs.getDouble("EGR_IMPORTE"));
					dto.setComments(rs.getString("EGR_COMENTARIOS"));
					ExpensesItemDTO eDto = new ExpensesItemDTO();
					eDto.setDescription(rs.getString("CT.CATE_DESCRIPCION"));
					eDto.setId(rs.getInt("CT.CATE_ID"));
					dto.setExpensesItemDTO(eDto);
					SuppliersDTO sDTO = new SuppliersDTO();
					sDTO.setProvId(rs.getInt("P.PROV_ID"));
					sDTO.setName(rs.getString("P.PROV_NOMBRE"));
					dto.setSuppliersDTO(sDTO);
					return dto;
				}
			});
		}catch(Exception exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_SELECT);
			throw expensesException;
		}
		
		return expensesList;
	}

	public void editExpensesDTO(ExpensesDTO dto) throws ExpensesException {
		try{
			this.jdbcTemplate.update("UPDATE EGRESOS SET EGR_IMPORTE = ?, EGR_COMENTARIOS = ?, CATE_ID = ?, PROV_ID = ? WHERE EGR_ID = ?", 
								new Object[]{dto.getAmount(), dto.getComments(),dto.getExpensesItemDTO().getId(), dto.getSuppliersDTO().getProvId(), dto.getId()});
		}catch(DataAccessException exception){
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_UPDATE);
			log.error(exception);
			throw expensesException;
		}			
	}

	public void deleteExpensesDTO(int id) throws ExpensesException {
		try{
			this.jdbcTemplate.update("DELETE FROM EGRESOS WHERE EGR_ID = ?", new Object[]{id});
		}catch(DataAccessException exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_UPDATE);
			throw expensesException;
		}					
	}

	public List<ExpensesReportDTO> getExpensesReportDTO(int month, int year) throws ExpensesException {
		List<ExpensesReportDTO> list = null;
		StringBuilder sql = new StringBuilder(0);
		try{
			sql.append("SELECT CE.CATE_DESCRIPCION, SUM(EGR_IMPORTE) AS IMPORTE FROM EGRESOS E ");
			sql.append("INNER JOIN CATALOGO_EGRESOS CE ON E.CATE_ID = CE.CATE_ID  ");
			sql.append("WHERE MONTH(EGR_FECHA_EGRESO) = ? AND YEAR(EGR_FECHA_EGRESO) = ? ");
			sql.append("GROUP BY CE.CATE_ID");
			list = this.jdbcTemplate.query(sql.toString(), new Object[]{month, year}, new RowMapper<ExpensesReportDTO>() {
				
				public ExpensesReportDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ExpensesReportDTO dto = new ExpensesReportDTO();
					dto.setDescription(rs.getString("CE.CATE_DESCRIPCION"));
					dto.setAmount(rs.getDouble("IMPORTE"));
					return dto;
				}
			});			
		}catch(DataAccessException exception){
			log.error(exception);
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_SELECT);
			throw expensesException;
		}		
		return list;
	}

	
		
}
