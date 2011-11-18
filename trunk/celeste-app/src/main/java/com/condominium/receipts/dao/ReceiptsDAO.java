package com.condominium.receipts.dao;

import java.util.List;

import com.condominium.receipts.dto.ReceiptsDTO;
import com.condominium.receipts.dto.ReceiptsItemDTO;
import com.condominium.receipts.exception.ReceiptsException;

public interface ReceiptsDAO {

	public List<ReceiptsDTO> getReceiptsDTOList() throws ReceiptsException;
	
	public List<ReceiptsItemDTO> getReceiptsItemDTOList() throws ReceiptsException;
		
}
