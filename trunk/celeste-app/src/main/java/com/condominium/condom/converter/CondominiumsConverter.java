package com.condominium.condom.converter;

import java.util.ArrayList;
import java.util.List;

import com.condominium.common.utils.NumberUtil;
import com.condominium.condom.dto.CondominiumsDTO;
import com.condominium.condom.view.CondominiumsView;
import com.condominium.user.converter.UserConverter;

public class CondominiumsConverter {

	public CondominiumsView convertDTOToView(CondominiumsDTO dto){
		CondominiumsView view = null;
		if(dto != null){
			view = new CondominiumsView();
			view.setId(String.valueOf(dto.getId()));
			view.setCondominiumId(String.valueOf(dto.getCondominiumId()));
			view.setHouseNumber(String.valueOf(dto.getHouseNumber()));
			view.setTypeId(String.valueOf(dto.getTypeId()));
			view.setTypeDescription(dto.getTypeDescription());
			view.setDebt(NumberUtil.convertQuantity(dto.getDebt()));
			view.setDebDescription(dto.getDebDescription());
			if(dto.getUserDTO() != null){
				UserConverter uConverter = new UserConverter();
				view.setUserView(uConverter.convertDTOToView(dto.getUserDTO()));
			}
		}
		return view;
	}
	
	public List<CondominiumsView> convertCondominiumsDTOsToViews(List<CondominiumsDTO> dtos){
		List<CondominiumsView> condominiumsViews = new ArrayList<CondominiumsView>(0);		
		if(dtos != null){
			for (CondominiumsDTO dto : dtos) {
				CondominiumsView view = this.convertDTOToView(dto);
				condominiumsViews.add(view);
			}
		}
		return condominiumsViews;
	}
		
}
