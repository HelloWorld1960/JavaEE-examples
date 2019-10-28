/**
 * Conexion a MySQL.
 * 
 * */
package com.HelloWorld1960.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	/*Atributos necesarios para la conexion.
	 * Los valores jdbcURL y su valor se asignan en el archivo web.xml*/
	private String jdbcURL;
	private String jdbcUserName;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	/*Constructor*/
	public Conexion(String jdbcURL, String jdbcUserName, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUserName = jdbcUserName;
		this.jdbcPassword = jdbcPassword;
	}
	
	/*Metodos*/
	/*Crear conexion.*/
	public void connection() throws SQLException {
		/*Revisar si hay una conexion existente.*/
		if(jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); //Cargar el controlador para realizar la conexion.
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			} 
			/*Estableciendo la conexion con la BD.*/
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
		}
	}
	
	/*Cerrar conexion.*/
	public void disconnect() throws SQLException {
		/*Revisar si hay una conexion existente.*/
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			/*Cerrar conexion.*/
			jdbcConnection.close();
		}
	}
	
	/*Obtener conexion.*/
	public Connection getJdbcConnection() {
		
		return jdbcConnection;
	}
}
