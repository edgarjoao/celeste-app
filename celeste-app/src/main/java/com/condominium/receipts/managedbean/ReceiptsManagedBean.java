package com.condominium.receipts.managedbean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.condominium.common.utils.JSFUtil;
import com.condominium.common.utils.NumberUtil;
import com.condominium.common.utils.TimeUtils;
import com.condominium.condom.service.CondominiumsService;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.service.ReceiptsService;
import com.condominium.receipts.view.ReceiptsView;
import com.condominium.user.exception.UserException;
import com.condominium.user.view.UserView;
/**
 * 
 * @author rioslore
 *
 */
@ManagedBean(name="receiptsBean")
@SessionScoped
public class ReceiptsManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6145402496921694693L;
	
	@ManagedProperty(value="#{receiptsService}")
	private ReceiptsService receiptsService;
	@ManagedProperty(value="#{condominiumsService}")
	private CondominiumsService condominiumsService;	
	private String receiptType;	
	private List<ReceiptsView> receiptsViewList;
	private ReceiptsView receiptsView = new ReceiptsView();
	private ReceiptsView receiptsEditView = new ReceiptsView();	
	private String receiptSearchType;
	private String receiptSearchMonth;
	private String receiptSearchYear;	
	private String receiptMonth;
	private String receiptYear;	
	private String total;	
	private boolean showTable = false;	
	private boolean prepayments = false;
	private String condominiumsId;
	
	public String goAddReceipts(){
		this.clean();
		return "registrar_ingreso";
	}
	
	public String goAddAndNewReceipts(){
		try{
			receiptsView.setDate(TimeUtils.fillDate(receiptMonth, receiptYear));
			receiptsService.insertReceipt(receiptsView);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El ingreso se ha guardado satisfactoriamente", "Error de Ingresos");			
			this.clean();
		} catch (ReceiptsException e) {			
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error de Ingresos");
			return null;
		}
		return "registrar_ingreso";
	}
	
	public String backAction(){
		this.clean();
		return "listado_ingresos";
	}
	
	public String goEditReceipts(){
		Calendar cal = Calendar.getInstance();		
		String id = JSFUtil.getParam("receiptId");		
		for (ReceiptsView r : receiptsViewList) {
			if(id.equals(r.getId())){				
				this.setReceiptsEditView(r);
			}
		}		
		Date date = TimeUtils.convertStringToDate(receiptsEditView.getDate(), TimeUtils.DATE_PATTERN_DDMMYYYY);
		cal.setTime(date);
		this.receiptMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
		this.receiptYear = String.valueOf(cal.get(Calendar.YEAR));
		return "editar_ingreso";
	}
	
	public void fillDropDowns(ComponentSystemEvent event){
		Calendar cal = Calendar.getInstance();
		if(receiptSearchMonth == null && receiptSearchYear == null){			
			this.receiptSearchMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
			this.receiptSearchYear = String.valueOf(cal.get(Calendar.YEAR));			
		}else if(receiptSearchMonth != null && receiptSearchYear != null){
			if(receiptSearchMonth.equals("") && receiptSearchYear.equals("")){
				this.receiptSearchMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
				this.receiptSearchYear = String.valueOf(cal.get(Calendar.YEAR));	
			}
		}		
	}
	/**
	 * Pre-LLena los combos de anio y fecha en la pantalla de agregar Ingreso.
	 */
	public void fillDropDownsInsertPage(){
		Calendar cal = Calendar.getInstance();
		if(receiptMonth == null && receiptYear == null){			
			this.receiptMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
			this.receiptYear = String.valueOf(cal.get(Calendar.YEAR));			
		}else if(receiptMonth != null && receiptYear != null){
			if(receiptMonth.equals("") && receiptYear.equals("")){
				this.receiptMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
				this.receiptYear = String.valueOf(cal.get(Calendar.YEAR));	
			}
		}
	}
	
	public void validateReceiptsForm(ComponentSystemEvent event){
		FacesContext fc = FacesContext.getCurrentInstance();
		UIComponent components = event.getComponent();
		UIInput impInput = (UIInput)components.findComponent("importeId");
		String impValue = impInput.getLocalValue().toString();
		
		UISelectOne receiptType = (UISelectOne)components.findComponent("tipoIngresoId");
		String recpValue = receiptType.getLocalValue().toString();
		
		if(impValue.equals("".trim())){			
			fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "El importe es requerido.", "El importe es requerido."));			
		}		
		if(recpValue.equals("-1")){			
			fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione un tipo de Ingreso.", "Seleccione un tipo de Ingreso."));
			
		}
		if(!fc.getMessageList().isEmpty()){
			fc.renderResponse();
		}
		
	}	
	
	public void clean(){		
		this.showTable = false;
		this.prepayments = false;
		this.receiptsViewList = null;
		this.receiptType = "";
		this.receiptsEditView = new ReceiptsView();
		this.receiptsView = new ReceiptsView();
	}
	
	public void init(){
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String searchAction(){
		try {
			receiptsViewList = receiptsService.getSearchReceiptsList(
						Integer.parseInt(receiptSearchMonth),
						Integer.parseInt(receiptSearchYear), 
						Integer.parseInt(receiptSearchType),
						Integer.parseInt(condominiumsId));
			if(!receiptsViewList.isEmpty()){
				double total = 0;
				for(ReceiptsView v : receiptsViewList){
					total += NumberUtil.parseDouble(v.getAmount());
				}
				this.total = NumberUtil.convertQuantity(total);
				this.setShowTable(true);				
			}else{
				this.setShowTable(false);
				JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, "No se encontraron resultados.", "No se encontraron resultados.");	
			}			
		} catch (ReceiptsException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error de Ingresos");
		}
		return null;
	}	
	
	public String insertReceipt(){
		try{
			receiptsView.setDate(TimeUtils.fillDate(receiptMonth, receiptYear));
			receiptsView.setUserId(JSFUtil.getSessionAttribute(UserView.class, "userView").getUserId());			
			receiptsService.insertReceipt(receiptsView);
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El ingreso se ha guardado satisfactoriamente", "Error de Ingresos");			
			this.clean();
		} catch (ReceiptsException e) {			
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error de Ingresos");
			return null;
		}
		return "listado_ingresos";
	}	
	
	public String deleteReceiptsAction(){
		try{
			String id = JSFUtil.getParam("id");			
			receiptsService.deleteReceipt(Integer.parseInt(id));
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El ingreso se ha eliminado con exito.", "El ingreso se ha eliminado con exito.");
			this.clean();
		} catch (ReceiptsException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error de Ingresos");
			return null;
		}
		return "listado_ingresos";
	}
	
	public String updateReceiptsAction(){		
		try {
			receiptsEditView.setDate(TimeUtils.fillDate(receiptMonth, receiptYear));
			this.receiptsService.updateReceipt(receiptsEditView);
			this.clean();
			JSFUtil.writeMessage(FacesMessage.SEVERITY_INFO, "El ingreso se ha actualizado con exito.", "El ingreso se ha actualizado con exito.");			
		} catch (ReceiptsException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error de Ingresos");
			return null;
		}		
		return "listado_ingresos";
	}
	
	/**
	 * Este metodo sirve para mostrar el input para pago adelantado.
	 * @param event
	 */
	@Deprecated
	public void showPrepaymentListener(ValueChangeEvent event){
		boolean value = ((Boolean) event.getNewValue()).booleanValue();
		if(value){
			this.setPrepayments(true);
		}else{			
			//TODO
			this.setPrepayments(false);
		}
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
	
	public List<SelectItem> getCondominiumsList(){
		List<SelectItem> list = null;
		try {
			list = condominiumsService.getCondominiumsItems();
		} catch (UserException e) {
			e.printStackTrace();
		}
		return list;
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

	public boolean isPrepayments() {
		return prepayments;
	}

	public void setPrepayments(boolean prepayments) {
		this.prepayments = prepayments;
	}

	public ReceiptsService getReceiptsService() {
		return receiptsService;
	}

	public void setCondominiumsService(CondominiumsService condominiumsService) {
		this.condominiumsService = condominiumsService;
	}

	public ReceiptsView getReceiptsEditView() {
		return receiptsEditView;
	}

	public void setReceiptsEditView(ReceiptsView receiptsEditView) {
		this.receiptsEditView = receiptsEditView;
	}

	public String getReceiptSearchType() {
		return receiptSearchType;
	}

	public void setReceiptSearchType(String receiptSearchType) {
		this.receiptSearchType = receiptSearchType;
	}

	public boolean isShowTable() {
		return showTable;
	}

	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	public String getReceiptSearchMonth() {
		return receiptSearchMonth;
	}

	public void setReceiptSearchMonth(String receiptSearchMonth) {
		this.receiptSearchMonth = receiptSearchMonth;
	}

	public String getReceiptSearchYear() {
		return receiptSearchYear;
	}

	public void setReceiptSearchYear(String receiptSearchYear) {
		this.receiptSearchYear = receiptSearchYear;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getReceiptMonth() {
		return receiptMonth;
	}

	public void setReceiptMonth(String receiptMonth) {
		this.receiptMonth = receiptMonth;
	}

	public String getReceiptYear() {
		return receiptYear;
	}

	public void setReceiptYear(String receiptYear) {
		this.receiptYear = receiptYear;
	}

	public String getCondominiumsId() {
		return condominiumsId;
	}

	public void setCondominiumsId(String condominiumsId) {
		this.condominiumsId = condominiumsId;
	}
				
}
