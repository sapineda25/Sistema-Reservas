package reservas.presentacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import reservas.accesoDatos.DaoRestaurante;
import reservas.modelo.Restaurante;


@ManagedBean
@RequestScoped
public class ListaRestaurantes {

	// propiedades de los formularios
	
	List<Restaurante>	restaurantes;
	
	// EJBs
	
	@EJB
	DaoRestaurante daoRestaurante;
	
	public ListaRestaurantes() {
	}

	// eventos
	
	public String refrescar() {
		restaurantes = null;
		return "";
	}
	
	// manejo de propiedades
	
	public List<Restaurante> getRestaurantes() {
		if ( restaurantes == null ) {
			restaurantes = daoRestaurante.findAll();
		}
		return restaurantes;
	}
	
	public void setEstados(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
	
	
}
