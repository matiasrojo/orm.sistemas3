package persistencia;

import java.io.IOException;
import java.util.Map;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
//Clase simple de utilidad para leer la lista de instancias y parámetros
//desde un fichero de configuración en formato XML
//EN: simple utility class to read the `parameters and instances
// configuration list from a xml file
public class InstanceConfigurationReader {
 
 
       /** lee la configuración y crea la lista de
        * instanceConfiguratios
        */   
       public void readConfiguration(
                      Map<String, InstanceConfiguration> instanceConfigurations) {
              Document document = null;
             
              //Carga del fichero de configuración
              //EN: load de xml configuration file
              DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
              DocumentBuilder builder;
              System.out.println("###DependencyConfiguratorReader.readConfiguration");
              try {
                      builder = factory.newDocumentBuilder();          
                      document = builder.parse(
                                    this.getClass().getResourceAsStream("instance-config.xml"));
      
                      // Creación de las configuraciones
                      NodeList list = document.getElementsByTagName("instance");
                      for (int i=0; i<list.getLength(); ++i){
                             Element element = (Element) list.item(i);
                            
                             //creamos la configuración de la instancia
                             InstanceConfiguration instanceConfiguration =
                                           new InstanceConfiguration();
                             instanceConfiguration.setName(element.getAttribute("name"));
                             instanceConfiguration.setType(element.getAttribute("type"));
                            
                             NodeList parameterNodes = element.getElementsByTagName("parameter");
                             for (int j=0; j < parameterNodes.getLength(); ++j) {
                                    Element parameterNode = (Element) parameterNodes.item(j);
                                    Atributo pc = new Atributo();
                                    pc.setName(parameterNode.getAttribute("name"));
                                    
                                    try {
										pc.setType(Class.forName(parameterNode.getAttribute("type")));
									} catch (ClassNotFoundException e) {
										e.printStackTrace();
									}
                                    
                                    pc.setValue(parameterNode.getAttribute("value"));
                                    instanceConfiguration.getParameters().add(pc);
                             }
                             instanceConfigurations.put(element.getAttribute("name"),
                                           instanceConfiguration);
                      }
              } catch (SAXException e) {
                      e.printStackTrace();
              } catch (ParserConfigurationException e) {
                      e.printStackTrace();
              } catch (IOException e) {
                      e.printStackTrace();
              }
       }
}
