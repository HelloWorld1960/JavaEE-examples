package dao;
/**MODELO: Contiene una representacion de de los datos que maneja el sistema, su logica de negocio y sus mecanismos 
   de persistencia.
 *-Este modelo contiene los metodos con las consultas basicas CRUD (Create, Read, Update y Delete) a la tabla Producto.
 *-Patron DAO (Data Access Object): Este patron de la capa de Acceso a Datos se  encarga de extraer y 
 *almacenar informacion de la base de datos. 
 **/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexion.Conexion;
import model.Producto;

public class ProductoDAO {
	//Atributos.
	private Connection con = null; //Variable de tipo Connection
	private PreparedStatement stm; //Permite usar instrucciones preparadas o SQL basicas.
	private boolean estadoOperacion; 
	
	//Metodos
	//Guardar producto.
	public boolean guardar(Producto producto) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		con = obtenerConexion_(); // Solicitamos una conexion al pool.
		
		try {
			con.setAutoCommit(false); //(Transaccion)Indicamos el inicio de la transacción.
			//Consulta
			sql = "INSERT INTO productos (id, nombre, cantidad, precio, fecha_crear, fecha_actualizar) VALUES (?,?,?,?,?,?)";
			//Creamos el Statement para poder hacer consultas.
			stm = con.prepareStatement(sql); 
			
			//Obtenemos y Asignamos los elementos a guardar. 
			stm.setString(1, null);
			stm.setString(2, producto.getNombre());
			stm.setDouble(3, producto.getCantidad());
			stm.setDouble(4, producto.getPrecio());
			stm.setDate(5, producto.getFechaCrear());
			stm.setDate(6, producto.getFechaActualizar());
			
			//Ejecutar la consulta
			//Retorna un tipo entero.
			estadoOperacion = stm.executeUpdate() > 0;
			
			//Cerrar conexion.
			con.commit(); //(Transaccion)Si todas las consultas se jecutaron correctamente, hacemos commit y guardamos en la BD.
			stm.close(); //Cierra consulta.
			con.close(); //Cerrar conexion y regresarla al pool de conexiones para que alguien mas la utilize.
		} catch (SQLException e) {
			con.rollback(); //(Transaccion)Si ocurrio algun error en las consultas, entonces deshace las operaciones a la BD.
			e.printStackTrace();
		}
				
		return (estadoOperacion);
	}
	
	//Editar producto.
	public boolean editar(Producto producto) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		con = obtenerConexion_(); // Solicitamos una conexion al pool.
		
		try {
			con.setAutoCommit(false); //(Transaccion)Indicamos el inicio de la transacción.
			
			//Consulta
			sql = "UPDATE productos SET nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";
			//Creamos el Statement para poder hacer consultas.
			stm = con.prepareStatement(sql);
			
			//Obtenemos y Asignamos los elementos a Editar.
			stm.setString(1, producto.getNombre());
			stm.setDouble(2, producto.getCantidad());
			stm.setDouble(3, producto.getPrecio());
			stm.setDate(4, producto.getFechaActualizar());
			stm.setInt(5, producto.getId());
			
			//Ejecutar la consulta
			//Retorna un tipo entero.
			estadoOperacion = stm.executeUpdate() > 0;
			
			//Cerrar conexion.
			con.commit(); //(Transaccion)Si todas las consultas se jecutaron correctamente, hacemos commit y guardamos en la BD.
			stm.close(); //Cierra consulta.
			con.close(); //Cerrar conexion y regresarla al pool de conexiones para que alguien mas la utilize.
		} catch (SQLException e) {
			con.rollback(); //(Transaccion)Si ocurrio algun error en las consultas, entonces deshace las operaciones a la BD.
			e.printStackTrace();
		}
		
		return (estadoOperacion);
	}
	
	//Eliminar producto.
	public boolean eliminar(int idProducto) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		con = obtenerConexion_(); // Solicitamos una conexion al pool.
		
		try {
			con.setAutoCommit(false); //(Transaccion)Indicamos el inicio de la transacción.
			
			//Consulta
			sql = "DELETE FROM productos WHERE id=?";
			//Creamos el Statement para poder hacer consultas.
			stm = con.prepareStatement(sql);
			
			//Obtenemos y Asignamos los elementos a Eliminar.
			stm.setInt(1, idProducto);
			
			//Ejecutar la consulta
			//Retorna un tipo entero.
			estadoOperacion = stm.executeUpdate() > 0;
			
			//Cerrar conexion.
			con.commit(); //(Transaccion)Si todas las consultas se jecutaron correctamente, hacemos commit y guardamos en la BD.
			stm.close(); //Cierra consulta.
			con.close(); //Cerrar conexion y regresarla al pool de conexiones para que alguien mas la utilize.
		} catch (SQLException e) {
			con.rollback(); //(Transaccion)Si ocurrio algun error en las consultas, entonces deshace las operaciones a la BD.
			e.printStackTrace();
		}
		
		return (estadoOperacion);
	}
	
	//Obtener lista de productos.
	public List<Producto> obtenerProductos() throws SQLException {
		String sql = null;
		estadoOperacion = false;
		con = obtenerConexion_(); //Solicitamos una conexion al pool.
		ResultSet rs = null; //Conjunto de resultados que se devuelven de una query.
		List<Producto> listaProductos = new ArrayList<>();  //Variable de tipo List que almacena objetos del tipo Producto.
		
		try {
			
			sql = "SELECT * FROM productos";
			stm = con.prepareStatement(sql);
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Producto p = new Producto();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setCantidad(rs.getDouble(3));
				p.setPrecio(rs.getDouble(4));
				p.setFechaCrear(rs.getDate(5));
				p.setFechaActualizar(rs.getDate(6));
				listaProductos.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (listaProductos);
	}
	
	//Obtener producto.
	public Producto obtenerProducto(int idProducto) throws SQLException {
		ResultSet rs = null; //ResultSet: Interface que representa un conjunto de datos de una tabla. Es utilizado para ejecutar una consulta sobre la base de datos.
		Producto p = new Producto();
		String sql = null;
		estadoOperacion = false;
		con = obtenerConexion_(); // Solicitamos una conexion al pool.
		
		try {
			//Consulta
			sql = "SELECT * FROM productos WHERE id=?";
			//Creamos el Statement para poder hacer consultas.
			stm = con.prepareStatement(sql);
			//Obtenemos y Asignamos los elementos a Eliminar.
			stm.setInt(1, idProducto);
			
			//Ejecutar la consulta
			//Traer los resultados de la consulta.
			//executeQuery(): Metodo que ejecuta una query y devuelve el resultado en un ResultSet. 
			rs = stm.executeQuery();
			
			if (rs.next()) { //result.next() Obtiene la primer fila de la consulta.
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setCantidad(rs.getDouble(3));
				p.setPrecio(rs.getDouble(4));
				p.setFechaCrear(rs.getDate(5));
				p.setFechaActualizar(rs.getDate(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (p);
	}
	
	//Metodo que obtiene una conexion del pool.
	private Connection obtenerConexion_() throws SQLException {
		
		return (Conexion.getConexion_());
	}

}
