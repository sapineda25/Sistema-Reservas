package base.utils;

import reservas.modelo.pruebas.DatosPrueba;
import cc.jpa.JPQLe;

public class EjecutarJPQLTerminal {

	public static void main(String[] args) {

		try {
			
			DatosPrueba  datosPrueba = new DatosPrueba();
			datosPrueba.borrarDatosPrueba();
			datosPrueba.crearDatosPrueba();
			
		} catch (Exception e) {
			System.out.println("No se pudo crear los datos de prueba : " + e.getMessage());
		}
		
		JPQLe.main( new String[]{"local_PU"} );
		
	}
	
}
