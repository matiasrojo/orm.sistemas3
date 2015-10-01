package persistencia;

import java.util.ArrayList;

public interface IMedio {
	
	public String getName();
	public ArrayList<Atributo> load(String table, int id);
	public void save(String table, ArrayList<Atributo> campos, int id);
}
