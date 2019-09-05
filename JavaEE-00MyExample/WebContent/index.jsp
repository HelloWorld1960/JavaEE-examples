<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JavaEE-01MyExample</title>
</head>
<body>
	<h1>Hello world.</h1>
	<a href="MyServlet?var1=5&var2=10">Creamos una peticion de tipo GET.</a>
	<br><br>
	
	<h3>Enviar parametros al Metodo POST</h3>
	<form action="MyServlet" method="post">
		<label>Nombre:</label>
		<input type="text" name="nombre">
		<button type="submit">Enviar</button>
	</form>
</body>
</html>