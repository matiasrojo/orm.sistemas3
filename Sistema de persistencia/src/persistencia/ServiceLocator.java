package persistencia;

import gestorprueba.prueba;

public class ServiceLocator {

	private static ServiceLocator instance = new ServiceLocator();
	private SistemaGestor db;

	private ServiceLocator() 
	{
		this.loadSG();
	}

	public static ServiceLocator getInstance() {

		if (instance == null) {
			instance = new ServiceLocator();
		}
		
		return instance;
	}

	public void loadSG()
	{
		db = new prueba();
	}

	public SistemaGestor getSG()
	{
		return db;
	}

}
