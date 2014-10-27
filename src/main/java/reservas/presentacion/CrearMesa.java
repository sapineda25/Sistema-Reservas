package reservas.presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import base.presentacion.utils.FacesUtils;
import reservas.logica.ServicioMesa;


@ManagedBean
@RequestScoped
public class CrearMesa {
	
	// propiedades del formulario
	
	
	String descripcion;
	boolean disponible;
	 
	
	//EJB
	
	@EJB
	ServicioMesa servicioMesa;
	
	public CrearMesa(){}
	
	
	public String grabar(){
		
		try{
			// ejecuta el caso de uso 
			servicioMesa.agregarMesa(disponible, descripcion);
			// muestra un mensaje de éxito
			FacesUtils.addSuccessMessage( "Mesa creada !!");
			// va al index
			return "";
			
		}catch (Exception e) {
			
			// muestra el mensaje de error
			FacesUtils.addErrorMessage( "Error creando Mesa : " + e.getMessage());
			// sigue en la misma página
			return "";
		}
		
	}
	

	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean isDisponible() {
		return disponible;
	}


	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	public ServicioMesa getServicioMesa() {
		return servicioMesa;
	}


	public void setServicioMesa(ServicioMesa servicioMesa) {
		this.servicioMesa = servicioMesa;
	}	
		
	
}
