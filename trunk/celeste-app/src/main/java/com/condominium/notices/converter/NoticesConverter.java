package com.condominium.notices.converter;

import java.util.ArrayList;
import java.util.List;

import com.condominium.common.utils.TimeUtils;
import com.condominium.notices.dto.NoticesDTO;
import com.condominium.notices.view.NoticesView;
/**
 * 
 * @author rioslore
 *
 */
public class NoticesConverter {

	public NoticesView convertDTOToView(NoticesDTO dto){
		NoticesView view = new NoticesView();
		if(dto != null){
			view.setId(String.valueOf(dto.getId()));
			view.setDate(TimeUtils.convertJavaDateToStringDate(dto.getDate(), TimeUtils.DATE_PATTERN_DDMMYYYY));
			view.setDetail(dto.getDetail());			
			view.setTypeNoticeId(String.valueOf(dto.getTypeNoticeId()));
			view.setTypeNoticeDescription(dto.getTypeNoticeDescription());
		}
		return view;
	}
	
	public NoticesDTO convertViewToDTO(NoticesView view){
		NoticesDTO dto = new NoticesDTO();
		if(view != null){
			if(view.getId() != null){
				dto.setId( view.getId().equals("") ? 0 : Integer.parseInt(view.getId()));
			}
			dto.setDetail(view.getDetail());
			if(view.getTypeNoticeId() != null){
				dto.setTypeNoticeId(view.getTypeNoticeId().equals("") ? 0 : Integer.parseInt(view.getTypeNoticeId()));
			}
		}
		return dto;
	}
	
	public List<NoticesView> convertDTOsToViews(List<NoticesDTO> dtos){
		List<NoticesView> viewList = new ArrayList<NoticesView>(0);
		if(!dtos.isEmpty()){
			for(NoticesDTO dto: dtos){
				viewList.add(this.convertDTOToView(dto));
			}
		}
		return viewList;
	}
	
}
