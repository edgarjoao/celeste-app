package com.condominium.cumulative.service;

import java.util.List;

import com.condominium.cumulative.exception.CumulativeException;
import com.condominium.cumulative.view.CumulativeView;


public interface CumulativeService {
	
	public void generateCumulativeMonth() throws CumulativeException;
	
	public void generateCumulativeMonth(int month, int year) throws CumulativeException;
	
	public void deleteCumulative(int id) throws CumulativeException;
	
	public List<CumulativeView> getCumulativeList() throws CumulativeException;
	
	public boolean existCumulative(int month, int year) throws CumulativeException;
}
