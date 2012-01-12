package com.condominium.expenses.dao;

import java.util.List;

import com.condominium.expenses.dto.ExpensesDTO;
import com.condominium.expenses.dto.ExpensesItemDTO;
import com.condominium.expenses.dto.ExpensesReportDTO;
import com.condominium.expenses.dto.SuppliersDTO;
import com.condominium.expenses.exception.ExpensesException;

public interface ExpensesDAO {

	public List<ExpensesDTO> getExpensesList() throws ExpensesException;
	
	public List<ExpensesItemDTO> getExpensesItemList() throws ExpensesException;
	
	public List<SuppliersDTO> getSuppliersList() throws ExpensesException;
	
	public void addExpenses(ExpensesDTO dto) throws ExpensesException;

	public void addSupplier(SuppliersDTO dto) throws ExpensesException;
	
	public void addExpensesItem(ExpensesItemDTO dto) throws ExpensesException;
	
	public void editSupplierDTO(SuppliersDTO dto) throws ExpensesException;
	
	public void deleteSupplierDTO(int id) throws ExpensesException;
			
	public List<ExpensesDTO> searchExpensesList(int month, int year, int provId, int cateId) throws ExpensesException;
	
	public void editExpensesDTO(ExpensesDTO dto) throws ExpensesException;
	
	public void deleteExpensesDTO(int id) throws ExpensesException;
	
	public List<ExpensesReportDTO>  getExpensesReportDTO(int month, int year) throws ExpensesException;	
	
}
