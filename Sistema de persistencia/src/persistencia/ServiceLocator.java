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

			System.out.println("\nSERVICE LOCATOR> Cargando el Sistema Gestor de Base de Datos...");
			
			HashMap<String, String> parameters = LectorXML.loadConfiguration();
			
			Class<?> c = Class.forName(parameters.get("Clase"));
			this.db = (SistemaGestor) c.newInstance();
			
			System.out.println("SERVICE LOCATOR> Se ha cargado el Sistema Gestor: " + parameters.get("SistemaGestor"));
			
			this.db.setConfigurations(parameters);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public SistemaGestor getSG()
	{
		return this.db;
	}

}
