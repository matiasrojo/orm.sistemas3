package persistencia;

import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LectorXML {
	
	public static HashMap<String, String> loadConfiguration()
	{
		HashMap<String, String> respond = new HashMap<String, String>();
		
		Document document = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			
			builder = factory.newDocumentBuilder();
			document = builder.parse(LectorXML.class.getResourceAsStream("conf.xml"));
			
			NodeList listgestor = document.getElementsByTagName("SistemaGestor");
			
			for (int i = 0; i < listgestor.getLength(); i++) {
				
				Element sistemagestor = (Element) listgestor.item(i);
				respond.put("SistemaGestor",(sistemagestor.getAttribute("name")));
				NodeList listparametro = sistemagestor.getElementsByTagName("Parametro");
				
				for (int j = 0; j < listparametro.getLength(); j++) {
					
					Element parametro = (Element) listparametro.item(j);
					respond.put(parametro.getAttribute("name"),(parametro.getAttribute("value")));
				}
			}
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return respond;
	}
}
