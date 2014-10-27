package reservas.logica;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import reservas.accesoDatos.DaoComentario;
import reservas.accesoDatos.DaoRestaurante;
import reservas.accesoDatos.DaoUsuario;
import reservas.modelo.Calificacion;
import reservas.modelo.Comentario;
import reservas.modelo.Restaurante;
import reservas.modelo.Usuario;


//casos de uso relacionados con usuarios
@Stateless
public class ServicioComentarios {
	@EJB
	DaoRestaurante daoRestaurante;
	@EJB
	DaoUsuario daoUsuario;
	@EJB
	DaoComentario daoComentario;
	
	// Crear Comentario
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearComentario(int nit, Usuario autor, Date fecha, String texto, Calificacion calificacion)throws Exception{
		
		Restaurante RestauranteExistente = daoRestaurante.buscarPorNit(nit);
		if (RestauranteExistente == null){
			throw new Exception( "no existe un restaurante con ese nit" );
		}
		
		//6.	Verifica que el usuario haya hecho por lo menos una reserva en este restaurante.
		 
		
		
		Comentario coment = new Comentario();
		coment.setAutor(autor);
		coment.setRestaurante(RestauranteExistente);
		coment.setFecha(fecha);
		coment.setTexto(texto);
		coment.setCalificacion(calificacion);
				
		daoComentario.create(coment);
		
		RestauranteExistente.getComentarios().add(coment);
		daoRestaurante.update(RestauranteExistente);
				
		
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void borrarComentario(String login, Long id)throws Exception {
		// 2.	Valida que el usuario sea administrador de restaurante
				Usuario usuarioAdministrador = daoUsuario.ValidarUsuarioAdministrador(login);
				
				if ( usuarioAdministrador == null ) {
					throw new Exception( "El usuario no es administrador general");
				}
				
				Comentario ComentarioSeleccionado = daoComentario.findById(id);
				
				daoComentario.delete(ComentarioSeleccionado);
						
				
	}
	
	

}
