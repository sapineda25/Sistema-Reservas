package reservas.logica;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import reservas.accesoDatos.DaoReserva;
import reservas.accesoDatos.DaoUsuario;
import reservas.modelo.Reserva;
import reservas.modelo.Usuario;
import reservas.modelo.EstadoReserva;


@Stateless
public class ServicioReserva {
	
	
	//EJB
	@EJB
	DaoReserva daoReserva;
	@EJB
	DaoUsuario daoUsuario;
	
	
	// Casos de uso
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void agregarReserva(String login, String asunto, String descripcion, Long estado)throws Exception{
		
//		//3.	Valida que no exista otra solicitud con ese número
//		Solicitud solicitudExistente = daoSolitud.findById(id);
//		
//		if ( solicitudExistente != null ) {
//			throw new Exception( "ya existe otra solicitud con el mismo id");
//		}
//		
//		
//		//5.	Valida que exista un usuario con ese login
//		Usuarioold usuarioExistente = daoUsuario.buscarPorLogin(login);
//
//		if ( usuarioExistente == null ) {
//			throw new Exception( "No existe un usuario con ese login ");
//		}
//		
//		//7.	Verifica que el usuario no tenga otra solicitud con el mismo texto del asunto
//		
//		Solicitud asuntoExistente = daoSolitud.buscarPorAsunto(login, asunto);
//		if (asuntoExistente != null ){
//			throw new Exception( "Usuario ya tiene otra solicitud con el mismo asunto");
//		}
//		
//		//9.	Obtiene la fecha actual del sistema
//		
//		Date fecha = new Date(); 
//		
//		//11.	Verifica que exista un estado con ese id
//		
//		Estado estadoExistente = daoEstado.findById(estado);
//		if ( estadoExistente == null ) {
//			throw new Exception( "No existe un estado con ese id ");
//		}
//		
//		
//		Solicitud solicitud = new Solicitud();
//		solicitud.setId(id);
//		solicitud.setSolicitante(usuarioExistente);
//		solicitud.setFecha(fecha);
//		solicitud.setAsunto(asunto);
//		solicitud.setDescripcion(descripcion);
//		solicitud.setEstado(estadoExistente);
//		
//		daoSolitud.create(solicitud);
		
				
	}
	
	public List<Reserva>  consultarReservasUsuario(String login) throws Exception{
		
		// 3.	Verifica que exista un usuario con ese login
		Usuario loginExistente = daoUsuario.buscarPorLogin(login);
		if ( loginExistente == null ) {
			throw new Exception( "“No existe un usuario con ese login");
		}
		
		//4.	Obtiene las Reservas realizadas por ese usuario
		return daoReserva.buscarReservasPorUsuario(login);
			
						
	}
	
	
	
	public List<Reserva> consultarReservasFecha (Date fecha)throws Exception{
		
//			Obtiene las reservas para esta fecha
		
		return daoReserva.buscarReservasPorFecha(fecha);
	}
	

	public List<Reserva> consultarReservasEstado (EstadoReserva estado )throws Exception{
		
//			Obtiene las solicitudes que se encuentran en ese estado
		
		return daoReserva.buscarReservasPorEstado(estado);
	}
	
	
	
	
	
	
	

}
