package reservas.accesoDatos;

import javax.ejb.Stateless;


import reservas.modelo.Comentario;
import base.accesoDatos.utils.GenericJpaDAO;

@Stateless
public class DaoComentario extends GenericJpaDAO<Comentario, Long>{

	public DaoComentario() {
	}
	
}
