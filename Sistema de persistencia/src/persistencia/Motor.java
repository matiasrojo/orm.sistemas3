package persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class Motor {
	
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
	
	public void save()
	{
		this.refreshChildAtributtesValues();
		this.proveedor.getSG().save(this.childatributtes, 1);
	}
	
	public void load()
	{
		
	}
}
