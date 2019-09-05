package com.HelloWordl1960.test;

import java.sql.SQLException;

/**DAO (Data Acces Object).
 * Encapsula el acceso a la base de datos. Por lo que cuando la capa logica de datos necesite interactuar 
 * con la base de datos, va a hacerlo a travez de la API que ofrece DAO.
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
		//Lalamar al metodo.
		con.connection();
		//Imprime conexion.
		System.out.println(con.getJdbcConnection());
	}
	
	
}
