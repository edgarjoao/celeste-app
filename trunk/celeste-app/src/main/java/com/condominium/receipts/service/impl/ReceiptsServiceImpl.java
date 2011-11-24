package com.condominium.receipts.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominium.receipts.converter.ReceiptsConverter;
import com.condominium.receipts.dao.ReceiptsDAO;
import com.condominium.receipts.dto.ReceiptsItemDTO;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.service.ReceiptsService;
import com.condominium.receipts.view.ReceiptsItemView;
import com.condominium.receipts.view.ReceiptsView;
/**
 * 
 * @author rioslore
 *
 */
@Service("receiptsService")
public class ReceiptsServiceImpl implements ReceiptsService {

	@Autowired
	ReceiptsDAO receiptsDAO;
		
	public List<SelectItem> getReceiptsItemsList() throws ReceiptsException {				
		List<SelectItem> selectItems = null;
		try{		
			List<ReceiptsItemDTO> itemsList = receiptsDAO.getReceiptsItemDTOList();
			selectItems = new ArrayList<SelectItem>(0);
			SelectItem item = null;			
			for(ReceiptsItemDTO dto : itemsList){
				item = new SelectItem(String.valueOf(dto.getId()), dto.getDescription());
				selectItems.add(item);
			}
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}
		
		return selectItems;
	}

	public void insertReceipt(ReceiptsView view) throws ReceiptsException {
		try{
			ReceiptsConverter converter = new ReceiptsConverter();
			receiptsDAO.insertReceiptDTO(converter.convertViewToDto(view));
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}		
	}

	public void updateReceipt(ReceiptsView view) throws ReceiptsException {
		try{
			ReceiptsConverter converter = new ReceiptsConverter();
			receiptsDAO.updateReceiptDTO(converter.convertViewToDto(view));
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}	
	}

	public void deleteReceipt(int id) throws ReceiptsException {
		try{			
			receiptsDAO.deleteReceiptDTO(id);
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}		
	}

	public void insertReceiptItem(ReceiptsItemView view) throws ReceiptsException {
		try{			
			ReceiptsConverter converter = new ReceiptsConverter();
			receiptsDAO.insertReceiptItemDTO(converter.convertViewItemToDTO(view));
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}
	}

	public void updateReceiptItem(ReceiptsItemView view) throws ReceiptsException {
		try{			
			ReceiptsConverter converter = new ReceiptsConverter();
			receiptsDAO.updateReceiptItemDTO(converter.convertViewItemToDTO(view));
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}
	}

	public void deleteReceiptItem(int id) throws ReceiptsException {
		try{						
			receiptsDAO.deleteReceiptItemDTO(id);
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}		
	}
	
}
