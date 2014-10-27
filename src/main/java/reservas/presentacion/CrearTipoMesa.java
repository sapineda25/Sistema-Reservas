package reservas.presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import base.presentacion.utils.FacesUtils;
import reservas.logica.ServicioTipoMesa;
import reservas.modelo.Restaurante;

@ManagedBean
@RequestScoped
public class CrearTipoMesa {

	// propiedades del formulario
	
	Long id;
	int capacidad;
	String descripcion;
	Restaurante restaurante;

	// EJBs
	
	@EJB
	ServicioTipoMesa servicioTipoMesa;
	

	public CrearTipoMesa() {
	}
	
	// eventos

	public String grabar() {
		
		try {
		
			// ejecuta el caso de uso
			servicioTipoMesa.agregarTipoMesa(id, capacidad, descripcion, restaurante);
						
			// muestra un mensaje de éxito
			FacesUtils.addSuccessMessage( "Estado creado !!");
			// va al index
			return "/index.xhtml?faces-redirect=true";
			
		// cuando ocurre un error 
		} catch (Exception e) {
			
			// muestra el mensaje de error
			FacesUtils.addErrorMessage( "Error creando estado : " + e.getMessage());
			// sigue en la misma página
			return "";
		}
		
	}
	
	// manejo de propiedades

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public ServicioTipoMesa getServicioTipoMesa() {
		return servicioTipoMesa;
	}

	public void setServicioTipoMesa(ServicioTipoMesa servicioTipoMesa) {
		this.servicioTipoMesa = servicioTipoMesa;
	}	

	
}
