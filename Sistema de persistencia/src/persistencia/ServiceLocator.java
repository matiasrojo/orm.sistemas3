package persistencia;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

	private static ServiceLocator instance = new ServiceLocator();

	private Map<String,Object> instances = new HashMap<String, Object>();
	private Map<String,InstanceConfiguration> instanceConfigurations = new HashMap<String, InstanceConfiguration>();
	private InstanceConfigurationReader instanceConfigurationReader = new InstanceConfigurationReader();
	private InstanceConfigurator instanceConfigurator = new InstanceConfigurator();
	private InstanceCreator instanceCreator = new InstanceCreator();

	private IMedio persisterobject = null;

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

	private Map<String, Object> getPersistencesInstances() {

		return instances;
	}

	public void loadPersisterObject()
	{
		System.out.println("\nSERVICE LOCATOR> Cargando el medio de persistencia ...");

		instanceConfigurationReader.readConfiguration(instanceConfigurations);
		instanceCreator.createInstances(instanceConfigurations, instances);
		instanceConfigurator.configureInstances(
				instanceConfigurations,
				instances);

		
		System.out.println("SERVICE LOCATOR> Se han cargado " + instances.size() + " medios de persistencias");
	}
	
	public void selectPersisterObject(String name) 
	{
		this.persisterobject = (IMedio) this.getPersistencesInstances().get(name);
	}

	public IMedio getPersisterObject()
	{
		return this.persisterobject;
	}

}
