package reservas.logica;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import reservas.accesoDatos.DaoRestaurante;
import reservas.accesoDatos.DaoUsuario;
import reservas.modelo.Restaurante;
import reservas.modelo.Usuario;

//casos de uso relacionados con restaurantes
@Stateless
public class ServicioRestaurante {
	//EJB
	@EJB
	DaoRestaurante daoRestaurante;
	@EJB
	DaoUsuario daoUsuario;
	
	//casos de uso
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearRestaurante(int nit, String nombre, String direccion, String telefono,	String especialidad, String geocode,Boolean activo)throws Exception{
		
		//3. Valida que no exista un Restaurante con ese NIT.
		Restaurante nitExistente = daoRestaurante.buscarPorNit(nit);
		if ( nitExistente != null ) {
			throw new Exception( "Ya existe un restaurante con ese Nit");
		}
		// Verifica que se hayan ingresado completos loss datos del restaurante
		if ( nombre == null 
				|| ( nombre != null && nombre.trim().equals(""))) {
			throw new Exception( "No se han ingresado todos los datos del usuario" );
		}
		
		if ( direccion == null 
				|| ( direccion != null && direccion.trim().equals(""))) {
			throw new Exception( "No se han ingresado todos los datos del usuario" );
		}
		
		if ( telefono == null 
				|| ( telefono != null && telefono.trim().equals(""))) {
			throw new Exception( "No se han ingresado todos los datos del usuario" );
		}
		
		if ( especialidad == null 
				|| ( especialidad != null && especialidad.trim().equals(""))) {
			throw new Exception( "No se han ingresado todos los datos del usuario" );
		}
		
		if ( geocode == null 
				|| ( geocode != null && geocode.trim().equals(""))) {
			throw new Exception( "No se han ingresado todos los datos del usuario" );
		}
		
		//9.	Registra el nuevo restaurante en el sistema
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNit(nit);
		restaurante.setNombre(nombre);
		restaurante.setDireccion(direccion);
		restaurante.setTelefono(telefono);
		restaurante.setEspecialidad(especialidad);
		restaurante.setGeocode(geocode);
		restaurante.setActivo(activo);
				
		daoRestaurante.create(restaurante);
	}
	
	public List<Restaurante> traerTodosLosRestaurante (){
		//1.	Obtiene la  lista de todos los restaurantes registrados en la aplicación.
		return daoRestaurante.findAll();
	}
	
	// 3.	Obtiene la  lista de todos los restaurantes registrados en la aplicación.
	
	public List<Restaurante> buscarPorNombre(String nombre){
		return daoRestaurante.buscarPorNombre(nombre);
	}
	
	
	public void inactivarRestaurante(String login, int nit) throws Exception{
		// 2.	Valida que el usuario sea administrador de restaurante
		Usuario usuarioAdministrador = daoUsuario.ValidarUsuarioAdministrador(login);
		
		if ( usuarioAdministrador == null ) {
			throw new Exception( "El usuario no es administrador general");
		}
		
		Restaurante restauranteExistente = daoRestaurante.buscarPorNit(nit);
		
		if (restauranteExistente == null){
			throw new Exception( "El Restaurante no existe");
		}
		
//		8.	Realiza la inactivación del restaurante en el sistema.
	
        restauranteExistente.setActivo(false);
        daoRestaurante.update(restauranteExistente);
			
	} 
	// Obtiene la  lista de todos los restaurantes registrados en la aplicación que tienen la especialidad seleccionada.
	public List<Restaurante> buscarPorEspecialidad(String especialidad){
		return daoRestaurante.buscarPorEspecialidad(especialidad);
	} 
	
	

}
