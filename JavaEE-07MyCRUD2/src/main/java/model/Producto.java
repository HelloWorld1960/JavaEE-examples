package model;
/**MODELO: Contiene una representacion de de los datos que maneja el sistema, su logica de negocio y sus mecanismos 
   de persistencia.
 *-Este modelo representa una tabla en la base de datos y los atributos representan a las columnas 
   en la BD.
 *-JavaBean no es más que una clase en Java que sigue ciertos requisitos al momento de crearse de acuerdo a las
   especificaciones de la API para JavaBeans de la plataforma Java.
 * 1)Debe tener un constructor vacío.
 * 2)Debe implementar la interfaz Serializable
 * 3)Las propiedades/atributos deben ser privados.
 * 4)Debe tener métodos getters o setters o ambos que permitan acceder a sus propiedades.
   Se utiliza la interfaz Serializable debido a que por lo general la información que es persiste debe viajar mediante la red
   a un servidor por lo que un objeto que se envía a guardar debe ser descompuesto en bytes, la interfaz serializable permite
   que un objeto sea descompuesto a bytes y que al otro lado pueda ser reconstruido.
 */
import java.sql.Date;

public class Producto{
	//Atributos.
	private int id;
	private String nombre;
	private double cantidad;
	private double precio;
	private Date fechaCrear;
	private Date fechaActualizar;
	
	//Constructor vacio.
	public Producto() {
		// TODO Auto-generated constructor stub
	}
	
	//Constructor sobrecargado.
	public Producto(int id, String nombre, double cantidad, double precio, Date fechaCrear, Date fechaActualizar) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.fechaCrear = fechaCrear;
		this.fechaActualizar = fechaActualizar;
	}
	
	//Metodos
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public Date getFechaCrear() {
		return fechaCrear;
	}
	public void setFechaCrear(Date fechaCrear) {
		this.fechaCrear = fechaCrear;
	}
	
	public Date getFechaActualizar() {
		return fechaActualizar;
	}
	public void setFechaActualizar(Date fechaActualizar) {
		this.fechaActualizar = fechaActualizar;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", fechaCrear=" + fechaCrear + ", fechaActualizar=" + fechaActualizar + "]";
	}
}
