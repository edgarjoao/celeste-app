<% 
	session.removeAttribute("userView");
	session.invalidate();
	response.sendRedirect("index.jsf");
%>
    