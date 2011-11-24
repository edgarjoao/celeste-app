package com.condominium.receipts.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.condominium.common.utils.JSFUtil;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.service.ReceiptsService;
import com.condominium.receipts.view.ReceiptsView;
/**
 * 
 * @author rioslore
 *
 */
@ManagedBean(name="receiptsBean")
@ViewScoped
public class ReceiptsManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6145402496921694693L;
	
	@ManagedProperty(value="#{receiptsService}")
	private ReceiptsService receiptsService;
	public String receiptType;	
	public List<ReceiptsView> receiptsViewList;
	public ReceiptsView receiptsView;
	
	public void goAddReceipts(){
		JSFUtil.redirect("registrar_ingreso.jsf");
	}
	
	
	public void init(){
		receiptsViewList = new ArrayList<ReceiptsView>(0);
	}
	
	public void clean(){
		
	}
	
	public List<SelectItem> getReceiptsList(){
		List<SelectItem> list = null;
		try {
			list = receiptsService.getReceiptsItemsList();
		} catch (ReceiptsException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String insertReceipt(){
		try{
			receiptsService.insertReceipt(receiptsView);
		} catch (ReceiptsException e) {
			e.printStackTrace();
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error de Ingresos");
		}
		return "listado_ingresos.jsf";
	}
				
	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public void setReceiptsService(ReceiptsService receiptsService) {
		this.receiptsService = receiptsService;
	}

	public List<ReceiptsView> getReceiptsViewList() {
		return receiptsViewList;
	}

	public void setReceiptsViewList(List<ReceiptsView> receiptsViewList) {
		this.receiptsViewList = receiptsViewList;
	}

	public ReceiptsView getReceiptsView() {
		return receiptsView;
	}

	public void setReceiptsView(ReceiptsView receiptsView) {
		this.receiptsView = receiptsView;
	}	

}
