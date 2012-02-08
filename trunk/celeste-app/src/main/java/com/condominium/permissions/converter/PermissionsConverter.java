package com.condominium.permissions.converter;

import java.util.ArrayList;
import java.util.List;

import com.condominium.permissions.dto.PermissionsDTO;
import com.condominium.permissions.view.PermissionsView;

public class PermissionsConverter {

	public PermissionsView convertDtoToView(PermissionsDTO dto){
		PermissionsView view = null;
		if(dto != null){
			view = new PermissionsView();
			view.setPerId(dto.getPerId());
			view.setPerDescription(dto.getPerDescription());
			view.setShortName(dto.getShortName());
			view.setSelected(dto.isSelected());
		}
		return view;
	}
	
	public List<PermissionsView> convertDTOsToViews(List<PermissionsDTO> dtos){
		List<PermissionsView> list = new ArrayList<PermissionsView>(0);		
		if(!dtos.isEmpty()){
			for(PermissionsDTO dto: dtos){
				list.add(convertDtoToView(dto));
			}
		}
		return list;
	}
}
