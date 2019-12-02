package conexion;
/**Pool de conexiones.
 *-Crear una nueva conexion tiene un impacto en recursos bastante alto, por lo que lo mas conveniente es manejar el concepto 
 * de pool de conexiones.
 *-El objetivo de un pool de conexiones es tener listas varias conexiones a la base de datos, de manera que sea mucho mas 
 * eficiente el proceso de obtener y liberar una conexion a la base de datos.
 * */
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
	// Atributos
	// Atributo declarado static para poder llamarlo sin necesidad de declarar antes un objeto del tipo Pool.
	private static BasicDataSource ds = null;

	// Metodos
	private static DataSource getDataSource_() {
		if (ds == null) {
			ds = new BasicDataSource(); // Instancia del objeto.
			ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Driver de la conexión a la base de datos.
			ds.setUsername("root"); // Nombre de usuario de conexion a la BD.
			ds.setPassword(""); // Contraseña de acceso a la BD.
			ds.setUrl("jdbc:mysql://localhost:3306/jee_crud02?serverTimezone=UTC"); //

			// Definimos el tamano del pool de conexiones.
			ds.setInitialSize(50); // Conexiones iniciales al arrancar la aplicacion.
			ds.setMaxIdle(10); // Establece el número máximo de conexiones que pueden estar inactivas esperando a que llegue un usuario para usarlas.
			ds.setMaxTotal(20); // Establece el número máximo de conexiones inactivas y prestadas que pueden estar activas al mismo tiempo.
			ds.setMaxWaitMillis(5000); // Tiempo en que espera el pool para definir una conexion inactiva.
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
