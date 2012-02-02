package com.condominium.expenses.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.condominium.common.utils.NumberUtil;
import com.condominium.common.utils.TimeUtils;
import com.condominium.expenses.dto.ExpensesDTO;
import com.condominium.expenses.dto.ExpensesDetailReportDTO;
import com.condominium.expenses.dto.ExpensesItemDTO;
import com.condominium.expenses.dto.ExpensesReportDTO;
import com.condominium.expenses.dto.SuppliersDTO;
import com.condominium.expenses.view.ExpensesDetailReportView;
import com.condominium.expenses.view.ExpensesItemView;
import com.condominium.expenses.view.ExpensesReportView;
import com.condominium.expenses.view.ExpensesView;
import com.condominium.expenses.view.SuppliersView;
/**
 * 
 * @author rioslore
 *
 */
public class ExpensesConverter {

	public ExpensesView convertExpensesDTOToView(ExpensesDTO dto){
		ExpensesView view = null;
		if(dto != null){
			view = new ExpensesView();			
			view.setId(String.valueOf(dto.getId()));
			view.setAmount(NumberUtil.convertQuantity(dto.getAmount()));
			view.setComments(dto.getComments());
			view.setDate(TimeUtils.convertJavaDateToStringDate(dto.getDate(), TimeUtils.DATE_PATTERN_DDMMYYYY));
			if(dto.getExpensesItemDTO() != null){			
				view.setExpensesItemView(this.convertExpensesItemDTOToView(dto.getExpensesItemDTO()));
			}
			if(dto.getSuppliersDTO() != null){
				view.setSuppliersView(this.convertSuppliersDTOToView(dto.getSuppliersDTO()));
			}
			view.setUserId(dto.getUserId());
		}
		return view;
	}
	
	public ExpensesDTO convertExpensesViewToDTO(ExpensesView view){
		ExpensesDTO dto = null;
		if(view != null){
			dto = new ExpensesDTO();
			if(view.getId() != null){
				if(!view.getId().equals("")){
					dto.setId(Integer.parseInt(view.getId()));
				}
			}			
			dto.setAmount(NumberUtil.parseDouble(view.getAmount()));
			dto.setComments(view.getComments());
			dto.setDate(new Date());
			if(view.getExpensesItemView() != null){
				dto.setExpensesItemDTO(convertExpensesItemViewToDTO(view.getExpensesItemView()));				
			}
			if(view.getSuppliersView() != null){
				dto.setSuppliersDTO(convertSuppliersViewToDTO(view.getSuppliersView()));
			}
			dto.setUserId(view.getUserId());
		}
		return dto;
	}
	
	public List<ExpensesView> convertExpensesDTOsToViews(List<ExpensesDTO> dtos){
		List<ExpensesView> list = new ArrayList<ExpensesView>(0);		
		if(dtos != null){
			if(!dtos.isEmpty()){
				list = new ArrayList<ExpensesView>(0);
				for(ExpensesDTO dto : dtos){
					ExpensesView view = convertExpensesDTOToView(dto);
					list.add(view);
				}
			}			
		}		
		return list;
	}
	
	
	public SuppliersView convertSuppliersDTOToView(SuppliersDTO dto){
		SuppliersView view = null;
		if(dto != null){
			view = new SuppliersView();
			view.setProvId(String.valueOf(dto.getProvId()));
			view.setName(dto.getName());
			view.setAddress(dto.getAddress());		
			view.setPhoneNumber( dto.getCellPhoneNumber() == null ? "" : dto.getCellPhoneNumber());
			view.setCellPhoneNumber(dto.getCellPhoneNumber() == null ? "" : dto.getCellPhoneNumber());
		}
		return view;
	}
	
	public SuppliersDTO convertSuppliersViewToDTO(SuppliersView view){
		SuppliersDTO dto = null;
		if(view != null){
			dto = new SuppliersDTO();
			dto.setProvId(view.getProvId() == null ? 0 : Integer.parseInt(view.getProvId()));
			dto.setName(view.getName());
			dto.setAddress(view.getAddress());			
			dto.setPhoneNumber( view.getPhoneNumber() == null ? "" : view.getPhoneNumber());
			dto.setCellPhoneNumber( view.getCellPhoneNumber() == null ? "" : view.getCellPhoneNumber());
		}
		return dto;
	}
	
	public List<SuppliersView> convertSuppliersDTOsToViews(List<SuppliersDTO> dtos){
		List<SuppliersView> list = null;
		if(dtos != null){ 
			if(!dtos.isEmpty()){
				list = new ArrayList<SuppliersView>(0);
				for(SuppliersDTO dto : dtos){
					SuppliersView view = convertSuppliersDTOToView(dto);
					list.add(view);					
				}
			}
		}
		return list;
	}
	
	public ExpensesItemView convertExpensesItemDTOToView(ExpensesItemDTO dto){
		ExpensesItemView view = null;
		if(dto != null){
			view = new ExpensesItemView();
			view.setId(String.valueOf(dto.getId()));
			view.setDescription(dto.getDescription());
		}
		return view;
	}
	
	public ExpensesItemDTO convertExpensesItemViewToDTO(ExpensesItemView view){
		ExpensesItemDTO dto = null;
		if(view != null){
			dto = new ExpensesItemDTO();
			dto.setId(Integer.parseInt(view.getId()));
			dto.setDescription(view.getDescription());					
		}
		return dto;
	}
	
	
	public ExpensesReportView convertExpensesDTOToView(ExpensesReportDTO dto){
		ExpensesReportView view = null;
		if(dto != null){
			view = new ExpensesReportView();
			view.setDescription(dto.getDescription());
			view.setAmount(NumberUtil.convertQuantity(dto.getAmount()));
		}
		return view;
	}
	
	public List<ExpensesReportView> convertExpensesReportDTOsToViews(List<ExpensesReportDTO> dtos){
		List<ExpensesReportView> list = new ArrayList<ExpensesReportView>(0);
		if(dtos != null){
			for (ExpensesReportDTO dto : dtos) {
				ExpensesReportView view = this.convertExpensesDTOToView(dto);				
				list.add(view);
			}
		}
		return list;
	}
	
	public ExpensesDetailReportView convertDTOToView(ExpensesDetailReportDTO dto){
		ExpensesDetailReportView view = new ExpensesDetailReportView();
		if(dto != null){	
			view.setComments(dto.getComments());
			view.setAmount(NumberUtil.convertQuantity(dto.getAmount()));
		}
		return view;
	}
	
	public List<ExpensesDetailReportView> convertExpensesRDetailDTOToViews(List<ExpensesDetailReportDTO> dtos){
		List<ExpensesDetailReportView> viewList = new ArrayList<ExpensesDetailReportView>(0);
		if(!dtos.isEmpty()){
			for(ExpensesDetailReportDTO dto : dtos){
				viewList.add(convertDTOToView(dto));
			}
		}
		return viewList;
	}
}
