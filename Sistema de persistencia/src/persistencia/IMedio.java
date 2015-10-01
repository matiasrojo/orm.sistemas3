package persistencia;

import java.util.ArrayList;

public interface IMedio {
	
	public String getName();
	public ArrayList<Atributo> load(String table, int id);
	public boolean save(String tableName, ArrayList<Atributo> campos, int id);
	public boolean delete(String tableName, int id);
	public int getLastId(String tableName);
}
