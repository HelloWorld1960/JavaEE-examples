<!-- Los Java Servers Pages (JSPs) son componentes del lado del servidor web, los cuales se utilizan para manejar 
    codigo html e incrustar codigo java por medio de etiquetas conocidas como tags.
    Los JSPs son utilizados como metodo de presentacion, es decir son utilizados para mostrar informacion procesada 
    por componentes como los servlets.
    Al compilarse un JSP se crea un servlet asociado al este JSP, despues su ciclo de vida es igual al de un servlet. -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> <%-- Directiva que indica la codificacion del documento. --%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JavaEE-06ConnectionPool</title>
</head>
<body>

<!-- Realizar peticion al servlet, mediante el metodo get. -->
<a href="Controller?opcion=1">Insertar un registro</a> <br>


</body>
</html>