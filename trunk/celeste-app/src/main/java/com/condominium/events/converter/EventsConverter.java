package com.condominium.events.converter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.condominium.condom.dto.CondominiumsDTO;
import com.condominium.events.dto.EventsDTO;
import com.condominium.events.view.EventsView;
import com.condominium.user.converter.UserConverter;

public class EventsConverter {

	public EventsView convertDTOToView(EventsDTO dto){
		EventsView view = null;		
		if(dto != null){
			Calendar cal = Calendar.getInstance();
			view = new EventsView();
			view.setId(dto.getId());
			cal.setTime(dto.getDate());
			view.setDay(cal.get(Calendar.DATE));
			view.setMonth(cal.get(Calendar.MONTH) + 1);
			view.setYear(cal.get(Calendar.YEAR));
			view.setStatus(dto.getStatus());
			view.setDescription(dto.getDescription());
			view.setCondId(dto.getCondominiumsDTO().getCondominiumId());
			view.setHouseNumber(dto.getCondominiumsDTO().getHouseNumber());
			UserConverter userConverter = new UserConverter();
			view.setUserView(userConverter.convertDTOToView(dto.getUserDTO()));
		}
		return view;
	}
	
	public EventsDTO convertViewToDTO(EventsView view){
		EventsDTO dto = null;
		if(view != null){
			Calendar cal = Calendar.getInstance();
			dto = new EventsDTO();		
			dto.setId(view.getId());
			cal.set(view.getYear(), view.getMonth(), view.getDay());			
			dto.setDate(cal.getTime());			
			dto.setDescription(view.getDescription());
			dto.setStatus(view.getStatus());
			CondominiumsDTO condominiumsDTO = new CondominiumsDTO();
			condominiumsDTO.setCondominiumId(view.getCondId());
			dto.setCondominiumsDTO(condominiumsDTO);			
		}
		return dto;
	}
	
	
	public List<EventsView> convertDTOsToViews(List<EventsDTO> dtos){
		List<EventsView> list = new ArrayList<EventsView>(0);
		if(dtos != null){
			if(!dtos.isEmpty()){
				for (EventsDTO dto : dtos) {
					EventsView view = convertDTOToView(dto);
					list.add(view);
				}
			}
		}
		return list;
	}
}
