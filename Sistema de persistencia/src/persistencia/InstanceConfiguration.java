package persistencia;

import java.util.ArrayList;
import java.util.List;
 
/**
 * JavaBean que almacena la configuración de una instancia
 * EN: simple JavaBean to hold the instance configuration data
 * @author Cristóbal González Almirón
 *
 */
public class InstanceConfiguration {
 
       private String name;
       private String type;
       private String value;
       private List<Atributo> parameters =
              new ArrayList<Atributo>();
      
       public String getName() {
              return name;
       }
      
       public void setName(String name) {
              this.name = name;
       }
       public String getType() {
              return type;
       }
       public void setType(String type) {
              this.type = type;
       }
       public String getValue() {
              return value;
       }
       public void setValue(String value) {
              this.value = value;
       }
 
       public List<Atributo> getParameters() {
              return this.parameters;
       }
 
       public void setParameters(List<Atributo> parameters) {
              this.parameters = parameters;
       }                    
}