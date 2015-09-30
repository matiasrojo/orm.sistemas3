package persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class Motor {

	protected int id;

	private ArrayList<Atributo> childatributtes;
	private ServiceLocator proveedor;

	public Motor()
	{
		this.proveedor = ServiceLocator.getInstance();
		this.loadChildAtributtes();
	}

	private void loadChildAtributtes()
	{
		this.childatributtes = new ArrayList<Atributo>();

		for (Field field : this.getClass().getDeclaredFields()) {

			field.setAccessible(true);

			try {
				childatributtes.add(new Atributo(field.getName(), field.getType(), (String) field.get(this)));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private void refreshChildAtributtesValues()
	{
		int i = 0;

		for (Field field : this.getClass().getDeclaredFields()) {

			field.setAccessible(true);

			try {
				childatributtes.get(i).setValue((String) field.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

			i++;
		}
	}

	private void setChildAtributtes()
	{
		for(Atributo atributte : this.childatributtes)
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
		this.refreshChildAtributtesValues();
		this.proveedor.getPersisterObject().save(this.childatributtes, 1);
	}

	public void load(int id)
	{
		this.childatributtes = this.proveedor.getPersisterObject().load(id);
		this.setChildAtributtes();
	}
}
