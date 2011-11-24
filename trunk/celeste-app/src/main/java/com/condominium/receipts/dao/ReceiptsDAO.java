package com.condominium.receipts.dao;

import java.util.List;

import com.condominium.receipts.dto.ReceiptsDTO;
import com.condominium.receipts.dto.ReceiptsItemDTO;
import com.condominium.receipts.exception.ReceiptsException;

public interface ReceiptsDAO {

	public List<ReceiptsDTO> getReceiptsDTOList() throws ReceiptsException;
	
	public List<ReceiptsItemDTO> getReceiptsItemDTOList() throws ReceiptsException;
	
	public void insertReceiptDTO(ReceiptsDTO dto) throws ReceiptsException;
		
	public void updateReceiptDTO(ReceiptsDTO dto) throws ReceiptsException;
	
	public void deleteReceiptDTO(int id) throws ReceiptsException;
	
	public void insertReceiptItemDTO(ReceiptsItemDTO dto) throws ReceiptsException;
	
	public void updateReceiptItemDTO(ReceiptsItemDTO dto) throws ReceiptsException;
	
	public void deleteReceiptItemDTO(int id) throws ReceiptsException;
}
