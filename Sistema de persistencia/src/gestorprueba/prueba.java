package gestorprueba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import persistencia.Atributo;
import persistencia.SistemaGestor;

public class prueba implements SistemaGestor {

	@Override
	public ArrayList<Atributo> load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ArrayList<Atributo> campos, int id) {

		System.out.println("\nGESTOR DE PRUEBA> Persistiendo objeto...");
		
		// A modo de testeo
		for (Atributo campo : campos)
		{
			System.out.println("GESTOR DE PRUEBA> " + campo.getName() + " (" + campo.getType() + " ) : " + campo.getValue());
		}
		
		System.out.println("GESTOR DE PRUEBA> Objeto persistido.");
	}

	@Override
	public void setConfigurations(HashMap<String, String> parameters) {
		System.out.println("\nGESTOR DE PRUEBA> Cargando configuraciones...");
		
		for(Entry<String, String> e : parameters.entrySet()) {
	        String key = e.getKey();
	        String value = e.getValue();
	        
	        System.out.println("GESTOR DE PRUEBA> " + key + " : " + value);
	    }
		
		System.out.println("GESTOR DE PRUEBA> Configuraciones cargadas con éxito.");
	}

}
