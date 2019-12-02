package modell;
/**Esta clase contiene el codigo que llama al archivo persistence.xml para realizar la conexion a la BD.*/

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	//Atributos.
	private static final String PERSISTENCE_UNIT_NAME = "MyPERSISTENCE"; //nombre de la unidad de persistencia.
	private static EntityManagerFactory factory; //objeto del tipo EntityManagerFactory. 

	//Metodos.
	public static EntityManagerFactory getEntityManagerFactory_() {
		//Obtiene la conexon.
		if (factory==null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return (factory); //Retorna las propiedades de conexion a la BD.				
	}
	
	public static void shutdown() {
		//Cerrar conexion.
		if (factory!=null) {
			factory.close();
		}		
	}
}