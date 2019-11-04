package com.HelloWorld1960.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**DAO (Data Acces Object).
 * Encapsula el acceso a la base de datos. Por lo que cuando la capa logica de datos necesite interactuar 
 * con la base de datos, va a hacerlo a travez de la API(Application Programming Interface) que ofrece DAO.
 * Generalmente esta API consiste en metodos CRUD(Create, Read, Update y Delete). Entonces cuando la capa logica 
 * necesite guardar un dato en la base de datos va a llamar al metodo registrar().
 */
/**Pool de conexiones.
 * El pool de conexiones es una técnica usada en aplicaciones Web para mejorar el rendimiento de las aplicaciones Web. 
 * 
 * */
public class TestDAO {
	//Atributos.
	private Connection con = null;

	//Constructor.
	public TestDAO() {

	}
	
	//Metodo para Registrar.
	public boolean registrar(Articulo art) throws SQLException {
		boolean estado = false;
		Statement stm; //Sirve para procesar una sentencia SQL estática y obtener los resultados producidos por ella.
		
		con = getConnection_(); // Solicitamos una conexion al pool.
		
		//Consulta SQL.
		String sql = "INSERT INTO articulos VALUES (NULL, '"+art.getNombre()+"', '"+art.getDescripcion()+"', "+art.getPrecio()+")";
		
		try {
			con.setAutoCommit(false); // Indicamos el inicio de la transacción
			
			//Inicio de la Transaccion	
			stm = con.createStatement(); // Creamos nuestro objecto de consulta.
			
			//Ejecutar la consulta
			stm.executeUpdate(sql); //Retorna un tipo booleano.
			
			con.commit(); //Si todas las consultas se jecutaron correctamente, hacemos commit y guardamos en la BD.
			
			estado = true;
			
			stm.close(); //Cierra consulta.
			
			con.close(); //Llama al metodo para cerrar conexion y regresarla al pool de conexiones para que alguien mas la utilize.
		} catch (SQLException e1) {
			try {
				estado = false;
				con.rollback(); // Si ocurrio algun error en las consultas, entonces deshace las operaciones a la BD.
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e1.printStackTrace();
		}
		
		return estado;
	}
	
	/*Accede a la conexión de la clase Pool.
	Este metodo nos regresa una conexion a base de datos, esta la podemos usar como una conexion usual*/
	private Connection getConnection_() throws SQLException {
		return (Pool.getConexion_()); //AgetConexion_ es un metodo estatico y puedo llamarlo sin necesidad de crear el objeto.
	}
	
}
