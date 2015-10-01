package persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class Motor {

	public static int id = 0;
	private ServiceLocator proveedor;
	private String childTableName;
	private int instanceId;

	
	public Motor()
	{
		this.getChildTableName();
		this.proveedor = ServiceLocator.getInstance();
		Motor.id += 1;
		this.instanceId = Motor.id;
	}
	
	private void getChildTableName()
	{
		this.childTableName = this.getClass().getAnnotation(Table.class).name();
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
				if (attribute.getName().equals("id"))
				{
					//this.getClass().getSuperclass().getDeclaredField("instanceId").set(this, attribute.getValue());
				}
				else
				{
					Field field = this.getClass().getDeclaredField(attribute.getName());
					
					field.setAccessible(true);
					field.set(this, attribute.getValue());
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

	public void save()
	{
		this.proveedor.getPersisterObject().save(this.childTableName, this.getChildAttributes(), this.instanceId);
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
}
