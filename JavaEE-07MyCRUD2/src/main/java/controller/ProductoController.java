package controller;
/**CONTROLADOR:
 *-Este servlet se encarga de agregar la informacion que el jsp va a utilizar, es decir va a
   compartir informacion por ejemplo en el alcance request y una vez que el servlet ha compartido la informacion y 
   redirecciona el flujo al jsp, entonces el jsp puede acceder a los objetos que se han compartido en cierto alcance.
 * */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductoDAO;
import model.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**Cuando el usuario envia parametros de forma explicita en la URL, se considera una peticion doGet.
     *-Para procesar esta peticion se sobreescribe el metodo doGet.
     *-HttpServletRequest y HttpServletResponse los proporcion el ciclo de vida del servlet, el metodo service es 
     * el responsable de ejecutar el servlet y cuando detecta que se ejecuto un metodo de tipo get, manda a llamar al 
     * metodo doGet y le proporciona los parametros request y response.
     *-Una vez ejecutado el metodo doGet, el metodo service se encarga de enviar la respuesta hacia el cliente que 
     * hizo la llamada.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperamos el parametro opcion.
		String opcion = request.getParameter("opcion"); //Alcance request.
		
		if (opcion.contentEquals("crear")) {
			System.out.println("Usted a presionado opcion crear");
			//Redireccionar a la vista crear.jsp.
			RequestDispatcher rD = request.getRequestDispatcher("/views/crear.jsp");
			rD.forward(request, response);
			
		}else if (opcion.contentEquals("listar")) {
			System.out.println("Usted a presionado opcion listar");
			ProductoDAO productoDAO = new ProductoDAO();
			List<Producto> lista = new ArrayList<>();
			
			try {
				lista = productoDAO.obtenerProductos();
				
				//Imprimir lista.
				//for (Producto producto : lista) {
				//	System.out.println(producto);
				//}
				
				//Enviar parametro a la vista listar.
				request.setAttribute("lista", lista);
				
				//Redireccionar a la vista listar.jsp.
				RequestDispatcher rD = request.getRequestDispatcher("/views/listar.jsp");
				rD.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (opcion.equals("meditar")) {
			System.out.println("Usted a presionado opcion editar");
			
			//Obtenemos el id a modificar.
			int id = Integer.parseInt(request.getParameter("id"));
			//Imprimir Id.
			//System.out.println("Editar ID:"+id);
			
			ProductoDAO productoDAO = new ProductoDAO();
			Producto p = new Producto();
			
			try {
				//Obtener datos del producto a editar.
				p = productoDAO.obtenerProducto(id); 
				//Imprimir objeto.
				//System.out.println(p);
				
				//Enviar parametro a la vista listar.
				request.setAttribute("producto", p);
				
				//Redireccionar a la vista editar.jsp
				RequestDispatcher rD = request.getRequestDispatcher("/views/editar.jsp");
				rD.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if (opcion.equals("eliminar")) {
			System.out.println("Usted a presionado opcion eliminar");
			//Obtenemos el id a modificar.
			int id = Integer.parseInt(request.getParameter("id"));
			
			ProductoDAO productoDAO = new ProductoDAO();
			
			try {
				productoDAO.eliminar(id);
				System.out.println("Registro eliminado exitosamente.");
				
				//Redireccionar a la vista index.jsp
				RequestDispatcher rD = request.getRequestDispatcher("/index.jsp");
				rD.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**Cuando el usuario decea procesar peticiones con parametros de un formulario, se considera una peticion doPost.
     *-Para procesar esta peticion se sobreescribe el metodo doPost.
     *-HttpServletRequest y HttpServletResponse los proporcion el ciclo de vida del servlet, el metodo service es 
     * el responsable de ejecutar el servlet y cuando detecta que se ejecuto un metodo de tipo post, manda a llamar al 
     * metodo doPost y le proporciona los parametros request y response.
     *-getParameter obtiene los argumentos esperados por el servlet.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperamos el parametro opcion.
		String opcion = request.getParameter("opcion");
		
		Date fechaActual = new Date();
		
		if (opcion.equals("guardar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = new Producto();
			
			//Obtenermos los valores del formulario y se los asignamos a el nuevo objeto.
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad"))); //Realizamos convercion de String a Double.
			producto.setPrecio(Double.parseDouble(request.getParameter("precio"))); //Realizamos convercion de String a Double.
			producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
			
			try {
				productoDAO.guardar(producto); //Llamar al metodo Guardar producto.
				System.out.println("Registro exitoso");
				
				//Redireccionar a la vista index.jsp
				RequestDispatcher rD = request.getRequestDispatcher("/index.jsp");
				rD.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (opcion.equals("editar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = new Producto();
			
			//Obtenermos los valores del formulario y se los asignamos a el nuevo objeto.
			producto.setId(Integer.parseInt(request.getParameter("id"))); //Realizamos convercion de String a Int.
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad"))); //Realizamos convercion de String a Double.
			producto.setPrecio(Double.parseDouble(request.getParameter("precio"))); //Realizamos convercion de String a Double.
			producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
			
			try {
				productoDAO.editar(producto); //Llamar al metodo Editar producto.
				System.out.println("Registro editado exitosamente.");
				
				//Redireccionar a la vista index.jsp
				RequestDispatcher rD = request.getRequestDispatcher("/index.jsp");
				rD.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//doGet(request, response);
	}

}
