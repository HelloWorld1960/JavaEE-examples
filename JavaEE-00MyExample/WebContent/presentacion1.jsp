<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Presentacion1</title>
</head>
<body>
	
	<h1>Esta es la pagina presentacion1.jsp</h1>
	
	<!-- Accediendo al la variable del jsp -->
	Esto es una variable request: <strong> ${nameRequest}</strong> <br>
	Esto es una variable session: <strong> ${nameSession}</strong> <br>
	Esto es una variable context: <strong> ${nameContext}</strong> <br>
	
	<a href="/JavaEE-00MyExample/index.jsp">Llevar al inicio</a>

</body>
</html>