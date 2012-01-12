package com.condominium.condom.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletResponse;

import com.condominium.common.utils.JSFUtil;
import com.condominium.common.utils.StringUtils;
import com.condominium.condom.view.CondominiumsView;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.service.ReceiptsService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@ManagedBean(name="condominiumsDebtsBean")
@ViewScoped
public class CondominiumsDebtsManagedBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2579407159919241798L;
	@ManagedProperty(value="#{receiptsService}")
	private ReceiptsService receiptsService;
	private List<CondominiumsView> condominiumsList;
	private String debsHeader;
	
	public void fillDebtsTable(ComponentSystemEvent event){
		Calendar cal = Calendar.getInstance();
		StringBuilder title = new StringBuilder(0);
		try {
			//Se obtiene el mes y anio actual para llenar la tabla de adeudos del mes.
			condominiumsList = receiptsService.getDebtsOfTheMonth(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
			title.append(StringUtils.capitalize(StringUtils.getMonth(cal.get(Calendar.MONTH) + 1))).append(" ");
			title.append(cal.get(Calendar.YEAR));
			debsHeader = title.toString();
		} catch (ReceiptsException e) {			
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getErrorCode(), e.getErrorCode());
		}
	}
	
	public void pdfExportAction(){
		FacesContext context = FacesContext.getCurrentInstance();		
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment;filename=\"Reporte Adeudo Mensual-" + System.currentTimeMillis() + ".pdf\"");		
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();			
			PdfPTable tablaAdeudo = new PdfPTable(4);
			tablaAdeudo.setTotalWidth(new float[]{ 50, 150, 150, 50 });
			
			PdfPCell headerTitle = new PdfPCell(new Phrase("Reporte Adeudo - " + debsHeader, FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.BOLD, BaseColor.BLACK)));
			headerTitle.setColspan(4);
			tablaAdeudo.addCell(headerTitle);
			
			PdfPCell houseNumberHeader = new PdfPCell(new Phrase("Casa", FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.BOLD, BaseColor.WHITE)));			
			houseNumberHeader.setBackgroundColor(BaseColor.GRAY);
			houseNumberHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell nameHeader = new PdfPCell(new Phrase("Nombre", FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.BOLD, BaseColor.WHITE)));			
			nameHeader.setBackgroundColor(BaseColor.GRAY);
			nameHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell descriptionHeader = new PdfPCell(new Phrase("Descripción", FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.BOLD, BaseColor.WHITE)));			
			descriptionHeader.setBackgroundColor(BaseColor.GRAY);
			descriptionHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell amoutHeader = new PdfPCell(new Phrase("Cantidad", FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.BOLD, BaseColor.WHITE)));			
			amoutHeader.setBackgroundColor(BaseColor.GRAY);
			amoutHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			tablaAdeudo.addCell(houseNumberHeader);
			tablaAdeudo.addCell(nameHeader);
			tablaAdeudo.addCell(descriptionHeader);
			tablaAdeudo.addCell(amoutHeader);
			
			PdfPCell numEgresosCellAling = null;
			for(CondominiumsView v : condominiumsList){
				tablaAdeudo.addCell(new Phrase(v.getHouseNumber()));
				StringBuilder name = new StringBuilder(0);
				name.append(v.getUserView().getNombre() != null ? v.getUserView().getNombre() : "").append(" ");
				name.append(v.getUserView().getApaterno() != null ? v.getUserView().getApaterno() : "").append(" ");
				name.append(v.getUserView().getAmaterno() != null ? v.getUserView().getAmaterno() : "");
				tablaAdeudo.addCell(new Phrase(name.toString()));				
				tablaAdeudo.addCell(new Phrase(v.getDebDescription()));
				Phrase amountColor = new Phrase(v.getDebt(), FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.BOLD, v.getDebt().equals("0.00") ? BaseColor.RED : BaseColor.BLACK));
				numEgresosCellAling = new PdfPCell(amountColor);
				numEgresosCellAling.setHorizontalAlignment(Element.ALIGN_RIGHT);				
				tablaAdeudo.addCell(numEgresosCellAling);
			}
			document.add(tablaAdeudo);
			document.close(); 
			context.responseComplete();
		}catch(DocumentException e){			
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			e.printStackTrace();
		}
	}
			
	public List<CondominiumsView> getCondominiumsList() {
		return condominiumsList;
	}

	public void setCondominiumsList(List<CondominiumsView> condominiumsList) {
		this.condominiumsList = condominiumsList;
	}

	public void setReceiptsService(ReceiptsService receiptsService) {
		this.receiptsService = receiptsService;
	}

	public String getDebsHeader() {
		return debsHeader;
	}

	public void setDebsHeader(String debsHeader) {
		this.debsHeader = debsHeader;
	}		
}
