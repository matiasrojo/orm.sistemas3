package programa;

import persistencia.ServiceLocator;

public class Main {

	public static void main(String[] args) {

		Vista vista = new Vista();
		vista.setVisible(true);
		
		ServiceLocator.getInstance().selectPersisterObject("Firebird");	
		
		//Perro perro = new Perro("Zack", personita2);
		
		//System.out.println(perro.getDuenio().getName());
		
		//perro.save();
		
		//Perro perro = (Perro) new Perro().load(3);
		//perro = (Perro) new Perro().load(3);
		
		//System.out.println(perro.getDuenio().getName());
	}

}
