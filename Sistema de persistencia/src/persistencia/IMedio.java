package persistencia;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMedio {
	
	public void setConfigurations(HashMap<String, String> parameters);
	public ArrayList<Atributo> load(int id);
	public void save(ArrayList<Atributo> campos, int id);
}
