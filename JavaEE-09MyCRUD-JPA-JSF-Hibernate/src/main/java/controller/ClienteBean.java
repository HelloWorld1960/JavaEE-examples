package controller;
/**CONTROLADOR:
 *-Una Managed-Bean es una clase JavaBean gestionado por el framework JSF(JavaServer Faces) a la que se le asocia
   un componente de interfaz de usuario, es decir ls paginas dinamicas.
 *-Este Managed-Bean se va encargar de recibir todas las peticiones del cliente para poder guardar y recoger datos 
   de la sesion.
 *-Un Managed-Bean recibe todas las peticiones del cliente y puede contener metodos getter y setter, logica de negocio 
  o incluso un Backing-Bean(Bean de Respaldo, Bean que contiene todos los valores de un formulario HTML), 
  aunque si la aplicacion es compleja la logica de negocio se recomienda que recaiga en un componentes EJB. */

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import dao.ClienteDAO;
import modell.Cliente;

@ManagedBean(name = "clienteBean") //Anotacion para que JSF lo reconozca como un Managed-Bean.
@RequestScoped //Alcance del Managed-Bean.
public class ClienteBean {
	//Atributos

	
	//Metodos.
	//Registrar cliente
	public String nuevo() {
		Cliente c = new Cliente();
		
		//Coleccion de tipo Map.
		//Almacena la sesion y persistira durante toda la aplicacion.
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		//Insertamos un par clave/valor con los datos del cliente, esta se envia a el JSF nuevo.xhtml
		//put("clave", "valor")
		sessionMap.put("cliente", c);
		
		return  ("/faces/nuevo.xhtml");
	}
	
	public String guardar (Cliente cliente) {
		//Guarda la fecha de registro
		Date fechaActual= new Date();
		cliente.setFecha_registro(new java.sql.Date(fechaActual.getTime()));
		
		ClienteDAO clienteDAO= new ClienteDAO();
		clienteDAO.guardar(cliente); //Llama al metodo del ClienteDAO que se encarga de guardar los datos.
		
		return ("/faces/index.xhtml");
	}
	
	//Editar cliente.
	public String editar(Long id) {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente c = new Cliente();
		
		//Llamar al metodo.
		c = clienteDAO.buscar(id);
		
		//Imprime en consola los datos del cliente que  se decean modificar.
		System.out.println("******************************************");
		System.out.println(c);
		
		//Coleccion de tipo Map.
		//Almacena la sesion y persistira durante toda la aplicacion.
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		//Insertamos un par clave/valor con los datos del cliente, esta se envia a el JSF editar.xhtml
		//put("clave", "valor")
		sessionMap.put("cliente", c);
		
		return ("/faces/editar.xhtml");
	}
	
	public String actualizar(Cliente cliente) {
		//Guarda la fecha de actualizacion.
		Date fechaActual = new Date();
		cliente.setFecha_actualizacion(new java.sql.Date(fechaActual.getTime()));
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.editar(cliente); //Llama al metodo del ClienteDAO que se encarga de actualizar los datos.
		
		return ("/faces/index.xhtml");
	}
	
	// Eliminar un cliente.
	public String eliminar(Long id) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.eliminar(id); //Llama al metodo del ClienteDAO que se encarga de eliminar los datos.
		
		//Imprime en consola el mensaje.
		System.out.println("Cliente eliminado..");
		
		return ("/faces/index.xhtml");
	}
	
	//Listar clientes.
	public List<Cliente> obtenerClientes() {
		/* List<Cliente> listaClientes = new ArrayList<>(); Cliente c1 = new Cliente();
		 * c1.setId(1L); c1.setNombre("Jorge"); c1.setApellido("Thomson");
		 * c1.setDireccion("Calle falsa 123");
		 * 
		 * Cliente c2 = new Cliente(); c2.setId(1L); c2.setNombre("Rebeca");
		 * c2.setApellido("Villalobos"); c2.setDireccion("Calle Caoba 1414");
		 * 
		 * listaClientes.add(c1); listaClientes.add(c2);
		 * 
		 * return (listaClientes);
		 */
		ClienteDAO clienteDAO = new ClienteDAO();
		
		//Llama al metodo del ClienteDAO que se encarga de listar los datos.
		return (clienteDAO.obtenerClientes());
	}

}
