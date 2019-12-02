package com.HelloWorld1960.app;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Pool {
	//Atributos
	//Atributo declarado static para poder llamarlo sin necesidad de declarar antes un objeto del tipo Pool.
	private static BasicDataSource ds = null;

	//Metodos
	public static DataSource getDataSource_() {
		if (ds == null) {
			ds = new BasicDataSource(); // Instancia del objeto.
			ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); //Driver de nuestra conexión a la base de datos.
			ds.setUsername("root"); //Nombre de usuario de conexion a la BD.
			ds.setPassword(""); //Contraseña a la BD.
			ds.setUrl("jdbc:mysql://localhost:3306/jee_crud01?serverTimezone=UTC"); //
			
			// Definimos el tamano del pool de conexiones.
			ds.setInitialSize(50); // Conexiones iniciales al arrancar la aplicacion.
			ds.setMaxIdle(10); //Indica el número máximo de conexiones que puede haber esperando a que llegue un usuario para usarlas.
			ds.setMaxTotal(20); //Indica el número máximo de conexiones abiertas.
			ds.setMaxWaitMillis(5000); //Tiempo en que espera el pool para definir una conexion inactiva.
		}
		return ds;
	}

	//Al tratarse de un metodo estatico puedo llamarlo sin necesidad de crear el objeto.
	public static Connection getConexion_() throws SQLException {
		
		return (getDataSource_().getConnection());
		//Retornaria un objeto del tipo BasicDataSource: 
		//ds.getConnection();
	}
}
