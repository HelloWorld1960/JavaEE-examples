package com.HelloWorld1960.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.api.jdbc.Statement;

/**DAO (Data Acces Object).
 * Encapsula el acceso a la base de datos. Por lo que cuando la capa logica de datos necesite interactuar 
 * con la base de datos, va a hacerlo a travez de la API(Application Programming Interface) que ofrece DAO.
 * Generalmente esta API consiste en metodos CRUD(Create, Read, Update y Delete). Entonces cuando la capa logica 
 * necesite guardar un dato en la base de datos va a llamar al metodo registrar().
 */
public class TestDAO {
	//Atributos.
	private Conexion con;

	//Constructor.
	public TestDAO(String jdbcURL, String jdbcUserName, String jdbcPassword) throws SQLException {
		//Instancia
		con = new Conexion(jdbcURL, jdbcUserName, jdbcPassword);
		//Llamar al metodo.
		//con.connection();
		//Imprime conexion.
		//System.out.println(con.getJdbcConnection());
	}
	
	//Metodo para Registrar.
	public boolean registrar(Articulo art) {
		boolean estado = false;
		Statement stm;//Sirve para procesar una sentencia SQL estática y obtener los resultados producidos por ella.
		
		/* Manejo de Transacciones: Una transacción es un conjunto de operaciones que van a ser tratadas como una única 
		 * unidad. Estas transacciones deben cumplir 4 propiedades fundamentales comúnmente conocidas como 
		 * ACID (atomicidad, coherencia, asilamiento y durabilidad).*/ 
		
		Articulo nuevoArticulo = new Articulo(0, "Sierra doble", "Sierra con doble hoja.", 250);
		//Consulta SQL.
		String sqlArticulo = "INSERT INTO articulos VALUES (NULL, '"+nuevoArticulo.getNombre()+"', '"+nuevoArticulo.getDescripcion()+"', "+nuevoArticulo.getPrecio()+")";
		
		//Consulta SQL.
		String sql = "INSERT INTO articulos VALUES (NULL, '"+art.getNombre()+"', '"+art.getDescripcion()+"', "+art.getPrecio()+")";
		
		try {
			//Abrir conexion.
			con.connection(); //Llama al metodo para crear conexion.
			
			//Inicio de la Transaccion
			con.getJdbcConnection().setAutoCommit(false); // Desactivamos auto commit.
			
			stm = (Statement) con.getJdbcConnection().createStatement(); //Obtiene conexion.
			
			//Ejecutar la consulta
			stm.executeUpdate(sqlArticulo); //Retorna un tipo booleano.
			stm.executeUpdate(sql); //Retorna un tipo booleano.
			
			con.getJdbcConnection().commit(); //Si todas las consultas se jecutaron correctamente, hacemos commit y guardamos en la BD.
			
			stm.close(); //Cierra consulta.
			
			con.disconnect(); //Llama al metodo para cerrar conexion.
			
			estado = true;
		} catch (SQLException e1) {
			try {
				estado = false;
				con.getJdbcConnection().rollback(); // Si ocurrio algun error en las consultas, entonces deshace las operaciones a la BD.
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e1.printStackTrace();
		}
		
		return estado;
	}
	
	//Metodo para actualizar un articulo.
	public boolean editar(Articulo art) {
		boolean estado = false;
		Statement stm;//Sirve para procesar una sentencia SQL estática y obtener los resultados producidos por ella.
		
		//Sonsulta SQL.
		String sql = "UPDATE articulos SET nombre='"+art.getNombre()+"' WHERE id="+art.getId();
		
		try {
			//Abrir conexion.
			con.connection(); //Llama al metodo para crear conexion.
			
			stm = (Statement) con.getJdbcConnection().createStatement(); //Obtiene conexion.
			
			//Ejecutar la consulta
			stm.execute(sql); //Retorna un tipo booleano.
			
			stm.close(); //Cierra consulta.
			
			con.disconnect(); //Llama al metodo para cerrar conexion.
			
			estado = true;
		} catch (SQLException e) {
			e.printStackTrace();
			estado = false;
		}
		
		return estado;
	}
	
	//Metodo para eliminar un articulo.
	public boolean eliminar(Articulo art) {
		boolean estado = false;
		Statement stm;//Sirve para procesar una sentencia SQL estática y obtener los resultados producidos por ella.
		
		//Sonsulta SQL.
		String sql = "DELETE FROM articulos WHERE id="+art.getId();
		
		try {
			//Abrir conexion.
			con.connection(); //Llama al metodo para crear conexion.
			
			stm = (Statement) con.getJdbcConnection().createStatement(); //Obtiene conexion.
			
			//Ejecutar la consulta
			stm.executeUpdate(sql); //Retorna un tipo booleano.
			
			stm.close(); //Cierra consulta.
			
			con.disconnect(); //Llama al metodo para cerrar conexion.
			
			estado = true;
		} catch (SQLException e) {
			e.printStackTrace();
			estado = false;
		}
		
		return estado;
	}
	
	//Consulta un articulo
	public void obtenerArticulo(int id) {
		Statement stm;
		ResultSet res = null;
		Articulo art;
		
		//Consulta SQL.
		String sql = "SELECT * FROM articulos WHERE id=" + id;
		
		try {
			//Abrir conexion.
			con.connection(); //Llama al metodo para crear conexion.
			
			stm = (Statement) con.getJdbcConnection().createStatement(); //Obtiene conexion.
			
			//Ejecutar la consulta
			res = stm.executeQuery(sql); //Retorna un tipo booleano.
			
			if (res.next()) {
				art = new Articulo(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getDouble("precio"));
				System.out.println(art);
			}
			
			stm.close(); //Cierra consulta.
			
			con.disconnect(); //Llama al metodo para cerrar conexion.
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Consultar todos los articulos.
	public void obtenerArticulos() {
		Statement stm;
		ResultSet res = null;
		Articulo art;
		
		//Consulta SQL.
		String sql = "SELECT * FROM articulos";
		
		try {
			//Abrir conexion.
			con.connection(); //Llama al metodo para crear conexion.
			
			stm = (Statement) con.getJdbcConnection().createStatement(); //Obtiene conexion.
			
			//Ejecutar la consulta
			res = stm.executeQuery(sql); //Retorna un tipo booleano.
			
			while (res.next()) {
				art = new Articulo(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getDouble("precio"));
				System.out.println(art);
			}
			
			stm.close(); //Cierra consulta.
			
			con.disconnect(); //Llama al metodo para cerrar conexion.
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
