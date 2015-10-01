package programa;

import persistencia.Table;
import persistencia.Motor;

@Table(name = "Persona")
public class Persona extends Motor {

	private String name;
	private String lastname;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
