with(milonic=new menuname("Main Menu")){
	top=1;
	left=600;
	alwaysvisible=1;
	orientation="horizontal";
	style=menuStyle;
	aI("text=Inicio;url=home.jsf");		
	aI("showmenu=condomin;text=Condominos;");			
	aI("showmenu=receipts;text=Ingresos;");
	aI("showmenu=expenses;text=Egresos;");
	aI("text=Salir;url=logout.do");
}		
with(milonic=new menuname("condomin")){
	style=menuStyle;				
		aI("text=Transportes;url=showTransportQuery.do;");							
		aI("text=Costos;url=costElementList.do;");							
		aI("text=Plantas;url=listPlantsQuery.do;");				
}		 	   
with(milonic=new menuname("receipts")){
	style=menuStyle;			
		aI("showmenu=futures;text=Futuros;");					
		aI("text=Programa de Compras;url=listado_ingresos.jsf;");			
		aI("text=Versiones;url=versionList.do;");					
		aI("showmenu=instructionpurchase;text=Instrucciones de Compra;");
}		 
with(milonic=new menuname("expenses")){
	style=menuStyle;					
		aI("text=Datos;url=goCreditLine.do;");						
		aI("text=Balance;url=goCreditLineBalance.do;");					
}			
with(milonic=new menuname("futures")){
	style=menuStyle;
		aI("text=Compra de Futuros;url=showFuturePurchaseQuery.do;");
		aI("text=Futuros por cliente;url=showCustomerFuturePurchaseQuery.do;");
}			
with(milonic=new menuname("instructionpurchase")){
	style=menuStyle;
		aI("text=Detalle de instrucciones;url=instructionPurchaseCreateList.do?filters=news;");
		aI("text=Pronostico de Ventas;url=showInstructionPurchaseForecastQuery.do;");
}		
drawMenus();