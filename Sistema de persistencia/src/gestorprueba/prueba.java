package gestorprueba;

import java.util.ArrayList;

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

		// A modo de testeo
		for (Atributo campo : campos)
		{
			System.out.println(campo.getName() + " (" + campo.getType() + " ) : " + campo.getValue());
		}
	}

}
