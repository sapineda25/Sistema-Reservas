package reservas.presentacion;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import base.presentacion.utils.FacesUtils;
import reservas.logica.ServicioComentarios;
import reservas.modelo.Calificacion;
import reservas.modelo.Restaurante;
import reservas.modelo.Usuario;


@ManagedBean
@RequestScoped
public class CrearComentario {
	
	// propiedades del formulario
	
	int nit;
	Usuario autor;
	Long id;
	Date fecha;
	String texto;
	Calificacion calificacion;
	
	List<Restaurante> restaurantes;
	
	//EJB
	@EJB
	ServicioComentarios servicioComentarios;
	
	public CrearComentario(){}
	
	public String grabar(){
		
		try{
			// ejecuta el caso de uso 
			servicioComentarios.crearComentario(nit, autor, fecha, texto, calificacion);
			// muestra un mensaje de éxito
			FacesUtils.addSuccessMessage( "Solicitud creada !!");
			
			return "";
			
		}catch (Exception e) {
			
			// muestra el mensaje de error
			FacesUtils.addErrorMessage( "Error creando solicitud : " + e.getMessage());
			// sigue en la misma página
			return "";
		}
		
	}
		

}
