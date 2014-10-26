package reservas.accesoDatos;

import static base.accesoDatos.utils.FluentMap.Map;
import static base.accesoDatos.utils.FluentMap.entry;

import java.util.List;

import javax.ejb.Stateless;

import reservas.modelo.Restaurante;
import base.accesoDatos.utils.GenericJpaDAO;

@Stateless
public class DaoRestaurante extends GenericJpaDAO<Restaurante, String>{
	public DaoRestaurante(){
		
	}
	
	public Restaurante buscarPorNit(Double nit){
		return executeSingleResultNamedQuery(
				"restaurante.buscarPorNit",
				Map( entry("nit", nit))
				);
	}
	
	
	public List <Restaurante> buscarPorNombre(String nombre){
		return executeListResultNamedQuery(
				"restaurante.buscarPorNombre",
				Map( 	entry("nombre", nombre )
						));
	}
	
	


}
