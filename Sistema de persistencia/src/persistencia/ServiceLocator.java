package persistencia;

import java.util.HashMap;

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
		try {

			HashMap<String, String> parameters = LectorXML.loadConfiguration();
			
			Class<?> c = Class.forName(parameters.get("clase"));
			db = (SistemaGestor) c.newInstance();
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public SistemaGestor getSG()
	{
		return db;
	}

}
