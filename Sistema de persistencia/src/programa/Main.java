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
		
		//Perro perro = (Perro) new Perro().load(3);
		//perro = (Perro) new Perro().load(3);
		
		//System.out.println(perro.getDuenio().getName());
	}

}
