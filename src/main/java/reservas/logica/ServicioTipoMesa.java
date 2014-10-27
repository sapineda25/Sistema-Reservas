package reservas.logica;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;





import reservas.accesoDatos.DaoMesa;
import reservas.accesoDatos.DaoRestaurante;
import reservas.accesoDatos.DaoTipoMesa;
import reservas.modelo.Restaurante;
import reservas.modelo.TipoMesa;


//casos de uso relacionados con usuarios
@Stateless
public class ServicioTipoMesa {
	
	//EJB
	
	@EJB
	DaoMesa daoMesa;
	DaoTipoMesa daoTipoMesa;
	DaoRestaurante daoRestaurante;
	
	
	// Casos de uso
	
	public ServicioTipoMesa(){}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void agregarTipoMesa(Long id, int capacidad, String descripcion,Restaurante restaurante)throws Exception{
		

		TipoMesa tipo = daoTipoMesa.buscarPorTipo(descripcion);
		
	 
		if (tipo != null){
			throw new Exception( "este tipo de mesa ya existe" );
		}
		
		tipo.setId(id);
		tipo.setCapacidad(capacidad);
		tipo.setDescripcion(descripcion);
		tipo.setRestaurante(restaurante);
		restaurante.getMesas().add(tipo);
			
		daoTipoMesa.create(tipo);
		daoRestaurante.update(restaurante);
		
	}
	

}
