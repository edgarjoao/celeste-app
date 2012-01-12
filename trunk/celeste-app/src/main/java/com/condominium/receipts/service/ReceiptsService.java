package com.condominium.receipts.service;

import java.util.List;

import javax.faces.model.SelectItem;

import com.condominium.condom.view.CondominiumsView;
import com.condominium.events.view.EventsView;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.view.ReceiptsItemView;
import com.condominium.receipts.view.ReceiptsReportView;
import com.condominium.receipts.view.ReceiptsView;

public interface ReceiptsService {
	
	public List<SelectItem> getReceiptsItemsList() throws ReceiptsException;
	
	public void insertReceiptItem(ReceiptsItemView view) throws ReceiptsException;
	
	public void updateReceiptItem(ReceiptsItemView view) throws ReceiptsException;
	
	public void deleteReceiptItem(int id) throws ReceiptsException;
	
	public void insertReceipt(ReceiptsView view) throws ReceiptsException;
	
	public void updateReceipt(ReceiptsView view) throws ReceiptsException;

	public void deleteReceipt(int id) throws ReceiptsException;
	
	public List<ReceiptsView> getReceiptsList() throws ReceiptsException;
	
	public List<ReceiptsView> getSearchReceiptsList(int month, int year, int catId, int condId) throws ReceiptsException;
	
	public List<ReceiptsReportView> receiptsReportByMonth(int month, int year) throws ReceiptsException;
	
	public String totalPrevious(int month, int year) throws ReceiptsException;
	
	public List<CondominiumsView> getDebtsOfTheMonth(int month, int year) throws ReceiptsException;
	
	public List<EventsView> getEventsList(int month, int year) throws ReceiptsException;
	
	public void insertEventView(EventsView view) throws ReceiptsException;
	
	public void deleteEventView(int id) throws ReceiptsException;
	
	public void editEventView(EventsView view) throws ReceiptsException;
	
	public EventsView getEventEventsViewById(int id) throws ReceiptsException;
		
}
