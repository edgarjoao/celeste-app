package com.condominium.condom.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominium.condom.converter.CondominiumsConverter;
import com.condominium.condom.dao.CondominiumsDAO;
import com.condominium.condom.dto.CondominiumsDTO;
import com.condominium.condom.service.CondominiumsService;
import com.condominium.condom.view.CondominiumsView;
import com.condominium.user.exception.UserException;
/**
 * 
 * @author rioslore
 *
 */
@Service(value="condominiumsService")
public class CondominiumsServiceImpl implements CondominiumsService {

	@Autowired
	CondominiumsDAO condominiumsDAO;
	
	public List<CondominiumsView> getCondominiumsList() throws UserException {
		List<CondominiumsView> list = new ArrayList<CondominiumsView>(0);
		CondominiumsConverter converter = new CondominiumsConverter();
		
		list = converter.convertCondominiumsDTOsToViews(condominiumsDAO.getCondominiumsList());
				
		return list;
	}
	
	public List<SelectItem> getCondominiumsItems() throws UserException{
		List<SelectItem> selectItems = new ArrayList<SelectItem>(0);
		List<CondominiumsDTO> dtosList = condominiumsDAO.getCondominiumsList();		
		if(dtosList != null){
			for(CondominiumsDTO dto : dtosList){				
				StringBuilder value = new StringBuilder(0);
				value.append(dto.getHouseNumber());
				value.append(" - ");
				value.append(dto.getUserDTO().getNombre() == null ? "" : dto.getUserDTO().getNombre());
				value.append(" ");
				value.append(dto.getUserDTO().getApaterno() == null ? "" : dto.getUserDTO().getApaterno());
				value.append(" ");
				value.append(dto.getUserDTO().getAmaterno() == null ? "" : dto.getUserDTO().getAmaterno());
				SelectItem item = new SelectItem(String.valueOf(dto.getId()), value.toString());
				selectItems.add(item);
			}
		}				
		return selectItems;
	}

}
