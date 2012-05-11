package com.condominium.receipts.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominium.common.utils.NumberUtil;
import com.condominium.common.utils.StringUtils;
import com.condominium.common.utils.TimeUtils;
import com.condominium.condom.converter.CondominiumsConverter;
import com.condominium.condom.view.CondominiumsView;
import com.condominium.events.converter.EventsConverter;
import com.condominium.events.dto.EventsDTO;
import com.condominium.events.view.EventsView;
import com.condominium.receipts.converter.ReceiptsConverter;
import com.condominium.receipts.dao.ReceiptsDAO;
import com.condominium.receipts.dto.ReceiptsDTO;
import com.condominium.receipts.dto.ReceiptsItemDTO;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.service.ReceiptsService;
import com.condominium.receipts.view.ReceiptsItemView;
import com.condominium.receipts.view.ReceiptsReportView;
import com.condominium.receipts.view.ReceiptsView;
import com.condominium.reports.generalreport.view.GeneralReceiptsReportView;
import com.condominium.reports.generalreport.view.GeneralReportView;
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
			selectItems.add(new SelectItem("-1", "- Seleccionar -"));
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
			ReceiptsDTO dto = converter.convertViewToDto(view);
			if(receiptsDAO.verifyIfExistBeforeInsert(dto)){			
				throw new ReceiptsException("Los datos que usted intenta ingresar ya existen.", 
							ReceiptsException.LAYER_SERVICE, ReceiptsException.ACTION_INSERT);
			}else{
				receiptsDAO.insertReceiptDTO(dto);
			}
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}		
	}

	public void updateReceipt(ReceiptsView view) throws ReceiptsException {
		try{
			ReceiptsConverter converter = new ReceiptsConverter();
			ReceiptsDTO dto = converter.convertViewToDto(view);
			if(receiptsDAO.verifyIfExistBeforeInsert(dto)){			
				throw new ReceiptsException("Los datos que usted intenta actualizar ya existen.", 
							ReceiptsException.LAYER_SERVICE, ReceiptsException.ACTION_INSERT);
			}else{
				receiptsDAO.updateReceiptDTO(dto);
			}
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

	public List<ReceiptsView> getReceiptsList() throws ReceiptsException {
		List<ReceiptsView> list = null;
		try{
			ReceiptsConverter converter = new ReceiptsConverter();			
			list = converter.convertReceiptsDTOsToViews(receiptsDAO.getReceiptsDTOList());
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}		
		return list;
	}

	public List<ReceiptsView> getSearchReceiptsList(int month, int year, int catId, int condId) throws ReceiptsException {
		List<ReceiptsView> list = null;				
		try{
			ReceiptsConverter converter = new ReceiptsConverter();			
			list = converter.convertReceiptsDTOsToViews(receiptsDAO.getSearchReceiptsDTOList(month, year, catId, condId));
		}catch(ReceiptsException receiptsException){
			list = new ArrayList<ReceiptsView>(0);
			throw receiptsException;
		}		
		return list;			
	}

	public List<ReceiptsReportView> receiptsReportByMonth(int month, int year) throws ReceiptsException {
		List<ReceiptsReportView> list = new ArrayList<ReceiptsReportView>(0);
		try{
			ReceiptsConverter converter = new ReceiptsConverter();
			list = converter.convertReceiptsReportDTOsToViews(receiptsDAO.receiptsReportByMonth(month, year));
		}catch(ReceiptsException receiptsException){			
			throw receiptsException;
		}	
		return list;
	}

	public String totalPrevious(int month, int year) throws ReceiptsException {
		String ptotal = null;
		try{
			Calendar cal = Calendar.getInstance();		
			cal.set(year, month, 1);
			cal.add(Calendar.MONTH, -2);			
			int m = cal.get(Calendar.MONTH) + 1;
			int y = cal.get(Calendar.YEAR);		
			double previousTotal = receiptsDAO.totalPrevious(m, y);			
			ptotal = NumberUtil.convertQuantity(previousTotal);			
		}catch(ReceiptsException receiptsException){			
			throw receiptsException;
		}
		return ptotal;
	}

	public List<CondominiumsView> getDebtsOfTheMonth(int month, int year) throws ReceiptsException {
		List<CondominiumsView> list = new ArrayList<CondominiumsView>(0);
		try{
			CondominiumsConverter converter = new CondominiumsConverter();
			list = converter.convertCondominiumsDTOsToViews(receiptsDAO.getDebtsOfTheMonth(month, year));
		}catch(ReceiptsException receiptsException){			
			throw receiptsException;
		}	
		return list;
	}
	
	public List<EventsView> getEventsList(int month, int year) throws ReceiptsException {
		List<EventsView> list = new ArrayList<EventsView>();
		try{
			EventsConverter converter = new EventsConverter();
			list = converter.convertDTOsToViews(receiptsDAO.getEventsDTOs(month, year));
		}catch(ReceiptsException e){
			ReceiptsException expensesException = new ReceiptsException(e, ReceiptsException.LAYER_SERVICE, ReceiptsException.ACTION_SELECT);
			throw expensesException;
		}
		return list;
	}

	public void insertEventView(EventsView view) throws ReceiptsException {
		try{
			EventsConverter converter = new EventsConverter();
			EventsDTO dto = converter.convertViewToDTO(view); 			
			if(receiptsDAO.validateBeforeReserv(view.getDay(), view.getMonth() + 1, view.getYear())){				
				throw new ReceiptsException("No se puede guardar porque ya existe una reservacion para el dia " + TimeUtils.convertJavaDateToStringDate(dto.getDate(), TimeUtils.DATE_PATTERN_DDMMYYYY), 
						ReceiptsException.LAYER_SERVICE, ReceiptsException.ACTION_INSERT);
			}else{
				receiptsDAO.insertEventDTO(dto);
			}
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}		
	}

	public void deleteEventView(int id) throws ReceiptsException {
		try{						
			receiptsDAO.deleteEventDTO(id);
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}		
	}

	public void editEventView(EventsView view) throws ReceiptsException {
		try{			
			EventsConverter converter = new EventsConverter();
			EventsDTO dto = converter.convertViewToDTO(view); 			
			/*if(receiptsDAO.validateBeforeReserv(view.getDay(), view.getMonth() + 1, view.getYear())){				
				throw new ReceiptsException("No se puede guardar porqueo ya existe una reservacion para el dia " + TimeUtils.convertJavaDateToStringDate(dto.getDate(), TimeUtils.DATE_PATTERN_DDMMYYYY), 
						ReceiptsException.LAYER_SERVICE, ReceiptsException.ACTION_INSERT);
			}else{*/				
				receiptsDAO.editEventDTO(dto);
			//}													
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}
	}

	public EventsView getEventEventsViewById(int id) throws ReceiptsException {
		EventsView view = null;
		try{			
			EventsConverter converter = new EventsConverter();
			view = converter.convertDTOToView(receiptsDAO.getEventDTOById(id));			
		}catch(ReceiptsException receiptsException){
			throw receiptsException;
		}
		return view;
	}

	public void generateCumulativeMonth() throws ReceiptsException {
		
		
	}
	
	public GeneralReceiptsReportView getGeneralReport(String initMonth, String initYear, String endMonth, String endYear,
			String category) throws ReceiptsException {

		GeneralReceiptsReportView gReportView = new GeneralReceiptsReportView();
		String strdate = TimeUtils.buildStringDate("01",initMonth, initYear);  //"01/10/2011";// any start date
		String enddate = TimeUtils.buildStringDate("01",endMonth, endYear);//"01/05/2012";// any end date
		SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");				
		Date st = null;
		Date ed = null;
		try {
			st = formatterDate.parse(strdate);
			ed = formatterDate.parse(enddate);
		} catch (ParseException e) {			
			ReceiptsException rException = 
						new ReceiptsException("Formato de Fecha invalido.", ReceiptsException.LAYER_SERVICE, ReceiptsException.ACTION_LISTS);
			throw rException;
		}
		Calendar ss = Calendar.getInstance();
		Calendar ee = Calendar.getInstance();

		ss.setTime(st);
		ee.setTime(ed);
		ee.add(Calendar.MONTH, 1);//Adding a month
		List<GeneralReportView> gViews = new ArrayList<GeneralReportView>(0);
		List<Double> totalAmountPeriod = new ArrayList<Double>(0);
		while (!ss.equals(ee)) {			
			int month = ss.get(Calendar.MONTH) + 1;
			int year = ss.get(Calendar.YEAR);		
			ss.add(Calendar.MONTH, 1);
			GeneralReportView generalReportView = new GeneralReportView();
			//Adding period
			generalReportView.setRangeDate(StringUtils.getMonth(month) +" " + year);
			generalReportView.setAmount(receiptsDAO.getGeneralReportAmount(month, year, Integer.parseInt(category)));
			//Adding total Amount
			double totalAP = ReceiptsServiceImpl.sum(generalReportView.getAmount());
			totalAmountPeriod.add(new Double(totalAP));
			String totalAmount = NumberUtil.convertQuantity(totalAP);
			generalReportView.setTotalAmount(totalAmount);
			gViews.add(generalReportView);						
		}
		gReportView.setGeneralReportViews(gViews);		
		List<CondominiumsView> list = new ArrayList<CondominiumsView>(0);
		try{
			CondominiumsConverter converter = new CondominiumsConverter();
			list = converter.convertCondominiumsDTOsToViews(receiptsDAO.getGeneralReportCondominiums(Integer.parseInt(initMonth), Integer.parseInt(initYear), Integer.parseInt(category)));
			gReportView.setCondominiumsViews(list);
		}catch(ReceiptsException receiptsException){			
			throw receiptsException;
		}
		//Adding Catalog Description
		if(!list.isEmpty()){
			gReportView.setCatalogDescription(list.get(0).getDebDescription());
		}		
		//Adding total Amount Period
		if(!totalAmountPeriod.isEmpty()){
			gReportView.setTotalAmountPeriod(NumberUtil.convertQuantity(ReceiptsServiceImpl.sum(totalAmountPeriod)));
		}		
		return gReportView;
	}
	
	public static double sum(List<Double> list) {
	     double sum= 0; 
	     for (Double i:list){
	         sum = sum + i;
	     }
	     return sum;
	}
	
}
