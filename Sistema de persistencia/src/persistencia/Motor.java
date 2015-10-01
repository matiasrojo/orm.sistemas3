package persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class Motor {

	protected int id;
	private ServiceLocator proveedor;

	
	public Motor()
	{
		this.proveedor = ServiceLocator.getInstance();
	}

	private ArrayList<Atributo> getChildAtributtes()
	{
		
		ArrayList<Atributo> respond = new ArrayList<Atributo>();
		
		for (Field field : this.getClass().getDeclaredFields()) {

			field.setAccessible(true);

			try {
				respond.add(new Atributo(field.getName(), field.getType(), (String) field.get(this)));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return respond;
	}

	private void setChildAtributtes(ArrayList<Atributo> attributes)
	{
		for(Atributo atributte : attributes)
		{
			try {

				/**
				 * Hay que hacer esta validación, ya que la reflexión no conoce
				 * los atributos protected del hijo
				 */
				if (atributte.getName().equals("id"))
				{
					this.getClass().getSuperclass().getDeclaredField("id").set(this, atributte.getValue());
				}
				else
				{
					Field field = this.getClass().getDeclaredField(atributte.getName());
					
					field.setAccessible(true);
					field.set(this, atributte.getValue());
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
		this.proveedor.getPersisterObject().save(this.getChildAtributtes(), 1);
	}

	public void load(int id)
	{
		this.setChildAtributtes(this.proveedor.getPersisterObject().load(id));
	}
}
