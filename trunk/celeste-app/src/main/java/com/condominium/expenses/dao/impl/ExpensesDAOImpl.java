package com.condominium.expenses.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.expenses.dao.ExpensesDAO;
import com.condominium.expenses.dto.ExpensesDTO;
import com.condominium.expenses.dto.ExpensesItemDTO;
import com.condominium.expenses.dto.SuppliersDTO;
import com.condominium.expenses.exception.ExpensesException;
/**
 * 
 * @author rioslore
 *
 */
@Repository
public class ExpensesDAOImpl extends GenericDAO implements ExpensesDAO {

	
	public List<ExpensesItemDTO> getExpensesItemList() throws ExpensesException {		
		List<ExpensesItemDTO> exItemDTOs = null;		
		try{
			exItemDTOs = this.jdbcTemplate.query("SELECT CATE_ID, CATE_DESCRIPCION FROM CATALOGO_EGRESOS", new RowMapper<ExpensesItemDTO>() {
	
				public ExpensesItemDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ExpensesItemDTO dto = new ExpensesItemDTO();
					dto.setId(rs.getInt("CATE_ID"));
					dto.setDescription(rs.getString("CATE_DESCRIPCION"));
					return null;
				}
			});	
		}catch(Exception exception){
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
					dto.setPhoneNumer(rs.getInt("PROV_TEL_CASA"));
					dto.setCellPhoneNumber(rs.getInt("PROV_TEL_CELULAR"));
					return dto;
				}
			});
		}catch(Exception exception){
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_SELECT);
			throw expensesException;
		}		
		return suppliersList;
	}

	public List<ExpensesDTO> getExpensesList() throws ExpensesException {
		List<ExpensesDTO> expensesList = null;
		try{
			expensesList = this.jdbcTemplate.query("SELECT EGR_ID, EGR_FECHA_EGRESO, EGR_IMPORTE, EGR_COMENTARIOS FROM EGRESOS ORDER BY EGR_FECHA_EGRESO DESC", new RowMapper<ExpensesDTO>() {
	
				public ExpensesDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					ExpensesDTO dto = new ExpensesDTO();
					dto.setId(rs.getInt("EGR_ID"));
					dto.setDate(rs.getTimestamp("EGR_FECHA_EGRESO"));
					dto.setAmount(rs.getDouble("EGR_IMPORTE"));
					dto.setComments(rs.getString("EGR_COMENTARIOS"));					
					return dto;
				}
			});
		}catch(Exception exception){
			ExpensesException expensesException = new ExpensesException(exception, ExpensesException.LAYER_DAO, ExpensesException.ACTION_SELECT);
			throw expensesException;
		}
		
		return expensesList;
	}

	
}
