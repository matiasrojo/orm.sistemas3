package programa;

import persistencia.Motor;
import persistencia.Table;

@Table(name = "perro")
public class Perro extends Motor {
	String nombre;
	Persona duenio;
	
	public Perro() {}
	
	public Perro(String nombre, Persona duenio) {
		this.nombre = nombre;
		this.duenio = duenio;
	}
	
	public String getName() {
		return this.nombre;
	}
	
	public void setName(String nombre) {
		this.nombre = nombre;
	}
	
	public Persona getDuenio() {
		return this.duenio;
	}
	
	public void setDuenio(Persona duenio) {
		this.duenio = duenio;
	}
}
