package com.condominium.notices.dao;

import java.util.List;

import com.condominium.notices.dto.NoticesDTO;
import com.condominium.notices.exception.NoticesException;

public interface NoticesDAO {

	public List<NoticesDTO> getNoticesList() throws NoticesException;
	
	public void addNotices(NoticesDTO dto) throws NoticesException;
	
	public void editNotices(NoticesDTO dto) throws NoticesException;
	
	public void deleteNotices(int id) throws NoticesException;
	
	public List<NoticesDTO> getNoticesCatalogList() throws NoticesException;
	
	public NoticesDTO getNoticeById(int id) throws NoticesException;
}
