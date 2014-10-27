package reservas.modelo.pruebas;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import reservas.modelo.*;

@Stateless
@TransactionManagement( TransactionManagementType.BEAN )
public class DatosPrueba {


	// --

	@PersistenceContext( unitName="app_PU" )
	EntityManager 	manager;
	
	@Resource
	UserTransaction tx;
	
	EntityTransaction etx;
	
	public void initDB() {
		if ( manager == null ) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("local_PU");
			manager = factory.createEntityManager();
			etx = manager.getTransaction();
		}
	}
	
	private void beginTx() throws Exception {
		if ( tx != null ){
			tx.begin();
		} else {
			etx.begin();
		}
	}
	
	private void commitTx() throws Exception {
		if ( tx != null ){
			tx.commit();
		} else {
			etx.commit();
		}		
	}
	
	// --

	public void borrarDatosPrueba() throws Exception {

		initDB();

		beginTx();
		manager.createQuery( "DELETE FROM Reserva" ).executeUpdate();
		commitTx();
		
		beginTx();		
		manager.createQuery( "DELETE FROM Mesa" ).executeUpdate();
		commitTx();
		
		beginTx();		
		manager.createQuery( "DELETE FROM Comentario" ).executeUpdate();
		commitTx();
		
		beginTx();
		manager.createQuery( "DELETE FROM Usuario" ).executeUpdate();
		commitTx();
		
		beginTx();
		manager.createQuery( "DELETE FROM Restaurante" ).executeUpdate();
		commitTx();
		
	}

	public void crearUsuario( String login, String nombre, String apellido, String telefono, String password, TipoUsuario tipo, Boolean activo) throws Exception {
		
		initDB();		
		beginTx();
				
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setTelefono(telefono);
		usuario.setPassword(password);
		usuario.setTipo(tipo);
		usuario.setActivo(activo);
		
		manager.persist(usuario);
		
		commitTx();
		
	}
	 public void crearTipoMesa(Long id, int capacidad, int nit, String descripcion)throws Exception{
		 
		initDB();		
		beginTx();
		
		Restaurante restaurante = manager.find(Restaurante.class, id);
		
		TipoMesa tipo = new TipoMesa();
		tipo.setId(id);
		tipo.setCapacidad(capacidad);
		tipo.setDescripcion(descripcion);
		tipo.setRestaurante(restaurante);
		restaurante.getMesas().add(tipo);
		
		manager.persist(tipo);
		manager.persist(restaurante);
		
		commitTx();
		
		
	 }
	 
	 public void crearMesa(Long id, Boolean disponible)throws Exception{
		initDB();		
		beginTx();
		
		TipoMesa tipo = manager.find(TipoMesa.class, id);
		Mesa mesa = new Mesa();
		mesa.setDisponible(disponible);
		mesa.setTipo(tipo);
		tipo.getMesas().add(mesa);
		
		
		
	 }
	
	
	
	
	public void crearRestaurante(int nit, String nombre, String direccion, String telefono, String especialidad, String geocode, Boolean activo)throws Exception{
		
		initDB();		
		beginTx();
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNit(nit);
		restaurante.setNombre(nombre);
		restaurante.setDireccion(direccion);
		restaurante.setTelefono(telefono);
		restaurante.setEspecialidad(especialidad);
		restaurante.setGeocode(geocode);
		restaurante.setActivo(activo);
		
		manager.persist(restaurante);
		
		commitTx();
		
		
	}
	
	public void  CrearComentario(String login, int nit, Date fecha, String texto, Calificacion calificacion, Boolean activo)throws Exception {
		
		initDB();		
		beginTx();
		
		Usuario autor = manager.find( Usuario.class, login );
		Restaurante restaurante = manager.find(Restaurante.class, nit);
		
		Comentario coment = new Comentario();
		coment.setAutor(autor);
		autor.getComentarios().add(coment);
		
		
		coment.setRestaurante(restaurante);
		restaurante.getComentarios().add(coment);
		//fecha = new Date();
		coment.setFecha(fecha);
		coment.setTexto(texto);
		coment.setCalificacion(calificacion);
		coment.setActivo(activo);
		
		manager.persist(coment);
		manager.persist(autor);
		manager.persist(restaurante);
		
		commitTx();
		
		
	}
	
	
	
	
		
//	public void crearSolicitud( String login, Long id, String descripcion, Long idEstado, Date fecha ) throws Exception {
//		
//		initDB();		
//		beginTx();
//					
//		Usuarioold usuario = manager.find( Usuarioold.class, login );
//		Estado estado = manager.find( Estado.class, idEstado );
//
//		Solicitud solicitud = new Solicitud();
//		solicitud.setId(id);
//		solicitud.setAsunto(descripcion);
//		solicitud.setDescripcion(descripcion);
//		solicitud.setFecha(fecha);
//		
//		solicitud.setSolicitante(usuario);
//		usuario.getSolicitudes().add(solicitud);
//		
//		solicitud.setEstado(estado);
//		estado.getSolicitudes().add(solicitud);
//		
//		manager.persist( usuario );
//		manager.persist( solicitud );
//		manager.persist( estado );
//		
//		commitTx();
//	}
	
//	public void crearAtencion( Long id,  String login, String descripcion, Date fecha ) throws Exception {
//
//		initDB();		
//		beginTx();
//			
//		Usuarioold usuario = manager.find( Usuarioold.class, login );
//		Solicitud solicitud = manager.find( Solicitud.class, id );
//
//		Atencion atencion = new Atencion();
//		atencion.setSolicitud(solicitud);
//		solicitud.getAtenciones().add(atencion);
//		
//		atencion.setFuncionario(usuario);
//		usuario.getAtenciones().add(atencion);
//
//		manager.persist( usuario );
//		manager.persist( solicitud );
//		manager.persist( atencion );
//		
//		commitTx();
//	}

	
	// --

	
	public  void crearDatosPrueba() throws Exception {

		initDB();
		
		
		crearUsuario("silvanopineda@gmail.com", "Silvano", "Pineda", "4302688", "Root", reservas.modelo.TipoUsuario.Admin, true);
		crearRestaurante(1014205572, "La Fragata", "Calle 93B- NO 11-26", "4330000", "Carnes", "-34.397, 150.644", true);
		crearTipoMesa(01L,01, 1014205572, "Mesa para uno");
		crearTipoMesa(02L,02, 1014205572, "Mesa para dos");
		crearTipoMesa(03L,03, 1014205572, "Mesa para Cuatro");
		crearTipoMesa(04L,04, 1014205572, "Mesa para Seis");
		crearMesa(01L, true);
		crearMesa(01L, true);
		crearMesa(01L, true);
		crearMesa(02L, true);
		crearMesa(02L, true);
		crearMesa(02L, true);
		crearMesa(03L, true);
		crearMesa(03L, true);
		crearMesa(03L, true);
		

		CrearComentario("silvanopineda@gmail.com", 1014205572, new Date(), "El servicio es Excelente", reservas.modelo.Calificacion.CUATRO, true);
		
		
		
	}	
	
}
