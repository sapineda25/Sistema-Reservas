package reservas.logica;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import reservas.accesoDatos.DaoMesa;

import reservas.accesoDatos.DaoTipoMesa;
import reservas.modelo.Mesa;

import reservas.modelo.TipoMesa;


//casos de uso relacionados con usuarios
@Stateless
public class ServicioMesa {
	
	//EJB
	
	@EJB
	DaoMesa daoMesa;
	DaoTipoMesa daoTipoMesa;
	
	
	// Casos de uso
	
	public ServicioMesa(){}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void agregarMesa(Boolean disponible, String descripcion )throws Exception{
		

		TipoMesa tipo = daoTipoMesa.buscarPorTipo(descripcion);
	
		if (tipo == null){
			throw new Exception( "este tipo de mesa no existe" );
		}
		
		Mesa mesa = new Mesa();
		mesa.setTipo(tipo);
		mesa.setDisponible(disponible);
			
		daoMesa.create(mesa);
		
		tipo.getMesas().add(mesa);
		daoTipoMesa.update(tipo);
		
	}
	

}
