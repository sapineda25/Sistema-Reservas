package reservas.presentacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import reservas.accesoDatos.DaoUsuario;
import reservas.modelo.Usuario;



@ManagedBean
@RequestScoped
public class ListaUsuarios {
	
	// propiedades de los formularios
	List<Usuario> usuarios;
	
	//EJB
	@EJB
	DaoUsuario daoUsuario;
	
	public ListaUsuarios(){}
	
	// eventos
	
	public String refrescar() {
			usuarios = null;
			return "";
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null){
			usuarios = daoUsuario.findAll();
			System.out.println(usuarios);
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	

}
