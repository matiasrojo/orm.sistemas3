package programa;

import persistencia.ServiceLocator;

public class Main {

	public static void main(String[] args) {
		
		ServiceLocator.getInstance().selectPersisterObject("Firebird");
		
		System.out.println("\nPROGRAMA> levantando al usuario con ID = " + 1 + " ..." );
		
		Persona personita = (Persona) new Persona().load(1);
		
		System.out.println("\nPROGRAMA> usuario " + personita.getName() + " levantado.");
		System.out.println("\nPROGRAMA> " + personita.getName() + " " + personita.getLastname());
		
		Persona personita2 = (Persona) new Persona().load(2);
		
		System.out.println("\nPROGRAMA> usuario " + personita2.getName() + " levantado.");
		System.out.println("\nPROGRAMA> " + personita2.getName() + " " + personita2.getLastname());
		
		//Perro perro = new Perro("Zack", personita2);
		
		//System.out.println(perro.getDuenio().getName());
		
		//perro.save();
		
		/*personita2.setName("Pato");
		personita2.setLastname("Torres");
		personita2.save();*/
		
		Perro perro = (Perro) new Perro().load(1);
		
		/*perro.setName("Zack");
		perro.setDuenio(personita2);
		perro.save();*/
		
		System.out.println("\nEl dueño de " + perro.getName() + " es " + perro.getDuenio().getName());
		
		/*Persona persona = new Persona();
		persona.setName("Juan");
		persona.setLastname("Pérez");
		
		Perro perro = new Perro();
		perro.setName("Manchitas");
		perro.setDuenio(persona);
		
		persona.save();
		perro.save();*/
		
		Perro perro2 = (Perro) new Perro().load(2);
		
		System.out.println("\nEl dueño de " + perro2.getName() + " es " + perro2.getDuenio().getName());
	}

}
