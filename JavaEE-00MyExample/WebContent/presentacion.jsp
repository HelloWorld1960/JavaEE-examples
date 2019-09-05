<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Presentacion</title>
</head>
<body>

	<h1>Esta es la pagina presentacion.jsp</h1>
	<h3>Usando expresion lenguage.</h3>
	<!-- Accediendo al la variable del jsp -->
	Esto es una variable request: <strong> ${nameRequest}</strong> <br>
	Esto es una variable session: <strong> ${nameSession}</strong> <br>
	Esto es una variable context: <strong> ${nameContext}</strong> <br>
	
	<a href="/JavaEE-00MyExample/presentacion1.jsp">Llevar a presentacion1.jsp</a>
	<br>
	
	<h3>Usando scriplets.</h3>
	<%  int a=8;
		int b=5;
		int c=b+a;
		
		out.print(c);
	%>
	
	<h3>Usando expresiones.</h3>
	<%=request.getAttribute("nameRequest") %> <br>
	<%=request.getSession().getAttribute("nameSession") %> <br>
	<%=getServletContext().getAttribute("nameContext") %> <br>
	

</body>
</html>