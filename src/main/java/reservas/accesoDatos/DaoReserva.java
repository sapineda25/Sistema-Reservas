package reservas.accesoDatos;

import static base.accesoDatos.utils.FluentMap.Map;
import static base.accesoDatos.utils.FluentMap.entry;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import reservas.modelo.EstadoReserva;
import reservas.modelo.Reserva;
import base.accesoDatos.utils.GenericJpaDAO;

@Stateless
public class DaoReserva extends GenericJpaDAO<Reserva, Long>{

	public DaoReserva() {}
		
	
		
		
		public List<Reserva> buscarReservasPorUsuario(String login){
			return executeListResultNamedQuery("reserva.buscarReservasPorUsuario",
					Map(entry("login", login))
					);
		}
		
		public List<Reserva> buscarReservasPorFecha(Date fecha){
			return executeListResultNamedQuery("reserva.buscarReservasPorFecha",
					Map(entry("fecha",fecha))
					);
			
		}
		
		public List<Reserva> buscarReservasPorEstado(EstadoReserva estado){
			return executeListResultNamedQuery("reserva.buscarReservasPorEstado",
					Map(entry("estado",estado))
					);
			
		}
		
		public Reserva buscarUsuarioConReserva(String login){
			return executeSingleResultNamedQuery(
					"reserva.buscarUsuarioConReservas",
					Map( entry("login", login))
					);
		}
		
		
		
	}

