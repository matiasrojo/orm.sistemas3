package firebird;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import persistencia.Atributo;
import persistencia.SistemaGestor;

public class firebird implements SistemaGestor {

	private String db;
	private String user;
	private String password;

	
	private Connection connectDB()
	{
		Connection connection = null;
		
		try{
			
			System.out.println("\nFIREBIRD> Conectando a la base de datos " + this.db + " ..." );

			Class.forName("org.firebirdsql.jdbc.FBDriver");
			connection = DriverManager.getConnection("jdbc:firebirdsql://localhost/" + this.db, this.user, this.password);

			System.out.println("FIREBIRD> Conexión realizada.");

		}catch(Exception e){
			System.out.println(e);
		}
		
		return connection;
	}

	@Override
	public void setConfigurations(HashMap<String, String> parameters) {

		this.db = parameters.get("Database");
		this.user = parameters.get("User");
		this.password = parameters.get("Password");

	}

	@Override
	public ArrayList<Atributo> load(int id) {
		
		ArrayList<Atributo> respond = new ArrayList<Atributo>();
		
		try {
			
			System.out.println("\nFIREBIRD> obteniendo la tupla con ID = " + id + " ..." );
			
			Statement statement = this.connectDB().createStatement();
			ResultSet rs = null;
			Class<?> type_class = null;
			
			rs = statement.executeQuery("SELECT * FROM PERSONA WHERE ID  = " + id);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next())
			{
				for (int i = 0; i < rsmd.getColumnCount(); i++)
				{
				   
				   if (rsmd.getColumnType(i + 1) == Types.VARCHAR || rsmd.getColumnType(i + 1) == Types.CHAR) 
				   {
					   type_class = String.class;
				   }
				   else if (rsmd.getColumnType(i + 1) == Types.INTEGER)
				   {
					   type_class = int.class;
				   }
				
				   respond.add(new Atributo(rsmd.getColumnName(i + 1).toLowerCase(), type_class, rs.getString(rsmd.getColumnName(i + 1))));
				}
			}
		}
		
		catch (SQLException ex) {
			System.out.println(ex);
		}
		
		System.out.println("FIREBIRD> tupla obtenida." );
		
		return respond;
	}

	@Override
	public void save(ArrayList<Atributo> campos, int id) {
		// TODO Auto-generated method stub

	}

}
