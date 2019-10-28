package com.HelloWorld1960.test;
/*Modelo: Este modelo representa una tabla en la base de datos y los atributos representan a las columnas 
 * en la BD.*/
public class Articulo {
	//Atributos
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;
	
	//Contructor.
	public Articulo() {
		// TODO Auto-generated constructor stub
	}
	
	//Constructor sobrecargado.
	public Articulo(int id, String nombre, String descripcion, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	//Metodos.
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

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	//Metodo para conocer el estado del objeto.
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ "]";
	}
	
	
}
