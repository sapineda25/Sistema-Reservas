package reservas.accesoDatos;


import static base.accesoDatos.utils.FluentMap.Map;
import static base.accesoDatos.utils.FluentMap.entry;

import javax.ejb.Stateless;
import reservas.modelo.TipoMesa;
import base.accesoDatos.utils.GenericJpaDAO;

@Stateless
public class DaoTipoMesa extends GenericJpaDAO<TipoMesa, String>{

	public DaoTipoMesa() {
	}
	
	// consultas
	public TipoMesa buscarPorTipo( String descripcion ) {		
		return executeSingleResultNamedQuery(
				"tipo.buscarPorDescripcion",
				Map( entry("descripcion", descripcion ))
				);		
	}
	
	
	
}
