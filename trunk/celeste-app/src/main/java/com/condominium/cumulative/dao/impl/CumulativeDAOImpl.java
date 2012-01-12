package com.condominium.cumulative.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.cumulative.dao.CumulativeDAO;
import com.condominium.cumulative.dto.CumulativeDTO;
import com.condominium.cumulative.exception.CumulativeException;
import com.condominium.receipts.exception.ReceiptsException;
/**
 * 
 * @author rioslore
 *
 */
@Repository
public class CumulativeDAOImpl extends GenericDAO implements CumulativeDAO {

	public CumulativeDTO getCurrentCumulative() throws CumulativeException {
		CumulativeDTO dto = null;
		try{				
			dto = this.jdbcTemplate.queryForObject("SELECT AC_ID, AC_MES, AC_ANIO, AC_MONTO FROM ACUMULADO ORDER BY AC_ID DESC limit 1", new RowMapper<CumulativeDTO>() {
				public CumulativeDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					CumulativeDTO dto = new CumulativeDTO();
					dto.setId(rs.getInt("AC_ID"));
					dto.setMonth(rs.getInt("AC_MES"));
					dto.setYear(rs.getInt("AC_ANIO"));
					dto.setAmount(rs.getDouble("AC_MONTO"));
					return dto;
				}
			});
		}catch(EmptyResultDataAccessException erdae){
			CumulativeException cumulativeException = new CumulativeException("No se encontraron resultados.", CumulativeException.LAYER_DAO, CumulativeException.ACTION_SELECT);
			throw cumulativeException;
		}catch(DataAccessException exception){
			CumulativeException cumulativeException = new CumulativeException(exception, CumulativeException.LAYER_DAO, CumulativeException.ACTION_SELECT);
			throw cumulativeException;
		}
		return dto;
	}

	public double totalPreviousCumulative(int month, int year) throws CumulativeException {		
		try{
			StringBuilder receiptsSQL = new StringBuilder(0);
			receiptsSQL.append("SELECT SUM(ING_IMPORTE) AS IMPORTE_INGRESOS FROM INGRESOS ");
			receiptsSQL.append("WHERE MONTH(ING_FECHA_ALTA) = ? AND YEAR(ING_FECHA_ALTA) = ? ");	
			Long receipt = this.jdbcTemplate.queryForLong(receiptsSQL.toString(), new Object[]{month, year});
			double ingreso = receipt.doubleValue();
			
			StringBuilder expensesSQL = new StringBuilder(0);
			expensesSQL.append("SELECT SUM(EGR_IMPORTE) FROM EGRESOS WHERE ");
			expensesSQL.append("MONTH(EGR_FECHA_EGRESO) = ? AND YEAR(EGR_FECHA_EGRESO) = ? ");			
			Long expenses = this.jdbcTemplate.queryForLong(expensesSQL.toString(), new Object[]{month, year});
			double egreso = expenses.doubleValue();			
			return ingreso - egreso;
		}catch(EmptyResultDataAccessException erdae){
			CumulativeException cumulativeException = new CumulativeException("No se encontraron Resultados.", CumulativeException.LAYER_DAO, CumulativeException.ACTION_SELECT);
			throw cumulativeException;
		}catch(DataAccessException dae){
			CumulativeException cumulativeException = new CumulativeException(dae, CumulativeException.LAYER_DAO, CumulativeException.ACTION_SELECT);
			throw cumulativeException;
		}
	}

	public void insertCumulative(int month, int year, double amount) throws CumulativeException {
		try{
			this.jdbcTemplate.update("INSERT INTO ACUMULADO (AC_MES, AC_ANIO, AC_MONTO) VALUES (?,?,?)", new Object[]{month, year, amount});
		}catch(DataAccessException exception){
			CumulativeException cumulativeException = new CumulativeException(exception, CumulativeException.LAYER_DAO, CumulativeException.ACTION_INSERT);
			throw cumulativeException;
		}			
	}

	public boolean existCumulative(int month, int year) throws CumulativeException {
		boolean result = false;
		try{
			int count = this.jdbcTemplate.queryForInt("SELECT count(*) FROM ACUMULADO WHERE AC_MES = ? AND AC_ANIO = ?", new Object[]{month, year});
			if(count > 0){
				result = true;
			}
		}catch(DataAccessException exception){
			CumulativeException cumulativeException = new CumulativeException(exception, CumulativeException.LAYER_DAO, CumulativeException.ACTION_INSERT);
			throw cumulativeException;
		}		
		return result;
	}

	public void deleteCumulative(int id) throws CumulativeException {		
		try{
			this.jdbcTemplate.update("DELETE FROM ACUMULADO WHERE AC_ID = ?", new Object[]{id});
		}catch(DataAccessException exception){
			CumulativeException cumulativeException = new CumulativeException(exception, CumulativeException.LAYER_DAO, CumulativeException.ACTION_DELETE);
			throw cumulativeException;
		}
	}

	public List<CumulativeDTO> getCumulativeList() throws CumulativeException {
		List<CumulativeDTO> dtos = null;
		try{				
			dtos = this.jdbcTemplate.query("SELECT AC_ID, AC_MES, AC_ANIO, AC_MONTO FROM ACUMULADO ORDER BY AC_ID DESC", new RowMapper<CumulativeDTO>() {
				public CumulativeDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					CumulativeDTO dto = new CumulativeDTO();
					dto.setId(rs.getInt("AC_ID"));
					dto.setMonth(rs.getInt("AC_MES"));
					dto.setYear(rs.getInt("AC_ANIO"));
					dto.setAmount(rs.getDouble("AC_MONTO"));
					return dto;
				}
			});
		}catch(EmptyResultDataAccessException erdae){
			CumulativeException cumulativeException = new CumulativeException("No se encontraron resultados.", CumulativeException.LAYER_DAO, CumulativeException.ACTION_SELECT);
			throw cumulativeException;
		}catch(DataAccessException exception){
			CumulativeException cumulativeException = new CumulativeException(exception, CumulativeException.LAYER_DAO, CumulativeException.ACTION_SELECT);
			throw cumulativeException;
		}
		return dtos;
	}

	public CumulativeDTO getCumulative(int month, int year) throws CumulativeException {
		CumulativeDTO dto = null;
		try{				
			dto = this.jdbcTemplate.queryForObject("SELECT AC_ID, AC_MES, AC_ANIO, AC_MONTO FROM ACUMULADO WHERE AC_MES = ? AND AC_ANIO = ?", 
									new Object[]{month, year}, new RowMapper<CumulativeDTO>() {
				public CumulativeDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					CumulativeDTO dto = new CumulativeDTO();
					dto.setId(rs.getInt("AC_ID"));
					dto.setMonth(rs.getInt("AC_MES"));
					dto.setYear(rs.getInt("AC_ANIO"));
					dto.setAmount(rs.getDouble("AC_MONTO"));
					return dto;
				}
			});
		}catch(EmptyResultDataAccessException erdae){
			CumulativeException cumulativeException = new CumulativeException("No se encontraron resultados.", CumulativeException.LAYER_DAO, CumulativeException.ACTION_SELECT);
			throw cumulativeException;
		}catch(DataAccessException exception){
			CumulativeException cumulativeException = new CumulativeException(exception, CumulativeException.LAYER_DAO, CumulativeException.ACTION_SELECT);
			throw cumulativeException;
		}
		return dto;
	}

	
}
