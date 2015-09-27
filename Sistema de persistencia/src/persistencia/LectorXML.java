package persistencia;

import java.util.HashMap;

public class LectorXML {
	
	public static HashMap<String, String> loadConfiguration()
	{
		HashMap<String, String> respond = new HashMap<String, String>();
		
		respond.put("clase", "gestorprueba.prueba");
		
		return respond;
	}
}
