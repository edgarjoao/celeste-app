with(milonic=new menuname("Main Menu")){
	top=1;
	left=600;
	alwaysvisible=1;
	orientation="horizontal";
	style=menuStyle;
	aI("text=Inicio;url=home.jsf");		
	aI("showmenu=catalogs;text=Catalogos;");			
	aI("showmenu=receipts;text=Ingresos;");
	aI("showmenu=expenses;text=Egresos;");
	aI("showmenu=reports;text=Reportes;");
	aI("text=Reservaciones;url=reservaciones.jsf");
	aI("text=Salir;url=logout.jsp");
}		
with(milonic=new menuname("catalogs")){
	style=menuStyle;				
		aI("text=Condominos;url=listado_condominos.jsf;");							
		aI("text=Proveedores;url=listado_proveedores.jsf;");										
}		 	   
with(milonic=new menuname("receipts")){
	style=menuStyle;									
		aI("text=Listado de Ingresos;url=listado_ingresos.jsf;");		
}		 
with(milonic=new menuname("expenses")){
	style=menuStyle;					
		aI("text=Listado de Egresos;url=listado_egresos.jsf;");						
}
with(milonic=new menuname("reports")){
	style=menuStyle;				
		aI("text=Reporte Mensual;url=reporte_mensual.jsf;");																		
}		 	   
drawMenus();