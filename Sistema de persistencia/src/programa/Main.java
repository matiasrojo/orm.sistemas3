package programa;

import persistencia.ServiceLocator;

public class Main {

	public static void main(String[] args) {
		
		ServiceLocator.getInstance().selectPersisterObject("Firebird");
		
		Persona personita = new Persona();
		
		System.out.println("\nPROGRAMA> levantando al usuario con ID = " + 1 + " ..." );
		
		personita.load(1);
		
		System.out.println("\nPROGRAMA> usuario " + personita.getName() + " levantado.");
		System.out.println("\nPROGRAMA> " + personita.getName() + " " + personita.getLastname());

	}

}
