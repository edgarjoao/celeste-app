package com.condominium.reports.montlhy.managedbean;

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
import com.condominium.common.utils.NumberUtil;
import com.condominium.common.utils.StringUtils;
import com.condominium.expenses.exception.ExpensesException;
import com.condominium.expenses.service.ExpensesService;
import com.condominium.expenses.view.ExpensesReportView;
import com.condominium.receipts.exception.ReceiptsException;
import com.condominium.receipts.service.ReceiptsService;
import com.condominium.receipts.view.ReceiptsReportView;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@ManagedBean(name="monthlyBean")
@ViewScoped
public class MonthlyReportManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8566448829206253118L;
	@ManagedProperty(value="#{receiptsService}")
	private ReceiptsService receiptsService;	
	@ManagedProperty(value="#{expensesService}")
	private ExpensesService expensesService;
	private List<ReceiptsReportView> receiptsReportList;
	private List<ExpensesReportView> expensesReportList;
	
	private String month;
	private String year;
	private String ingresoTotal;
	private String egresosTotal;
	private String total;
	private String partialTotal;
	private boolean showReport;
	private String headerText;
	private double totalStyle;
	private double partialTotalStyle;	
	
	public void fillDropDowns(ComponentSystemEvent event){
		Calendar cal = Calendar.getInstance();
		if(month == null && year == null){			
			this.month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			this.year = String.valueOf(cal.get(Calendar.YEAR));			
		}else if(month != null && year != null){
			if(month.equals("") && year.equals("")){
				this.month = String.valueOf(cal.get(Calendar.MONTH) + 1);
				this.year = String.valueOf(cal.get(Calendar.YEAR));	
			}
		}		
	}
	
	public String searchAction(){
		this.ingresoTotal = "";
		this.egresosTotal = "";
		this.total = "";
		try {			
			receiptsReportList = receiptsService.receiptsReportByMonth(Integer.parseInt(month), Integer.parseInt(year));
			expensesReportList = expensesService.getExpensesReport(Integer.parseInt(month), Integer.parseInt(year));
			double rTotal = 0;
			double eTotal = 0;
			//Obtener el Acumulado del Mes Anterior
			this.partialTotal = this.receiptsService.totalPrevious(Integer.parseInt(month), Integer.parseInt(year));
			if(!receiptsReportList.isEmpty()){				
				for(ReceiptsReportView v : receiptsReportList){
					rTotal += NumberUtil.parseDouble(v.getAmount());
				}				
			}
			this.ingresoTotal = NumberUtil.convertQuantity(rTotal + NumberUtil.parseDouble(partialTotal));			
								
			if(!expensesReportList.isEmpty()){				
				for(ExpensesReportView v : expensesReportList){
					eTotal += NumberUtil.parseDouble(v.getAmount());
				}				
			}
			this.egresosTotal = eTotal > 0 ? NumberUtil.convertQuantity(eTotal) : "0.00";
								
			this.total = NumberUtil.convertQuantity(NumberUtil.parseDouble(ingresoTotal) - eTotal);
			
			this.showReport = true;
			//Estilo rojo si hay numeros negativos.
			this.totalStyle = NumberUtil.parseDouble(total);
			this.partialTotalStyle = NumberUtil.parseDouble(partialTotal);
			//Header
			this.headerText = StringUtils.buildTextHeader(month, year);
		} catch (NumberFormatException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		} catch (ReceiptsException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getCustomError(), e.getErrorCode());
		} catch (ExpensesException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getCustomError(), e.getErrorCode());
		}		
		return null;
	}
	
	
	public void pdfExportAction(){
		FacesContext context = FacesContext.getCurrentInstance();		
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment;filename=\"ReporteMensual-" + System.currentTimeMillis() + ".pdf\"");		
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			Paragraph header = new Paragraph("Reporte Mensual",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK) );
			header.setAlignment(Element.ALIGN_CENTER);			
			document.add(header);
			Paragraph headerFecha = new Paragraph(headerText);
			headerFecha.setAlignment(Element.ALIGN_CENTER);
			document.add(headerFecha);
			document.add(Chunk.NEWLINE);			
			
			PdfPTable tablaIngresos = new PdfPTable(3);																	
			PdfPCell ingresosHeader = new PdfPCell(new Phrase("Ingresos", FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.BOLD, BaseColor.WHITE)));
			ingresosHeader.setColspan(3);
			ingresosHeader.setBackgroundColor(BaseColor.GRAY);
			
			ingresosHeader.setHorizontalAlignment(Element.ALIGN_LEFT);									
			tablaIngresos.addCell(ingresosHeader);				
			//Ingresos
			PdfPCell numIngresoCellAling = null;						
			for(ReceiptsReportView r : this.receiptsReportList){
				tablaIngresos.addCell(new Phrase(r.getMonthName()));
				tablaIngresos.addCell(new Phrase(r.getDescription()));
				numIngresoCellAling = new PdfPCell(new Phrase(r.getAmount()));
				numIngresoCellAling.setHorizontalAlignment(Element.ALIGN_RIGHT);				
				tablaIngresos.addCell(numIngresoCellAling);
			}
			PdfPCell acumuladoHeader = new PdfPCell(new Phrase("ACUMULADO DEL MES"));
			acumuladoHeader.setColspan(2);
			tablaIngresos.addCell(acumuladoHeader);
			//Acumulado
			PdfPCell acumuladoNum = new PdfPCell(new Phrase(partialTotal));
			acumuladoNum.setHorizontalAlignment(Element.ALIGN_RIGHT);			
			tablaIngresos.addCell(acumuladoNum);
			//Total Ingreso
			PdfPCell totalIngresosCell = new PdfPCell(new Phrase("Total Ingreso : " + ingresoTotal));
			totalIngresosCell.setHorizontalAlignment(Element.ALIGN_RIGHT);			
			totalIngresosCell.setColspan(3);
			tablaIngresos.addCell(totalIngresosCell);
					
			//Egresos
			PdfPTable tablaEgresos = new PdfPTable(2);			
			PdfPCell egresosHeader = new PdfPCell(new Phrase("Egresos", FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.WHITE)));
			egresosHeader.setColspan(3);
			egresosHeader.setBackgroundColor(BaseColor.GRAY);
			egresosHeader.setHorizontalAlignment(Element.ALIGN_LEFT);									
			tablaEgresos.addCell(egresosHeader);				
			
			PdfPCell numEgresosCellAling = null;						
			for(ExpensesReportView r : this.expensesReportList){				
				tablaEgresos.addCell(new Phrase(r.getDescription()));
				numEgresosCellAling = new PdfPCell(new Phrase(r.getAmount()));
				numEgresosCellAling.setHorizontalAlignment(Element.ALIGN_RIGHT);				
				tablaEgresos.addCell(numEgresosCellAling);
			}			
			PdfPCell totalEgresoCell = new PdfPCell(new Phrase("Total Egresos: " + egresosTotal));
			totalEgresoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			totalEgresoCell.setColspan(3);
			tablaEgresos.addCell(totalEgresoCell);

			//Total Mes				
			PdfPTable totalMesTabla = new PdfPTable(1);																	
			PdfPCell totalMesCell = new PdfPCell(new Phrase("TOTAL MES: " + total, FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK)));
			totalMesCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			totalMesTabla.addCell(totalMesCell);
						
	
			document.add(tablaIngresos);
			document.add(Chunk.NEWLINE);
			document.add(tablaEgresos);
			document.add(Chunk.NEWLINE);			
			document.add(totalMesTabla);
			document.close(); 
			context.responseComplete();
		}catch(DocumentException e){
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		} catch (IOException e) {
			JSFUtil.writeMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}	
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setReceiptsService(ReceiptsService receiptsService) {
		this.receiptsService = receiptsService;
	}
	public void setExpensesService(ExpensesService expensesService) {
		this.expensesService = expensesService;
	}

	public List<ReceiptsReportView> getReceiptsReportList() {
		return receiptsReportList;
	}

	public void setReceiptsReportList(List<ReceiptsReportView> receiptsReportList) {
		this.receiptsReportList = receiptsReportList;
	}

	public List<ExpensesReportView> getExpensesReportList() {
		return expensesReportList;
	}

	public void setExpensesReportList(List<ExpensesReportView> expensesReportList) {
		this.expensesReportList = expensesReportList;
	}

	public String getIngresoTotal() {
		return ingresoTotal;
	}

	public void setIngresoTotal(String ingresoTotal) {
		this.ingresoTotal = ingresoTotal;
	}

	public String getEgresosTotal() {
		return egresosTotal;
	}

	public void setEgresosTotal(String egresosTotal) {
		this.egresosTotal = egresosTotal;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public boolean isShowReport() {
		return showReport;
	}

	public void setShowReport(boolean showReport) {
		this.showReport = showReport;
	}

	public String getPartialTotal() {
		return partialTotal;
	}

	public void setPartialTotal(String partialTotal) {
		this.partialTotal = partialTotal;
	}

	public double getPartialTotalStyle() {
		return partialTotalStyle;
	}

	public void setPartialTotalStyle(double partialTotalStyle) {
		this.partialTotalStyle = partialTotalStyle;
	}

	public double getTotalStyle() {
		return totalStyle;
	}

	public void setTotalStyle(double totalStyle) {
		this.totalStyle = totalStyle;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
			
}
