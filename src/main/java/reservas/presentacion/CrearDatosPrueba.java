package reservas.presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import reservas.modelo.pruebas.DatosPrueba;
import base.presentacion.utils.FacesUtils;

@ManagedBean
@RequestScoped
public class CrearDatosPrueba {

	@EJB
	DatosPrueba datosPrueba;
	
	public String crearDatosPrueba() {

		try {			
			datosPrueba.borrarDatosPrueba();
			datosPrueba.crearDatosPrueba();
			FacesUtils.addSuccessMessage("Datos de prueba creados !!");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage( e.getMessage() );
		}
		return "";
	}
	
}
