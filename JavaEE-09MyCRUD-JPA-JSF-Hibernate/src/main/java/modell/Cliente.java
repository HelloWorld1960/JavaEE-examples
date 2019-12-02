package modell;
/*Esta clase sera utlizada por Hibernate para persistir los objetos del tipo Cliente.
 *Hibernete reconoce a todas las que seran tablas por medio de anotaciones.*/

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity //Entity le indica a hibernate que se trata de una entidad que va a persistirse en la BD.
@Table(name="clientes") //Esta linea indica a Hibernate el nombre de la tabla.
public class Cliente {
	//Atributos.
	@Id //Le indica a Hibernate que es el id de la tabla.
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Le indica a Hibernate que es un campo Autoincrementable.
	private Long id;
	
	@Column(nullable = false) //Le indica a Hibernate que hay un campo en la BD con ese nombre y es NOT NULL.
	private String nombre;
	
	@Column(nullable = false) //Le indica a Hibernate que hay un campo en la BD con ese nombre y es NOT NULL.
	private String apellido;
	
	@Column(nullable = false) //Le indica a Hibernate que hay un campo en la BD con ese nombre y es NOT NULL.
	private String direccion;
	
	@Column(nullable = false) //Le indica a Hibernate que hay un campo en la BD con ese nombre y es NOT NULL.
	private String email;
	
	@Column(nullable = false) //Le indica a Hibernate que hay un campo en la BD con ese nombre y es NOT NULL.
	private String telefono;
	
	@Column(nullable = false) //Le indica a Hibernate que hay un campo en la BD con ese nombre y es NOT NULL.
	@Temporal(TemporalType.TIMESTAMP) //Le indica a Hibernate que almacena la fecha y hora.
	private Date fecha_registro;
	
	@Column(nullable = true) //Le indica a Hibernate que hay un campo en la BD con ese nombre y puede ser NULL.
	@Temporal(TemporalType.TIMESTAMP) //Le indica a Hibernate que hay un campo en la BD con ese nombre que almacena la fecha y hora.
	private Date fecha_actualizacion;
	
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
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}
	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", email=" + email + ", telefono=" + telefono + ", fecha_registro=" + fecha_registro
				+ ", fecha_actualizacion=" + fecha_actualizacion + "]";
	}
	
}
