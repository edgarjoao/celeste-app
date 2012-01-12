package com.condominium.cumulative.dao;

import java.util.List;

import com.condominium.cumulative.dto.CumulativeDTO;
import com.condominium.cumulative.exception.CumulativeException;

/**
 * 
 * @author rioslore
 *
 */
public interface CumulativeDAO {
	
	public CumulativeDTO getCurrentCumulative() throws CumulativeException;
	
	public CumulativeDTO getCumulative(int month, int year) throws CumulativeException;
	
	public double totalPreviousCumulative(int month, int year) throws CumulativeException;
	
	public void insertCumulative(int month, int year, double amount) throws CumulativeException;
	
	public boolean existCumulative(int month, int year) throws CumulativeException;
	
	public void deleteCumulative(int id) throws CumulativeException;
	
	public List<CumulativeDTO> getCumulativeList() throws CumulativeException;
}
