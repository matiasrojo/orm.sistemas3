package persistencia;

import java.util.HashMap;

public class ServiceLocator {

	private static ServiceLocator instance = new ServiceLocator();
	private IMedio persistenceobject;

	private ServiceLocator() 
	{
		this.loadPersisterObject();
	}

	public static ServiceLocator getInstance() {

		if (instance == null) {
			instance = new ServiceLocator();
		}
		
		return instance;
	}

	public void loadPersisterObject()
	{
		try {

			System.out.println("\nSERVICE LOCATOR> Cargando el medio de persistencia ...");
			
			HashMap<String, String> parameters = LectorXML.loadConfiguration();
			
			Class<?> c = Class.forName(parameters.get("Class"));
			this.persistenceobject = (IMedio) c.newInstance();
			
			System.out.println("SERVICE LOCATOR> Se ha cargado el medio de persistencia: " + parameters.get("MedioPersistencia"));
			
			this.persistenceobject.setConfigurations(parameters);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public IMedio getPersisterObject()
	{
		return this.persistenceobject;
	}

}
