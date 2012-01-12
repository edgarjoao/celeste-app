with(milonic=new menuname("Main Menu")){
	top=1;
	left=600;
	alwaysvisible=1;
	orientation="horizontal";
	style=menuStyle;
	aI("text=Inicio;url=home.jsf");			
	aI("showmenu=reports;text=Reportes;");
	aI("text=Terraza;url=terraza.jsf");
	aI("text=Salir;url=logout.jsp");
}
with(milonic=new menuname("reports")){
	style=menuStyle;				
		aI("text=Reporte Mensual;url=reporte_mensual.jsf;");																		
}		 	   
drawMenus();