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
	private Connection connection = null;
	
	private Connection connectDB()
	{
		if(connection == null) {
			try{
				
				System.out.println("\n" + this.sgbdname.toUpperCase() + "> Conectando a la base de datos " + this.dbname + " ..." );
	
				Class.forName(this.driver);
				connection = DriverManager.getConnection(this.connectionstring + this.dbname, this.user, this.password);
	
				System.out.println(this.sgbdname.toUpperCase() + "> Conexi�n realizada.");
	
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		return connection;
	}
	
	public ArrayList<Atributo> load(String tableName, int id) {
		
		ArrayList<Atributo> respond = new ArrayList<Atributo>();
		
		try {
			
			System.out.println("\n" + this.sgbdname.toUpperCase() + "> obteniendo la tupla con ID = " + id + " ..." );
			
			Statement statement = this.connectDB().createStatement();
			ResultSet rs = null;
			Class<?> type_class = null;
			
			rs = statement.executeQuery("SELECT * FROM " + tableName + " WHERE ID  = " + id);
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
	
	public void save(String tableName, ArrayList<Atributo> campos, int id) {
		Connection connection = null;
		
		try {
			connection = this.connectDB();
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			
			String query = "UPDATE OR INSERT INTO " + tableName + "(ID";
			for(Atributo campo: campos)
				query += ", " + campo.getName().toUpperCase();
			
			query += ") VALUES(" + String.valueOf(id);
			
			for(Atributo campo: campos) {
				System.out.println(campo.getName());
				System.out.println(campo.getType());
				System.out.println(campo.getValue());
				if(campo.getType() == String.class)
					query += ", '" + campo.getValue() + "'";
				else
					query += ", " + campo.getValue();
			}
			query += ");";
			
			System.out.println("\nEjecutando consulta " + query);
			statement.addBatch(query);
			statement.executeBatch();
			connection.commit();
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getName() {
		return this.sgbdname;
	}
}
