package com.condominium.expenses.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import com.condominium.common.utils.JSFUtil;
import com.condominium.expenses.exception.ExpensesException;
import com.condominium.expenses.service.ExpensesService;
import com.condominium.expenses.view.SuppliersView;

@ManagedBean(name="suppliersBean")
@SessionScoped
public class SuppliersManagedBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3961163286161720954L;
	
	
	@ManagedProperty(value="#{expensesService}")
	private ExpensesService expensesService;
	private SuppliersView suppliersView = new SuppliersView();
	private SuppliersView suppliersEditView = new SuppliersView();
	private List<SuppliersView> suppliersList = new ArrayList<SuppliersView>(0);
			
	public void init(){
		
	}
	
	public void populateTable(ComponentSystemEvent event){
		try{
			this.suppliersList = expensesService.getSuppliersList();
			if(this.suppliersList == null){
				JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "No se encontraron Proveedores.", "No se encontraron Proveedores.");
				this.suppliersList = new ArrayList<SuppliersView>(0);	
			}
		}catch (ExpensesException e) {
			this.suppliersList = new ArrayList<SuppliersView>(0);
		}	
	}
	
	public void clean(){
		this.suppliersEditView = new SuppliersView();
		this.suppliersEditView = new SuppliersView();
		try{
			this.suppliersList = expensesService.getSuppliersList();
			if(this.suppliersList == null){
				this.suppliersList = new ArrayList<SuppliersView>(0);	
			}
		}catch (ExpensesException e) {
			this.suppliersList = new ArrayList<SuppliersView>(0);
		}		
	}
	
	public void validateForm(ComponentSystemEvent event){
		FacesContext fc = FacesContext.getCurrentInstance();
		UIComponent components = event.getComponent();
		UIInput nameInput = (UIInput)components.findComponent("nombreId");
		String nameValue = nameInput.getLocalValue().toString();
		
		UIInput addressInput = (UIInput)components.findComponent("domicilioId");
		String addressValue = addressInput.getLocalValue().toString();
		
		if(nameValue.equals("".trim())){			
			fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "El importe es requerido.", "El importe es requerido."));			
		}
		if(addressValue.equals("".trim())){			
			fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "La dirección requerida.", "La dirección requerida."));			
		}		
		if(!fc.getMessageList().isEmpty()){
			fc.renderResponse();
		}
		
	}
	
	public String goAddSupplier(){
		return "registrar_proveedor?faces-redirect=true";
	}
	
	public String goBack(){
		return "listado_proveedores?faces-redirect=true";
	}
	
	public String goEditSupplier(){		
		String id = JSFUtil.getParam("provId");
		
		for(SuppliersView s : suppliersList){
			if(id.equals(s.getProvId())){				
				this.setSuppliersEditView(s);
			}
		}			
		return "editar_proveedor?faces-redirect=true";
	}
	
	public String editSupplierAction(){
		try {
			expensesService.editSupplier(suppliersEditView);
			this.clean();
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El Proveedor se a actualizado exitosamente.", "El Proveedor se a actualizado exitosamente.");
		} catch (ExpensesException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getExceptionCode(), e.getExceptionCode());
			return null;
		}					
		return "listado_proveedores";
	}
	
	public String deleteSupplierAction(){
		try {
			String id = JSFUtil.getParam("provId");
			expensesService.deleteSupplier(Integer.parseInt(id));
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El Proveedor se a eliminado exitosamente.", "El Proveedor se a eliminado exitosamente.");
		} catch (ExpensesException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getExceptionCode(), e.getExceptionCode());
			return null;
		}					
		return "listado_proveedores";
	}
	
	public String addSupplierAction(){		
		try {
			expensesService.addSupplier(suppliersView);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El Proveedor se a guardado exitosamente.", "El Proveedor se a guardado exitosamente.");
		} catch (ExpensesException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getExceptionCode(), e.getExceptionCode());
			return null;
		}		
		return "listado_proveedores";
	}	
			
	public void setSuppliersList(List<SuppliersView> suppliersList) {
		this.suppliersList = suppliersList;
	}

	public List<SuppliersView> getSuppliersList() {
		return suppliersList;
	}

	public SuppliersView getSuppliersView() {
		return suppliersView;
	}
	public void setSuppliersView(SuppliersView suppliersView) {
		this.suppliersView = suppliersView;
	}
	public void setExpensesService(ExpensesService expensesService) {
		this.expensesService = expensesService;
	}

	public SuppliersView getSuppliersEditView() {
		return suppliersEditView;
	}

	public void setSuppliersEditView(SuppliersView suppliersEditView) {
		this.suppliersEditView = suppliersEditView;
	}
				
}
