package com.condominium.user.converter;

import com.condominium.user.dto.UserDTO;
import com.condominium.user.view.UserView;

public class UserConverter {

	public UserView convertDTOToView(UserDTO dto){
		UserView view = null;
		if(dto != null){
			view = new UserView();
			view.setUserId(dto.getUserId());
			view.setUsername(dto.getUsername());
			view.setPassword(dto.getPassword());
			view.setApaterno(dto.getApaterno());
			view.setAmaterno(dto.getAmaterno());
			view.setNombre(dto.getNombre());
			view.setEmail(dto.getEmail());
			view.setRoleId(dto.getRoleId());
			view.setRolDescription(dto.getRolDescription());
			view.setTelCasa(String.valueOf(dto.getTelCasa()));
			view.setTelCelular(String.valueOf(dto.getTelCelular()));			
		}
		return view;
	}
	
	public UserDTO convertViewToDTO(UserView view){
		UserDTO dto = null;
		if(view != null){
			dto = new UserDTO();
			dto.setUserId(view.getUserId());
			dto.setUsername(view.getUsername());
			dto.setPassword(view.getPassword());
			dto.setNombre(view.getNombre());
			dto.setApaterno(view.getApaterno());
			dto.setAmaterno(view.getAmaterno());
			dto.setEmail(view.getEmail());
			dto.setRoleId(view.getRoleId());
			dto.setTelCasa(Integer.parseInt(view.getTelCasa()));
			dto.setTelCelular(Integer.parseInt(view.getTelCelular()));
		}		
		return dto;
	}
}
