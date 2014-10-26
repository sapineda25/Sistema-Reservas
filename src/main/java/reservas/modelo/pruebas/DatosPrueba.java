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
		manager.createQuery( "DELETE FROM Atencion" ).executeUpdate();
		commitTx();
		
		beginTx();		
		manager.createQuery( "DELETE FROM Solicitud" ).executeUpdate();
		commitTx();
		
		beginTx();
		manager.createQuery( "DELETE FROM Usuario" ).executeUpdate();
		commitTx();
		
		beginTx();
		manager.createQuery( "DELETE FROM Estado" ).executeUpdate();
		commitTx();
		
	}

	public void crearUsuario( String login, String password, String nombre, boolean administrador, boolean funcionario) throws Exception {
		
		initDB();		
		beginTx();
				
		Usuarioold usuario = new Usuarioold();
		usuario.setLogin(login);
		usuario.setPassword(password);
		usuario.setNombre(nombre);
		usuario.setAdministrador(administrador);
		usuario.setFuncionario(funcionario);
		
		manager.persist(usuario);
		
		commitTx();
		
	}
	
	public void crearEstado( Long id, String nombre, String descripcion ) throws Exception {

		initDB();
		beginTx();
					
		Estado estado = new Estado();
		estado.setId(id);
		estado.setNombre(nombre);
		estado.setDescripcion(descripcion);
		
		manager.persist(estado);
		
		commitTx();
		
	}
	
	public void crearSolicitud( String login, Long id, String descripcion, Long idEstado, Date fecha ) throws Exception {
		
		initDB();		
		beginTx();
					
		Usuarioold usuario = manager.find( Usuarioold.class, login );
		Estado estado = manager.find( Estado.class, idEstado );

		Solicitud solicitud = new Solicitud();
		solicitud.setId(id);
		solicitud.setAsunto(descripcion);
		solicitud.setDescripcion(descripcion);
		solicitud.setFecha(fecha);
		
		solicitud.setSolicitante(usuario);
		usuario.getSolicitudes().add(solicitud);
		
		solicitud.setEstado(estado);
		estado.getSolicitudes().add(solicitud);
		
		manager.persist( usuario );
		manager.persist( solicitud );
		manager.persist( estado );
		
		commitTx();
	}
	
	public void crearAtencion( Long id,  String login, String descripcion, Date fecha ) throws Exception {

		initDB();		
		beginTx();
			
		Usuarioold usuario = manager.find( Usuarioold.class, login );
		Solicitud solicitud = manager.find( Solicitud.class, id );

		Atencion atencion = new Atencion();
		atencion.setSolicitud(solicitud);
		solicitud.getAtenciones().add(atencion);
		
		atencion.setFuncionario(usuario);
		usuario.getAtenciones().add(atencion);

		manager.persist( usuario );
		manager.persist( solicitud );
		manager.persist( atencion );
		
		commitTx();
	}

	
	// --

	
	public  void crearDatosPrueba() throws Exception {

		initDB();
		
		crearUsuario( "uno", "uno", "uno", false, false);
		crearUsuario( "dos", "dos", "dos", false, false);
		crearUsuario( "tres", "tres", "tres", false, false);
		
		crearUsuario( "atencion1", "uno", "atencion uno", false, true);
		crearUsuario( "atencion2", "dos", "atencion dos", false, true);
		
		crearUsuario( "admin", "admin", "administrador", true, true);

		crearEstado( 1L, 	"abierto", 	"abierto" );
		crearEstado( 2L, 	"espera", 	"espera" );
		crearEstado( 3L, 	"atendido", "atendido" );
		crearEstado( 4L, 	"cerrado", 	"cerrado" );
		
		crearSolicitud("uno", 101L, "ayuda con la impresora", 4L, new Date());
		crearAtencion( 101L, "atencion1", "hay que prender la impresora", new Date());
		crearAtencion( 101L, "uno", "gracias", new Date());

		crearSolicitud("uno", 102L, "ayuda con el monitor", 4L, new Date());
		crearAtencion( 102L, "atencion2", "hay que prender el monitor", new Date());
		crearAtencion( 102L, "uno", "gracias", new Date());

		crearSolicitud("dos", 103L, "no funciona la base de datos", 2L, new Date());
		crearAtencion( 103L, "atencion1", "cual base de datos", new Date());

		crearSolicitud("tres", 104L, "no funciona la base de datos", 1L, new Date());

		crearSolicitud("dos", 105L, "necesito instalar office", 2L, new Date());
		crearAtencion( 105L, "atencion1", "tiene una licencia ?", new Date());
		crearAtencion( 105L, "dos", "no tengo", new Date());
		crearAtencion( 105L, "atencion1", "no puedo instalarlo", new Date());
		
		
	}	
	
}
