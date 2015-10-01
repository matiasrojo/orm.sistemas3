package persistencia;

import java.util.Map;

/**
 * Factoría simple para crear instancias de objetos
 * EN: simple factory to create Object instances
 */
public class InstanceCreator {
 
      
       /** Factoría abstracta genérica.  Crea instancias de clases a partir
        * de su numbre. La clase debe tener contructor sin parámetros.*/
       public Object getNewInstance(String type){
              try {
                      Class<?> clase = Class.forName(type);
                      return clase.newInstance();
             
              } catch (ClassNotFoundException e) {
                      System.out.println("Error de instanciación, clase no encontrada:" + type);
                      e.printStackTrace();
              } catch (InstantiationException e) {
                      System.out.println("Error de instanciación:" + type);
                      e.printStackTrace();
              } catch (IllegalAccessException e) {
                      System.out.println("Error de instanciación, acceso ilegal:" + type);
                      e.printStackTrace();
              }
              return null;
       }
      
       //Crea todas las instancias definidas en InstanceConfigurations, usando
       //una factoría abstracta simple, y añadiéndolos a la lista de instancias Instances
       //EN: creates the instances defined in the instanceConfiguration list and
       // adds each instance to the map intances.
       public void createInstances(Map<String, InstanceConfiguration> instanceConfigurations,
                      Map<String, Object> instances){
              System.out.println("###InstanceCreator.createInstance");
              for (Map.Entry<String, InstanceConfiguration> entry: instanceConfigurations.entrySet()){
                      InstanceConfiguration ic = entry.getValue();
                      instances.put(ic.getName(), getNewInstance(ic.getType()));
                      System.out.println("New instance: " + ic.getName() + ":"+ ic.getType());
              }
       }
}