<!-- Vista: La vista o interfaz de usuario compone la informacion que se envia al usuario y los mecanismos de interaccion 
	 con la aplicacion.

    -Los JSPs estan enfocados en desplegar la informacion de la aplicacion web, en este caso la informacion es 
    proveida por medio de los servlets y la informacion que se comparte entre los Servlets y JSPs suele manejarse 
    con JavaBean.
    -Los Java Servers Pages (JSPs) son componentes del lado del servidor web, los cuales se utilizan para manejar 
    codigo html e incrustar codigo java por medio de etiquetas conocidas como tags.
    -Los JSPs son utilizados como metodo de presentacion, es decir son utilizados para mostrar informacion procesada 
    por componentes como los servlets.
    -Al compilarse un JSP se crea un servlet asociado a ese JSP, despues su ciclo de vida es igual al de un servlet. -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> <%-- Directiva que indica la codificacion del documento. --%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Crear producto</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<div class="container">
  		<h1>Crear Producto.</h1>

		<form action="ProductoController" method="post">
			<input type="hidden" name="opcion" value="guardar">
			<div class="form-group col-sm-3">
				<label>Nombre:</label>
				<input type="text" name="nombre" class="form-control">
			</div>
			<div class="form-group col-sm-3">
				<label>Cantidad:</label>
				<input type="text" name="cantidad" class="form-control">
			</div>
			<div class="form-group col-sm-3">
				<label>Precio:</label>
				<input type="text" name="precio" class="form-control">
			</div>
			<div class="form-group col-sm-3">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
		
		<div>
			<a href="http://localhost:8080/JavaEE-07MyCRUD2">Regresar al inicio.</a>
		</div>
	</div>

<!-- Javascript -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>