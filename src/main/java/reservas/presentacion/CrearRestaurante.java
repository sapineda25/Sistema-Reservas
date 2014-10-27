package reservas.presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import base.presentacion.utils.FacesUtils;
import reservas.logica.ServicioRestaurante;


@ManagedBean
@RequestScoped
public class CrearRestaurante {
	
	// propiedades del formulario
	
	int nit;
	String nombre;
	String direccion;
	String telefono;
	String especialidad;
	String geocode;
	boolean activo;
	
	//EJBs
	
	@EJB	
	ServicioRestaurante servicioRestaurante;
	
	public CrearRestaurante(){}
	
	public String grabar(){
		try {
			//ejecuta el caso de uso
			servicioRestaurante.crearRestaurante(nit, nombre, direccion, telefono, especialidad, geocode, activo);
			// muestra un mensaje de éxito
						FacesUtils.addSuccessMessage( "Usuario creado !!");
						// va al index
						return "";			
		}catch (Exception e) {
			
			// muestra el mensaje de error
			FacesUtils.addErrorMessage( "Error creando usuario : " + e.getMessage());
			// sigue en la misma página
			return "";
		}
	}
	

}
