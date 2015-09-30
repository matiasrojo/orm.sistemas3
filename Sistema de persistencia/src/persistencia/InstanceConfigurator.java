package persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
/*^*
 * Configurador de instancias genérico simple
 * Funcionamiento: asigna valores a las diferentes propiedades
 * de una instancia, usando el API de reflexión de Java
 * EN: simple generic intance configurator
 * Configures the properties of an instance of a bean, using the
 * Java reflection API
 */
public class InstanceConfigurator {
 
       List<Atributo> parameters =
              new ArrayList<Atributo>();
      
      
       public void configureInstances(Map<String, InstanceConfiguration> instanceConfigurations,
                      Map<String, Object> instances){
             
              System.out.println("###instanceConfigurator.configureInstances");
              for (Map.Entry<String, InstanceConfiguration> entry: instanceConfigurations.entrySet()){
                     
                      InstanceConfiguration ic = entry.getValue();
                      Object instance = instances.get(ic.getName());
                      System.out.println(">Configurando Instancia: " + ic.getName() + ": classv" + ic.getType() + "= (value if String)" + ic.getValue());
                      try {
                            
                             //para cada parámetro se ajusta su valor
                             //EN: set value for each parameter
                             for (Atributo parameter : ic.getParameters()) {
                                    System.out.println("Procesando parámetro " + parameter.getName());
                                   
                                    //Si el parámetro es de tipo String asigna el valor a la propiedad
                                    //EN: if the param is a String, set the value to the property
                                    if (parameter.getType() == String.class) {
                                           Field attribute = instance.getClass().getDeclaredField(parameter.getName());
                                           attribute.setAccessible(true);
                                           //si es un String el valor del parámetro se asigna directamente
                                           attribute.set(instance, parameter.getValue());
                                           System.out.println("  Asignado a " + parameter.getName() + " el string " + parameter.getValue());
                                   
                                    //Si el parámetro es un objeto, asigna la instancia de la lista de instancias
                                    //creada con el InstanceCreator
                                    //EN: if the parameter is an Object, sets the property with an Objject instance
                                    //created with the InstanceCreator
                                    } else if (parameter.getType() == Object.class) {
                                           Field objectAttribute = instance.getClass().getDeclaredField (parameter.getName());
                                           objectAttribute.setAccessible(true);
                                           //Si es un objeto el valor del parámetro es el índice en la tabla de instancias
                                           objectAttribute.set(instance,instances.get(parameter.getValue()));
                                           System.out.println("  Asignado a " + parameter.getName() + " el objeto " + parameter.getValue());     
                                          
                                    } else
                                           System.out.println("  No es String o Object");
                                   
                             }
                      } catch (IllegalArgumentException e) {
                             e.printStackTrace();
                      } catch (SecurityException e) {
                             e.printStackTrace();
                      } catch (IllegalAccessException e) {
                             e.printStackTrace();
                      } catch (NoSuchFieldException e) {
                             e.printStackTrace();
                     }
              }
       }
      
      
       public void addParameter(String name, Class<?> type, String value){
              Atributo param = new Atributo();
              param.setName(name);
              param.setType(type);
              param.setValue(value);
              parameters.add(param);
       }
}
