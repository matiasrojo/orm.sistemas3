package programa;

import persistencia.ServiceLocator;

public class Main {

	public static void main(String[] args) {
		
		ServiceLocator.getInstance().selectPersisterObject("Firebird");
		
		Vista vista = new Vista();
		vista.setVisible(true);
		
	}

}
