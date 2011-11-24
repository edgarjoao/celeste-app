package com.condominium.receipts.service;

import java.util.List;

import javax.faces.model.SelectItem;

import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.view.ReceiptsItemView;
import com.condominium.receipts.view.ReceiptsView;

public interface ReceiptsService {
	
	public List<SelectItem> getReceiptsItemsList() throws ReceiptsException;
	
	public void insertReceiptItem(ReceiptsItemView view) throws ReceiptsException;
	
	public void updateReceiptItem(ReceiptsItemView view) throws ReceiptsException;
	
	public void deleteReceiptItem(int id) throws ReceiptsException;
	
	public void insertReceipt(ReceiptsView view) throws ReceiptsException;
	
	public void updateReceipt(ReceiptsView view) throws ReceiptsException;

	public void deleteReceipt(int id) throws ReceiptsException;
}
