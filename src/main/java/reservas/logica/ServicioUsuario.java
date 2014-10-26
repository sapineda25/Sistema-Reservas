package reservas.logica;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import reservas.accesoDatos.DaoUsuario;
import reservas.modelo.TipoUsuario;
import reservas.modelo.Usuario;

//casos de uso relacionados con usuarios
@Stateless
public class ServicioUsuario {
	
	//EJB
	
	@EJB
	DaoUsuario daoUsuario;
	
	// Casos de uso
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void agregarUsuario(String correo, String codigoValidacion, String nombre,String apellido,String telefono, String password, TipoUsuario tipo, Boolean activo )throws Exception{
		
		//1.	Valida que no exista un usuario con ese login
		Usuario loginExistente = daoUsuario.buscarPorLogin(correo);
		if ( loginExistente != null ) {
			throw new Exception( "Ya existe un usuario con ese correo electronico");
		}
					
		if ( nombre == null 
				|| ( nombre != null && nombre.trim().equals(""))) {
			throw new Exception( "No se han ingresado todos los datos del usuario" );
		}		
		
		
		if ( apellido == null 
				|| ( nombre != null && apellido.trim().equals(""))) {
			throw new Exception( "No se han ingresado todos los datos del usuario" );
		}
		
		if ( telefono == null 
				|| ( nombre != null && telefono.trim().equals(""))) {
			throw new Exception( "No se han ingresado todos los datos del usuario" );
		}
		
		if ( password == null 
				|| ( nombre != null && nombre.trim().equals(""))) {
			throw new Exception( "No se han ingresado todos los datos del usuario" );
		}
		
		
		//9. Registra el nuevo usuario en el sistema
		
		Usuario usuario = new Usuario();
		usuario.setCorreo(correo);
		usuario.setNombre(nombre);
		usuario.setCodigoValidacion(codigoValidacion);
		usuario.setApellido(apellido);
		usuario.setTelefono(telefono);
		usuario.setPassword(password);
		usuario.setTipo(tipo);
		usuario.setActivo(activo);		
				
		daoUsuario.create(usuario);
				
		System.out.println( usuario );
		
	}
	
	public void inactivarUsuario(String login) throws Exception{
		// 2.	Valida que el usuario sea administrador de restaurante
		Usuario usuarioAdministrador = daoUsuario.ValidarUsuarioAdministrador(login);
		
		if ( usuarioAdministrador == null ) {
			throw new Exception( "El usuario no es administrador general");
		}
		
		Usuario UsuarioExistente = daoUsuario.buscarPorLogin(login);
		
		if (UsuarioExistente == null){
			throw new Exception( "El Usuario no existe");
		}
		
//		8.	Realiza la inactivación del usuario en el sistema.
	
		UsuarioExistente.setActivo(false);
        daoUsuario.update(UsuarioExistente);
		
		
		
	} 
	
	
	

}
