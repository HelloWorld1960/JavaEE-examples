/**
 * Ciclo de vida de un servlet.
 * 1) Se realiza peticion al servidor web para que se ejecute el servlet.
 * 2) El servidor web crea una instancia del servlet.
 * 3) Si es que se definio un metodo init() se manda a llamar.
 * 4) Se manda a llamar al metodo service para ejecutar el servlet.
 * 5) El servlet envia una respuesta al cliente.
 */
package com.HelloWorld1960.test;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller.
 * Cuando el usuario coloca la url en el navegador se genera automaticamente una peticion get.
 * Para procesar esta peticion se sobreescribe el metodo doGet.
 * HttpServletRequest y HttpServletResponse los proporcion el ciclo de vida del servlet, el metodo service es 
   el responsable de ejecutar el servlet y cuando detecta que se ejecuto un metodo de tipo get, manda a llamar al 
   metodo doGet y le proporciona los parametros request y response.
 * Una vez ejecutado el metodo doGet, el metodo service se encarga de enviar la respuesta hacia el cliente que 
   hizo la llamada.
 *  
 * Este servlet se encargara de cargar los parametros de conexion, es decir llamar al web.xml y a la clase TestDAO.
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TestDAO test; //Atributo del tipo TestDAO.
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * init() es el método invocado por el contenedor de servlets cuando se produce la inicialización del servlet, antes 
     * de que este reciba la primera petición. 
     * Este metodo se sobreescribe en todos los servlets y se encargara de cargar las variables de conexion.*/
    @Override
    public void init() throws ServletException {
    	/*Asignar valores que estan en web.xml*/
    	String jdbcURL = getServletContext().getInitParameter("jdbcURL");
    	String jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
    	String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
    	//Imprime URL de la conexion, solo para comprobar datos.
    	System.out.println(jdbcURL);
    	
    	try {
			test = new TestDAO(jdbcURL, jdbcUserName, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("opcion");
		
		switch (op) {
		case "1": //Insertar un registro
			//Instancia de un nuevo articulo.
			Articulo articulo = new Articulo(0, "Televisor", "Televisor LG 25 pulgadas", 560);
			if (test.registrar(articulo)) {
				System.out.println("Articulo ingresado");
			}else {
				System.out.println("Articulo no ingresado");
			}
			break;
			
		case "2": //Editar un registro
			//Instancia de un nuevo articulo.
			Articulo articuloEdit = new Articulo(2, "Computadora", " ", 0);
			if (test.editar(articuloEdit)) {
				System.out.println("Articulo editado");
			}else {
				System.out.println("Articulo no editado");
			}
			break;
			
		case "3": //Eliminar un registro
			//Instancia de un nuevo articulo.
			Articulo articuloDel = new Articulo(3, " ", " ", 0);
			if (test.eliminar(articuloDel)) {
				System.out.println("Articulo eliminado");
			}else {
				System.out.println("Articulo no eliminado");
			}
			break;
			
		case "4": //Obtener un registro
			System.out.println("Articulo:");
			test.obtenerArticulo(1);

			break;
			
		case "5": //Obtener todos los registros
			System.out.println("Articulos:");
			test.obtenerArticulos();
			
			break;

		default:
			break;
		}
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
