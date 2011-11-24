package com.condominium.receipts.converter;

import com.condominium.receipts.dto.ReceiptsDTO;
import com.condominium.receipts.dto.ReceiptsItemDTO;
import com.condominium.receipts.view.ReceiptsItemView;
import com.condominium.receipts.view.ReceiptsView;

public class ReceiptsConverter {

	public ReceiptsView convertDtoToView(ReceiptsDTO dto){
		ReceiptsView view = null;
		if(dto != null){
			view = new ReceiptsView();
			view.setId(String.valueOf(dto.getId()));
			view.setAmount("");
			view.setComments(dto.getComments());
			view.setDescount("");
		}
		return view;
	}
	
	public ReceiptsDTO convertViewToDto(ReceiptsView view){
		ReceiptsDTO dto = null;
		if(view != null){
			dto = new ReceiptsDTO();
			
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
		}
		return view;
	}
	
}
