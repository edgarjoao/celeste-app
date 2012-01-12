package com.condominium.expenses.service;

import java.util.List;

import javax.faces.model.SelectItem;

import com.condominium.expenses.exception.ExpensesException;
import com.condominium.expenses.view.ExpensesItemView;
import com.condominium.expenses.view.ExpensesReportView;
import com.condominium.expenses.view.ExpensesView;
import com.condominium.expenses.view.SuppliersView;

public interface ExpensesService {
	
	public List<ExpensesView> getExpensesList() throws ExpensesException;
	
	public List<SuppliersView> getSuppliersList() throws ExpensesException;
	
	public void addSupplier(SuppliersView view) throws ExpensesException;
	
	public void addExpensesItem(ExpensesItemView view) throws ExpensesException;
	
	public void addExpenses(ExpensesView view) throws ExpensesException;
	
	public List<SelectItem> getSuppliersItems() throws ExpensesException;
	
	public List<SelectItem> getExpensesItems() throws ExpensesException;	
	
	public void editSupplier(SuppliersView view) throws ExpensesException; 
	
	public void deleteSupplier(int id) throws ExpensesException;
	
	public List<ExpensesView> searchExpensesList(int month, int year, int provId, int cateId) throws ExpensesException;
	
	public void editExpenses(ExpensesView view) throws ExpensesException; 
	
	public void deleteExpenses(int id) throws ExpensesException;
	
	public List<ExpensesReportView> getExpensesReport(int month, int year) throws ExpensesException;
		

}
