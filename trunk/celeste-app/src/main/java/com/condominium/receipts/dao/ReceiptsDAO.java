package com.condominium.receipts.dao;

import java.util.List;

import com.condominium.condom.dto.CondominiumsDTO;
import com.condominium.events.dto.EventsDTO;
import com.condominium.receipts.dto.ReceiptsDTO;
import com.condominium.receipts.dto.ReceiptsItemDTO;
import com.condominium.receipts.dto.ReceiptsReportDTO;
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
	
	public List<ReceiptsDTO> getSearchReceiptsDTOList(int month, int year, int catId, int condId) throws ReceiptsException;
	
	public boolean verifyIfExistBeforeInsert(ReceiptsDTO dto) throws ReceiptsException;
	
	public List<ReceiptsReportDTO> receiptsReportByMonth(int month, int year) throws ReceiptsException;
	
	public double totalPrevious(int month, int year) throws ReceiptsException;
	
	public List<CondominiumsDTO> getDebtsOfTheMonth(int month, int year) throws ReceiptsException;
	
	public List<EventsDTO> getEventsDTOs(int month, int year) throws ReceiptsException;
	
	public void insertEventDTO(EventsDTO dto) throws ReceiptsException;
	
	public void deleteEventDTO(int id) throws ReceiptsException;
	
	public void editEventDTO(EventsDTO dto) throws ReceiptsException;
	
	public EventsDTO getEventDTOById(int id) throws ReceiptsException;
	
	public boolean validateBeforeReserv(int day, int month, int year) throws ReceiptsException;
	
	public List<Double> getGeneralReportAmount(int month, int year, int category) throws ReceiptsException;
	
	public List<CondominiumsDTO> getGeneralReportCondominiums(int month, int year, int category) throws ReceiptsException;
		
}
