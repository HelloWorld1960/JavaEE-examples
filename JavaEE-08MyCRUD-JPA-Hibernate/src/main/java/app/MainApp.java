package app;
/**Programa principal.
 *-Hibernate: Es un ORM(Object Relational Maping), el cual se encarga de todo el mapeo objeto-relacional.
 *-Facilita la creacion de CRUDs a partir de los objetos que le proporcionemos.
 * */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Producto;

public class MainApp {

	public static void main(String[] args) {
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		Producto producto;

		//Objeto que trae de JPAUtil el objeto EntityManagerFactory factory, el cual es la conexion a la BD.
		EntityManager entity = JPAUtil.getEntityManagerFactory_().createEntityManager();
		while (opcion!=5) {
			System.out.println("1. Crear Producto");
			System.out.println("2. Buscar Producto");
			System.out.println("3. Actualizar Producto");
			System.out.println("4. Eliminar Producto");
			System.out.println("5. Salir");
			System.out.println("Elija una opci�n:");

			opcion = scanner.nextInt();
			switch (opcion) {
			//Guardar.
			case 1:
				System.out.println("Digite el nombre del producto:");
				producto = new Producto();
				producto.setId(null);
				scanner.nextLine();
				producto.setNombre(scanner.nextLine());

				System.out.println("Digite el precio del producto:");
				producto.setPrecio(scanner.nextDouble());
				System.out.println(producto);

				//Guardar en la BD.
				//El proceso lo realiza Hibernte.
				entity.getTransaction().begin(); //Comienza la transaccion.
				entity.persist(producto); //Guarda en la BD.
				entity.getTransaction().commit(); //Finaliza la transaccion y realiza commit en la BD.

				System.out.println("Producto registrado..");
				System.out.println();
				break;
			//Buscar.
			case 2:
				System.out.println("Digite el id del producto a buscar:");
				producto = new Producto();

				//Buscar producto.
				//El proceso lo realiza Hibernate.
				//find(clase donde esta instaciado el objeto que quiero de la BD, Registro que busco)
				producto = entity.find(Producto.class, scanner.nextLong());

				if (producto != null) {
					System.out.println(producto);
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Producto no encontrado... Lista de productos completa");

					List<Producto> listaProductos= new ArrayList<>();

					//createQuery(sentencia JQL administrado por Hibernate).
					//p es un alias de la clase Producto.
					Query query = entity.createQuery("SELECT p FROM Producto p"); //Consulta.
					//listaProductos recoge todos los registros de la tabla productos.
					listaProductos = query.getResultList();
					//Imprimir productos.
					for (Producto p : listaProductos) {
						System.out.println(p);
					}

					System.out.println();
				}

				break;
			//Actualizar.
			case 3:
				System.out.println("Digite el id del producto a actualizar:");
				producto = new Producto();

				//Buscar el producto.
				//El proceso lo realiza Hibernate.
				//find(clase donde esta instaciado el objeto que quiero de la BD, Registro que busco)
				producto = entity.find(Producto.class, scanner.nextLong());

				if (producto != null) {
					System.out.println(producto);
					System.out.println("Digite el nombre del producto:");
					scanner.nextLine();
					producto.setNombre(scanner.nextLine());
					System.out.println("Digite el precio del producto:");
					producto.setPrecio(scanner.nextDouble());

					//Actualizar en la BD.
					//El proceso lo realiza Hibernte.
					entity.getTransaction().begin(); //Comienza la transaccion.
					entity.merge(producto); //Actualiza en la BD.
					entity.getTransaction().commit(); //Finaliza la transaccion y realiza commit en la BD.

					System.out.println("Producto actualizado..");
					System.out.println();
				} else {
					System.out.println("Producto no encontrado....");
					System.out.println();
				}
				break;
			//Borrar.
			case 4:
				System.out.println("Digite el id del producto a eliminar:");
				producto = new Producto();

				//Buscar el producto.
				//El proceso lo realiza Hibernate.
				//find(clase donde esta instaciado el objeto que quiero de la BD, Registro que busco)
				producto = entity.find(Producto.class, scanner.nextLong());

				if (producto != null) {
					System.out.println(producto);

					//Borrar en la BD.
					//El proceso lo realiza Hibernte.
					entity.getTransaction().begin(); //Comienza la transaccion.
					entity.remove(producto); //Borrar en la BD.
					entity.getTransaction().commit(); //Finaliza la transaccion y realiza commit en la BD.

					System.out.println("Producto eliminado...");
				} else {
					System.out.println("Producto no encontrado...");
				}
				break;

			case 5:
				entity.close();
				JPAUtil.shutdown();
			break;

			default:
				System.out.println("Opci�n no v�lida\n");
				break;
			}
		}
	}

}
