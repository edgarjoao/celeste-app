package com.condominium.receipts.converter;

import java.util.ArrayList;
import java.util.List;

import com.condominium.common.utils.NumberUtil;
import com.condominium.common.utils.StringUtils;
import com.condominium.common.utils.TimeUtils;
import com.condominium.condom.converter.CondominiumsConverter;
import com.condominium.condom.view.CondominiumsView;
import com.condominium.receipts.dto.ReceiptsDTO;
import com.condominium.receipts.dto.ReceiptsItemDTO;
import com.condominium.receipts.dto.ReceiptsReportDTO;
import com.condominium.receipts.view.ReceiptsItemView;
import com.condominium.receipts.view.ReceiptsReportView;
import com.condominium.receipts.view.ReceiptsView;

public class ReceiptsConverter {

	public ReceiptsView convertDtoToView(ReceiptsDTO dto){
		ReceiptsView view = null;
		if(dto != null){
			view = new ReceiptsView();
			view.setId(String.valueOf(dto.getId()));
			view.setAmount(NumberUtil.convertQuantity(dto.getAmount()));
			view.setComments(dto.getComments());
			view.setDescount(NumberUtil.convertQuantity(dto.getAmount()));
			view.setDate(TimeUtils.convertJavaDateToStringDate(dto.getDate(), TimeUtils.DATE_PATTERN_DDMMYYYY));
			CondominiumsConverter condominiumsConverter = new CondominiumsConverter();
			if(dto.getCondominiumsDTO() != null){
				CondominiumsView conView = condominiumsConverter.convertDTOToView(dto.getCondominiumsDTO());
				view.setCondominiumsView(conView);
			}
			if(dto.getReceiptsItemDTO() != null){
				view.setReceiptsItemView(convertDTOToView(dto.getReceiptsItemDTO()));				
			}
			
		}
		return view;
	}
	
	public ReceiptsDTO convertViewToDto(ReceiptsView view){
		ReceiptsDTO dto = null;
		if(view != null){
			dto = new ReceiptsDTO();
			if(view.getId() != null){
				if(!view.getId().equals("")){
					dto.setId(Integer.parseInt(view.getId()));
				}
			}
			dto.setAmount(NumberUtil.parseDouble(view.getAmount()));
			dto.setComments(view.getComments());
			dto.setDate(TimeUtils.convertStringToDate(view.getDate(), TimeUtils.DATE_PATTERN_DDMMYYYY));
			/*if(view.getDate() != null){				
				dto.setDate(TimeUtils.convertStringToDate(view.getDate(), TimeUtils.DATE_PATTERN_DDMMYYYY));				
			}else{
				dto.setDate(new Date());
			}*/
			dto.setCatId(Integer.parseInt(view.getReceiptsItemView().getId()));
			dto.setCondId(Integer.parseInt(view.getCondominiumsView().getId()));		
		}
		return dto;
	}
	
	public ReceiptsItemDTO convertViewItemToDTO(ReceiptsItemView view){
		ReceiptsItemDTO dto = null;
		if(view != null){
			dto = new ReceiptsItemDTO();
		}
		return dto;
	}
	
	
	public ReceiptsItemView convertDTOToView(ReceiptsItemDTO dto){
		ReceiptsItemView view = null;
		if(dto != null){
			view = new ReceiptsItemView();
			view.setId(String.valueOf(dto.getId()));
			view.setDescription(dto.getDescription());
		}
		return view;
	}
	
	public List<ReceiptsView> convertReceiptsDTOsToViews(List<ReceiptsDTO> dtoList){
		List<ReceiptsView> viewList = new ArrayList<ReceiptsView>(0);
		if(dtoList != null){
			for(ReceiptsDTO dto : dtoList){
				ReceiptsView view = new ReceiptsView();
				view = this.convertDtoToView(dto);
				viewList.add(view);
			}		
		}
		return viewList;
	}	
	
	public ReceiptsReportView convertReceiptsReportDTOToView(ReceiptsReportDTO dto){
		ReceiptsReportView view = null;
		if(dto != null){
			view = new ReceiptsReportView();
			view.setMonthName(StringUtils.getMonth(dto.getMonth()));
			view.setAmount(NumberUtil.convertQuantity(dto.getAmount()));
			view.setDescription(dto.getDescription());
		}
		return view;
	}
	
	public List<ReceiptsReportView> convertReceiptsReportDTOsToViews(List<ReceiptsReportDTO> dtos){
		List<ReceiptsReportView> list = new ArrayList<ReceiptsReportView>(0);
		if(dtos != null){
			if(!dtos.isEmpty()){
				for (ReceiptsReportDTO dto : dtos) {
					ReceiptsReportView view = this.convertReceiptsReportDTOToView(dto);
					list.add(view);
				}
			}
		}
		return list;
	}
	
}
