package com.condominium.notices.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominium.notices.converter.NoticesConverter;
import com.condominium.notices.dao.NoticesDAO;
import com.condominium.notices.dto.NoticesDTO;
import com.condominium.notices.exception.NoticesException;
import com.condominium.notices.service.NoticesService;
import com.condominium.notices.view.NoticesView;
/**
 * 
 * @author rioslore
 *
 */
@Service("noticesService")
public class NoticesServiceImpl implements NoticesService {

	private static final Logger log = Logger.getLogger(NoticesServiceImpl.class);
	
	@Autowired
	private NoticesDAO noticesDAO;
	NoticesConverter converter;
	
	public List<NoticesView> getNoticesList() throws NoticesException {
		List<NoticesView> list = new ArrayList<NoticesView>();
		converter = new NoticesConverter();
		try{
			list = converter.convertDTOsToViews(noticesDAO.getNoticesList());
		}catch(NoticesException exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_SERVICE, NoticesException.ACTION_SELECT);
			throw noticesException;
		}			
		return list;
	}

	public void addNotices(NoticesView view) throws NoticesException {
		try{
			noticesDAO.addNotices(converter.convertViewToDTO(view));
		}catch(NoticesException exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_SERVICE, NoticesException.ACTION_INSERT);
			throw noticesException;
		}
	}

	public void editNotices(NoticesView view) throws NoticesException {
		try{
			noticesDAO.editNotices(converter.convertViewToDTO(view));
		}catch(NoticesException exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_SERVICE, NoticesException.ACTION_UPDATE);
			throw noticesException;
		}		
	}

	public void deleteNotices(int id) throws NoticesException {
		try{
			noticesDAO.deleteNotices(id);
		}catch(NoticesException exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_SERVICE, NoticesException.ACTION_DELETE);
			throw noticesException;
		}
		
	}

	public List<SelectItem> getNoticesCatalogList() throws NoticesException {
		List<SelectItem> items = new ArrayList<SelectItem>(0);		
		try{		
			List<NoticesDTO> dtos = noticesDAO.getNoticesCatalogList();
			if(!dtos.isEmpty()){
				for(NoticesDTO dto : dtos){				
					items.add(new SelectItem(String.valueOf(dto.getTypeNoticeId()), dto.getTypeNoticeDescription()));
				}
			}
		}catch(NoticesException exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_SERVICE, NoticesException.ACTION_SELECT);
			throw noticesException;
		}
		return items;
	}

	public NoticesView getNoticeById(int id) throws NoticesException {
		NoticesView view = null;
		try{
			view = converter.convertDTOToView(noticesDAO.getNoticeById(id));
		}catch(NoticesException exception){
			log.error(exception);
			NoticesException noticesException = new NoticesException(exception, NoticesException.LAYER_SERVICE, NoticesException.ACTION_SELECT);
			throw noticesException;
		}		
		return view;
	}

	
}
