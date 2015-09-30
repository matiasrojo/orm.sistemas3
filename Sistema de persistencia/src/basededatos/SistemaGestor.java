package basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import persistencia.Atributo;
import persistencia.IMedio;

public class SistemaGestor implements IMedio {

	private String driver;
	private String sgbdname;
	private String connectionstring;
	private String dbname;
	private String user;
	private String password;
	
	private Connection connectDB()
	{
		Connection connection = null;
		
		try{
			
			System.out.println("\n" + this.sgbdname.toUpperCase() + "> Conectando a la base de datos " + this.dbname + " ..." );

			Class.forName(this.driver);
			connection = DriverManager.getConnection(this.connectionstring + this.dbname, this.user, this.password);

			System.out.println(this.sgbdname.toUpperCase() + "> Conexión realizada.");

		}catch(Exception e){
			System.out.println(e);
		}
		
		return connection;
	}

	public ArrayList<Atributo> load(int id) {
		
		ArrayList<Atributo> respond = new ArrayList<Atributo>();
		
		try {
			
			System.out.println("\n" + this.sgbdname.toUpperCase() + "> obteniendo la tupla con ID = " + id + " ..." );
			
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
		
		System.out.println(this.sgbdname.toUpperCase() + "> tupla obtenida." );
		
		return respond;
	}

	
	public void save(ArrayList<Atributo> campos, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return this.sgbdname;
	}

}
