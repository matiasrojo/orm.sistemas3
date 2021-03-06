package persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class Motor {
	private ServiceLocator proveedor;
	private String childTableName;
	private int instanceId;

	
	public Motor()
	{
		this.childTableName = this.getChildTableName();
		this.proveedor = ServiceLocator.getInstance();
		this.instanceId = this.proveedor.getPersisterObject().getLastId(this.childTableName) + 1;
	}
	
	private String getChildTableName()
	{
		return this.getClass().getAnnotation(Table.class).name();
	}

	private ArrayList<Atributo> getChildAttributes()
	{
		
		ArrayList<Atributo> respond = new ArrayList<Atributo>();
		
		for (Field field : this.getClass().getDeclaredFields()) {

			field.setAccessible(true);

			try {
				if(field.get(this) instanceof Motor) {
					Motor obj = (Motor) field.get(this);
					respond.add(new Atributo(field.getName(), int.class, (String) String.valueOf(obj.getInstanceId())));
				} else {
					respond.add(new Atributo(field.getName(), field.getType(), (String) field.get(this)));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return respond;
	}

	private void setChildAttributes(ArrayList<Atributo> attributes)
	{
		for(Atributo attribute : attributes)
		{
			try {

				/**
				 * Hay que hacer esta validaci�n, ya que la reflexi�n no conoce
				 * los atributos protected del hijo
				 */
				if (!attribute.getName().equals("id"))
				/*{
					//this.getClass().getSuperclass().getDeclaredField("instanceId").set(this, attribute.getValue());
				}
				else*/
				{
					Field field = this.getClass().getDeclaredField(attribute.getName());
					
					field.setAccessible(true);
					
					//Esto es horrible pero no encontre otra forma
					String clase = field.getType().toString().substring(6);
					Object instancia = Motor.getInstance(clase);
					
					if(instancia instanceof Motor) {
						instancia = ((Motor) instancia).load((Integer) attribute.getValue());
						field.set(this, instancia);
					} else {
						field.set(this, attribute.getValue());
					}
				}

			} catch (IllegalArgumentException |  NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean save()
	{
		if(this.proveedor.getPersisterObject().save(this.childTableName, this.getChildAttributes(), this.instanceId)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean delete(int id)
	{
		if(this.proveedor.getPersisterObject().delete(this.childTableName, id)){
			return true;
		}
		else {
			return false;
		}
		
	}

	public Motor load(int id)
	{
		this.setChildAttributes(this.proveedor.getPersisterObject().load(this.childTableName, id));
		this.instanceId = id;
		return this;
	}
	
	public int getInstanceId() {
		return this.instanceId;
	}
	
	public static Object getInstance(String clase) {
		Object o = null;

		try {
			o = Class.forName(clase).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public void setId(int id) {
		this.instanceId = id;
	}
}
