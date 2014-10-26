package reservas.accesoDatos;

import static base.accesoDatos.utils.FluentMap.Map;
import static base.accesoDatos.utils.FluentMap.entry;

import javax.ejb.Stateless;


import reservas.modelo.Usuario;
import base.accesoDatos.utils.GenericJpaDAO;

@Stateless
public class DaoUsuario extends GenericJpaDAO<Usuario, String>{

	public DaoUsuario() {
	}
	
	// consultas
	
	public Usuario buscarPorLogin(String login){
		return executeSingleResultNamedQuery(
				"usuario.buscarPorCorreo",
				Map( entry("login", login))
				);
	}
	
	
	public Usuario ValidarUsuarioAdministrador(String login){
		return executeSingleResultNamedQuery(
				"usuario.validarUsuarioAdministrador",
				Map( entry("login", login))
				);
	}
	
	
		
	
	
}
