package persistencia;

import java.util.ArrayList;

public interface IMedio {
	
	public String getName();
	public ArrayList<Atributo> load(int id);
	public void save(ArrayList<Atributo> campos, int id);
}
