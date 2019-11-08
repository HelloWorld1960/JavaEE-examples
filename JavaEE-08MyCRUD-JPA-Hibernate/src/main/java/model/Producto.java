package model;
/*Esta clase sera utlizada por Hibernate para persistir el objetos del tipo producto.
 * Hibernete reconoce a todas las que seran tablas por medio de anotaciones.*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //Le indica a hibernate que se trata de una entidad que va a persistirse en la BD.
@Table(name="productos") //Le indica a Hibernate el nombre de la tabla.
public class Producto {
	//Atributos
	@Id //Le indica a Hibernate que es el id de la tabla.
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Le indica a Hibernate que es un campo Autoincrementable.
	private Long id;
	@Column(nullable = true) //Le indica a Hibernate que hay un campo en la BD con ese nombre y es not null.
	private String nombre;
	@Column(nullable = true) //Le indica a Hibernate que hay un campo en la BD con ese nombre y es not null.
	private double precio;
	
	//Metodos
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	
}