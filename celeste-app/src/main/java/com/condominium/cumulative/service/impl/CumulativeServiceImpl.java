package com.condominium.cumulative.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominium.cumulative.converter.CumulativeConverter;
import com.condominium.cumulative.dao.CumulativeDAO;
import com.condominium.cumulative.dto.CumulativeDTO;
import com.condominium.cumulative.exception.CumulativeException;
import com.condominium.cumulative.service.CumulativeService;
import com.condominium.cumulative.view.CumulativeView;
/**
 * 
 * @author rioslore
 *
 */
@Service("cumulativeService")
public class CumulativeServiceImpl implements CumulativeService {

	@Autowired
	CumulativeDAO cumulativeDAO;
	
	public void generateCumulativeMonth() throws CumulativeException {
		try{
 			Calendar cal = Calendar.getInstance();
			CumulativeDTO dto = cumulativeDAO.getCurrentCumulative();
			cal.set(dto.getYear(), dto.getMonth(), 1);
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);		
			/*if(cumulativeDAO.existCumulative(dto.getMonth(), dto.getYear())){
				CumulativeException cumulativeException = new CumulativeException("Ya existe un acumulado para el mes [ " + month + " ] y año [ " + year + " ]", 
											CumulativeException.LAYER_SERVICE, CumulativeException.ACTION_SELECT);	
				throw cumulativeException;
			}*/			
			double totalPrevious = cumulativeDAO.totalPreviousCumulative(month, year);
			double cumulative = dto.getAmount() + totalPrevious;
			cumulativeDAO.insertCumulative(month, year, cumulative);
		}catch(CumulativeException c){
			CumulativeException cumulativeException = new CumulativeException(c, CumulativeException.LAYER_SERVICE, CumulativeException.ACTION_SELECT);	
			throw cumulativeException;
		}		
	}

	public void deleteCumulative(int id) throws CumulativeException {
		try{
			cumulativeDAO.deleteCumulative(id);
		}catch(CumulativeException c){
			CumulativeException cumulativeException = new CumulativeException(c, CumulativeException.LAYER_SERVICE, CumulativeException.ACTION_SELECT);	
			throw cumulativeException;
		}		
	}

	public List<CumulativeView> getCumulativeList() throws CumulativeException {
		List<CumulativeView> list = new ArrayList<CumulativeView>(0);
		try{
			CumulativeConverter converter = new CumulativeConverter();
			list = converter.convertDTOsToViews(cumulativeDAO.getCumulativeList());
		}catch(CumulativeException c){
			CumulativeException cumulativeException = new CumulativeException(c, CumulativeException.LAYER_SERVICE, CumulativeException.ACTION_SELECT);	
			throw cumulativeException;
		}
		return list;
	}

	public boolean existCumulative(int month, int year) throws CumulativeException {
		boolean result = false;
		try{
			result = cumulativeDAO.existCumulative(month, year);
		}catch(CumulativeException c){
			CumulativeException cumulativeException = new CumulativeException(c, CumulativeException.LAYER_SERVICE, CumulativeException.ACTION_SELECT);	
			throw cumulativeException;
		}
		return result;
	}

	public void generateCumulativeMonth(int month, int year) throws CumulativeException {
		try{
 			Calendar cal = Calendar.getInstance();			
			cal.set(year, month, 1);		
			cal.add(Calendar.MONTH, -2);
			int m = cal.get(Calendar.MONTH) + 1;
			int y = cal.get(Calendar.YEAR);
			double amount = 0;
			try{
				CumulativeDTO dto = cumulativeDAO.getCumulative(m, y);
				amount = dto.getAmount();
			}catch(CumulativeException c){}
			double totalPrevious = cumulativeDAO.totalPreviousCumulative(month, year);
			double cumulative = amount + totalPrevious;
			cumulativeDAO.insertCumulative(month, year, cumulative);
		}catch(CumulativeException c){
			CumulativeException cumulativeException = new CumulativeException(c, CumulativeException.LAYER_SERVICE, CumulativeException.ACTION_SELECT);	
			throw cumulativeException;
		}		
		
	}

}
