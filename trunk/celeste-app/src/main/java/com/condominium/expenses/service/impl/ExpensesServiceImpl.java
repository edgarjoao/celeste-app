package com.condominium.expenses.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominium.expenses.converter.ExpensesConverter;
import com.condominium.expenses.dao.ExpensesDAO;
import com.condominium.expenses.dto.ExpensesItemDTO;
import com.condominium.expenses.dto.SuppliersDTO;
import com.condominium.expenses.exception.ExpensesException;
import com.condominium.expenses.service.ExpensesService;
import com.condominium.expenses.view.ExpensesItemView;
import com.condominium.expenses.view.ExpensesReportView;
import com.condominium.expenses.view.ExpensesView;
import com.condominium.expenses.view.SuppliersView;
/**
 * 
 * @author rioslore
 *
 */
@Service("expensesService")
public class ExpensesServiceImpl implements ExpensesService {

	@Autowired
	ExpensesDAO expensesDAO;
	
	public List<ExpensesView> getExpensesList() throws ExpensesException {
		List<ExpensesView> list = null;
		try{
			ExpensesConverter converter = new ExpensesConverter();
			list = converter.convertExpensesDTOsToViews(expensesDAO.getExpensesList());			
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_LISTS);
			throw expensesException;
		}		
		return list;
	}
	
	public List<SuppliersView> getSuppliersList() throws ExpensesException{
		List<SuppliersView> list = null;
		try{
			ExpensesConverter converter = new ExpensesConverter();
			list = converter.convertSuppliersDTOsToViews(expensesDAO.getSuppliersList());
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_LISTS);
			throw expensesException;
		}				
		return list;
	}
	
	public void addSupplier(SuppliersView view) throws ExpensesException{
		try{
			ExpensesConverter converter = new ExpensesConverter();
			expensesDAO.addSupplier(converter.convertSuppliersViewToDTO(view));			
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_INSERT);
			throw expensesException;
		}	
	}
	
	public List<SelectItem> getSuppliersItems() throws ExpensesException{
		List<SelectItem> items = null;
		try{
			List<SuppliersDTO> dtos = expensesDAO.getSuppliersList();
			items = new ArrayList<SelectItem>(0);
			items.add(new SelectItem("-1", "- Todos -"));
			if(dtos != null){				
				for (SuppliersDTO dto : dtos) {
					SelectItem item = new SelectItem(String.valueOf(dto.getProvId()), dto.getName());
					items.add(item);
				}
			}
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_LISTS);
			throw expensesException;
		}	
		return items;
	}
	
	public List<SelectItem> getExpensesItems() throws ExpensesException{
		List<SelectItem> items = null;
		try{
			List<ExpensesItemDTO> dtos = expensesDAO.getExpensesItemList();
			if(dtos != null){
				items = new ArrayList<SelectItem>(0);	
				items.add(new SelectItem("-1", "- Todos -"));
				for (ExpensesItemDTO dto : dtos) {
					SelectItem item = new SelectItem(String.valueOf(dto.getId()), dto.getDescription());
					items.add(item);
				}
			}
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_LISTS);
			throw expensesException;
		}	
		return items;
	}

	public void addExpensesItem(ExpensesItemView view) throws ExpensesException {
		try{
			ExpensesConverter converter = new ExpensesConverter();
			expensesDAO.addExpensesItem(converter.convertExpensesItemViewToDTO(view));			
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_INSERT);
			throw expensesException;
		}			
	}

	public void addExpenses(ExpensesView view) throws ExpensesException {
		try{
			ExpensesConverter converter = new ExpensesConverter();
			expensesDAO.addExpenses(converter.convertExpensesViewToDTO(view));			
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_INSERT);
			throw expensesException;
		}	
	}

	public void editSupplier(SuppliersView view) throws ExpensesException {
		try{
			ExpensesConverter converter = new ExpensesConverter();
			expensesDAO.editSupplierDTO(converter.convertSuppliersViewToDTO(view));				
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_UPDATE);
			throw expensesException;
		}			
	}

	public void deleteSupplier(int id) throws ExpensesException {
		try{			
			expensesDAO.deleteSupplierDTO(id);			
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_UPDATE);
			throw expensesException;
		}		
	}

	public List<ExpensesView> searchExpensesList(int month, int year, int provId, int cateId) throws ExpensesException {
		List<ExpensesView> list = null;
		try{
			ExpensesConverter converter = new ExpensesConverter();
			list = converter.convertExpensesDTOsToViews(expensesDAO.searchExpensesList(month, year, provId, cateId));			
		}catch(ExpensesException e){
			list = new ArrayList<ExpensesView>(0);
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_LISTS);
			throw expensesException;
		}		
		return list;
	}

	public void editExpenses(ExpensesView view) throws ExpensesException {
		try{
			ExpensesConverter converter = new ExpensesConverter();
			expensesDAO.editExpensesDTO(converter.convertExpensesViewToDTO(view));			
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_UPDATE);
			throw expensesException;
		}			
	}

	public void deleteExpenses(int id) throws ExpensesException {
		try{			
			expensesDAO.deleteExpensesDTO(id);			
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_DELETE);
			throw expensesException;
		}		
	}

	public List<ExpensesReportView> getExpensesReport(int month, int year) throws ExpensesException {
		List<ExpensesReportView> list = new ArrayList<ExpensesReportView>();
		try{
			ExpensesConverter converter = new ExpensesConverter();
			list = converter.convertExpensesReportDTOsToViews(this.expensesDAO.getExpensesReportDTO(month, year));
		}catch(ExpensesException e){
			ExpensesException expensesException = new ExpensesException(e, ExpensesException.LAYER_SERVICE, ExpensesException.ACTION_SELECT);
			throw expensesException;
		}		
		return list;
	}
	
}
