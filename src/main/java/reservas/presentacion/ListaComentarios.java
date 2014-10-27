package reservas.presentacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import reservas.accesoDatos.DaoComentario;
import reservas.modelo.Comentario;


@ManagedBean
@RequestScoped
public class ListaComentarios {

	// propiedades de los formularios
	
	List<Comentario> coments	;
	
	// EJBs
	
	@EJB
	DaoComentario daoComentario;
	
	public ListaComentarios() {
	}

	// eventos
	
	public String refrescar() {
		coments = null;
		return "";
	}
	
	// manejo de propiedades
	
	public List<Comentario> getRestaurantes() {
		if ( coments == null ) {
			coments = daoComentario.findAll();
		}
		return coments;
	}
	
	public void setEstados(List<Comentario> coments) {
		this.coments = coments;
	}
	
	
}
