package com.condominium.cumulative.converter;

import java.util.ArrayList;
import java.util.List;

import com.condominium.common.utils.NumberUtil;
import com.condominium.common.utils.StringUtils;
import com.condominium.cumulative.dto.CumulativeDTO;
import com.condominium.cumulative.view.CumulativeView;
/**
 * 
 * @author rioslore
 *
 */
public class CumulativeConverter {

	public CumulativeView convertDTOToView(CumulativeDTO dto){
		CumulativeView view = null;
		if(dto != null){
			view = new CumulativeView();
			view.setId(String.valueOf(dto.getId()));
			view.setMonth(StringUtils.getMonth(dto.getMonth()));
			view.setYear(String.valueOf(dto.getYear()));
			view.setAmount(NumberUtil.convertQuantity(dto.getAmount()));
		}
		return view;
	}
	
	public List<CumulativeView> convertDTOsToViews(List<CumulativeDTO> dtos){
		List<CumulativeView>  list = new ArrayList<CumulativeView>(0);
		if(!dtos.isEmpty()){
			for (CumulativeDTO dto : dtos) {
				list.add(this.convertDTOToView(dto));
			}
		}
		return list;
	}
}
