package com.condominium.expenses.dao;

import java.util.List;

import com.condominium.expenses.dto.ExpensesDTO;
import com.condominium.expenses.dto.ExpensesItemDTO;
import com.condominium.expenses.dto.SuppliersDTO;
import com.condominium.expenses.exception.ExpensesException;

public interface ExpensesDAO {

	public List<ExpensesDTO> getExpensesList() throws ExpensesException;
	
	public List<ExpensesItemDTO> getExpensesItemList() throws ExpensesException;
	
	public List<SuppliersDTO> getSuppliersList() throws ExpensesException;
		
}
