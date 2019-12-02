package dao;
/*MODELO: Contiene una representacion de de los datos que maneja el sistema, su logica de negocio y sus mecanismos de 
  persistencia. 
 *-Este modelo contiene los metodos con las consultas basicas CRUD (Create, Read, Update y Delete) a la tabla Producto. 
 *-Patron DAO (Data Access Object): Este patron de la capa de Acceso a Datos se encarga de extraer y almacenar 
   informacion de la base de datos.
 *
 * -Hibernate: 
 * -Es un framework ORM(Object Relational Maping, "Mapeo Objeto-Relacional") de persistencia de datos a 
 	una base de datos, el cual facilita la relacion entre la aplicacion y la base de datos.
 * -Facilita la implementacion de ORM(Object Relational Maping, “Mapeo Objeto-Relacional”), el cual simplemente  es el 
 	codigo que escribimos para guardar el valor de nuestras clases en una base de datos relacional. 
 * -Facilita la creacion de CRUDs a partir de los objetos que le proporcionemos.*/

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modell.Cliente;
import modell.JPAUtil;

public class ClienteDAO {
	// Atributos.
	// entity es un objeto que se trae de JPAUtil, el cual almacena la conexion a la BD.
	EntityManager entity = JPAUtil.getEntityManagerFactory_().createEntityManager();

	//Metodos.
	//Guardar cliente.
	public void guardar(Cliente cliente) {
		//El proceso lo realiza Hibernte.
		entity.getTransaction().begin(); //Comienza la transaccion.
		entity.persist(cliente); //Guarda en la BD.
		entity.getTransaction().commit(); //Finaliza la transaccion y realiza commit en la BD.
		// JPAUtil.shutdown(); //Cerrar conexion a la BD.
	}

	//Editar cliente
	public void editar(Cliente cliente) {
		//El proceso lo realiza Hibernte.
		entity.getTransaction().begin(); //Comienza la transaccion.
		entity.merge(cliente); //Actualia en la BD.
		entity.getTransaction().commit(); //Finaliza la transaccion y realiza commit en la BD.
		// JPAUtil.shutdown(); //Cerrar conexion a la BD.
	}

	//Buscar cliente
	public Cliente buscar(Long id) {
		Cliente c = new Cliente();
		//El proceso lo realiza Hibernate.
		//find(clase donde esta instaciado el objeto que quiero de la BD, Registro que busco).
		c = entity.find(Cliente.class, id);
		// JPAUtil.shutdown(); //Cerrar conexion a la BD.
		return (c);
	}

	//Eliminar cliente
	public void eliminar(Long id) {
		Cliente c = new Cliente();
		//El proceso lo realiza Hibernate.
		//find(clase donde esta instaciado el objeto que quiero de la BD, Registro que busco) 
		c = entity.find(Cliente.class, id);
		entity.getTransaction().begin(); //Comienza la transaccion.
		entity.remove(c); //Borrar en la BD.
		entity.getTransaction().commit(); //Finaliza la transaccion y realiza commit en la BD.
	}

	//Obtener todos los clientes
	public List<Cliente> obtenerClientes() {
		List<Cliente> listaClientes = new ArrayList<>();
		
		//createQuery(sentencia JQL administrado por Hibernate).
		//c es un alias de la clase Cliente.
		Query q = entity.createQuery("SELECT c FROM Cliente c");
		//listaClientes recoge todos los registros de la tabla clientes.
		listaClientes = q.getResultList();
		
		return (listaClientes);
	}

}
