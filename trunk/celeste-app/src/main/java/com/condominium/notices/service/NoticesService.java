package com.condominium.notices.service;

import java.util.List;

import javax.faces.model.SelectItem;

import com.condominium.notices.exception.NoticesException;
import com.condominium.notices.view.NoticesView;

public interface NoticesService {

	public List<NoticesView> getNoticesList() throws NoticesException;
	
	public void addNotices(NoticesView view) throws NoticesException;
	
	public void editNotices(NoticesView view) throws NoticesException;
	
	public void deleteNotices(int id) throws NoticesException;
	
	public List<SelectItem> getNoticesCatalogList() throws NoticesException;
	
	public NoticesView getNoticeById(int id) throws NoticesException;
}
