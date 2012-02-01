package com.condominium.notices.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.condominium.common.dao.GenericDAO;
import com.condominium.notices.dao.NoticesDAO;
import com.condominium.notices.dto.NoticesDTO;
import com.condominium.notices.exception.NoticesException;

@Repository
public class NoticesDAOImpl extends GenericDAO implements NoticesDAO {

	private static final Logger log = Logger.getLogger(NoticesDAOImpl.class);

	public List<NoticesDTO> getNoticesList() throws NoticesException {
		List<NoticesDTO> noticesDTOsList = null;
		StringBuilder sql = new StringBuilder(0);
		sql.append("SELECT AV_ID, AV_FECHA, AV_DETALLE, TA.TA_ID, TA.TA_TIPO_AVISO FROM ");
		sql.append("AVISOS A INNER JOIN TIPO_AVISO TA ON A.TA_ID = TA.TA_ID");		
		try{
			noticesDTOsList = this.jdbcTemplate.query(sql.toString(), new RowMapper<NoticesDTO>() {
	
				public NoticesDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					NoticesDTO dto = new NoticesDTO();
					dto.setId(rs.getInt("AV_ID"));
					dto.setDate(rs.getDate("AV_FECHA"));
					dto.setDetail(rs.getString("AV_DETALLE"));
					dto.setTypeNoticeId(rs.getInt("TA.TA_ID"));
					dto.setTypeNoticeDescription(rs.getString("TA.TA_TIPO_AVISO"));					
					return dto;
				}
			});	
		}catch(Exception exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_DAO, NoticesException.ACTION_SELECT);
			throw noticesException;
		}		
		return noticesDTOsList;
	}

	public void addNotices(NoticesDTO dto) throws NoticesException {
		try{
			this.jdbcTemplate.update("INSERT INTO AVISOS (AV_FECHA, AV_DETALLE, TA_ID) VALUES (NOW(),?,?)", 
								new Object[]{dto.getDetail(), dto.getTypeNoticeId()});
		}catch(DataAccessException exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_DAO, NoticesException.ACTION_INSERT);
			throw noticesException;
		}		
	}

	public void editNotices(NoticesDTO dto) throws NoticesException {
		try{
			this.jdbcTemplate.update("UPDATE AVISOS SET AV_DETALLE = ?, TA_ID = ? WHERE AV_ID = ?", 
								new Object[]{dto.getDetail(), dto.getTypeNoticeId(), dto.getId()});
		}catch(DataAccessException exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_DAO, NoticesException.ACTION_UPDATE);
			throw noticesException;
		}
		
	}

	public void deleteNotices(int id) throws NoticesException {
		try{
			this.jdbcTemplate.update("DELETE FROM AVISOS WHERE AV_ID = ?", new Object[]{id});
		}catch(DataAccessException exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_DAO, NoticesException.ACTION_DELETE);
			throw noticesException;
		}		
	}

	public List<NoticesDTO> getNoticesCatalogList() throws NoticesException {
		List<NoticesDTO> noticesDTOsList = null;				
		try{
			noticesDTOsList = this.jdbcTemplate.query("SELECT TA_ID, TA_TIPO_AVISO FROM TIPO_AVISO", new RowMapper<NoticesDTO>() {
	
				public NoticesDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					NoticesDTO dto = new NoticesDTO();
					dto.setId(0);
					dto.setDate(new Date());
					dto.setDetail("");
					dto.setTypeNoticeId(rs.getInt("TA_ID"));
					dto.setTypeNoticeDescription(rs.getString("TA_TIPO_AVISO"));					
					return dto;
				}
			});	
		}catch(Exception exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_DAO, NoticesException.ACTION_SELECT);
			throw noticesException;
		}		
		return noticesDTOsList;	
	}

	public NoticesDTO getNoticeById(int id) throws NoticesException {
		NoticesDTO dto = null;
		StringBuilder sql = new StringBuilder(0);
		sql.append("SELECT AV_ID, AV_FECHA, AV_DETALLE, TA.TA_ID, TA.TA_TIPO_AVISO FROM ");
		sql.append("AVISOS A INNER JOIN TIPO_AVISO TA ON A.TA_ID = TA.TA_ID WHERE AV_ID = ?");	
		try{
			dto = this.jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new RowMapper<NoticesDTO>() {	
				public NoticesDTO mapRow(ResultSet rs, int arg1) throws SQLException {
					NoticesDTO dto = new NoticesDTO();
					dto.setId(rs.getInt("AV_ID"));
					dto.setDate(rs.getDate("AV_FECHA"));
					dto.setDetail(rs.getString("AV_DETALLE"));
					dto.setTypeNoticeId(rs.getInt("TA.TA_ID"));
					dto.setTypeNoticeDescription(rs.getString("TA.TA_TIPO_AVISO"));		
					return dto;
				}
			});
		}catch(Exception exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_DAO, NoticesException.ACTION_SELECT);
			throw noticesException;
		}	
		return dto;		
	}
	
}
