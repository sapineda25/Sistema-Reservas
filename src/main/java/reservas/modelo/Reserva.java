package reservas.modelo;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery( name="reserva.buscarReservasPorUsuario", query="select r from Reserva r join r.usuario user where user.login =:login" ),
	@NamedQuery( name="reserva.buscarReservasPorFecha", query="select r from Reserva r where r.fecha =:fecha" ),
	@NamedQuery( name="reserva.buscarReservasPorEstado", query="select r from Reserva r where r.estado =:estado" ),
	//@NamedQuery( name="reserva.numeroReservasDeUsuario", query="select count(p) from Reserva r where r.usuario.login = :login and p.estado = 'completed' ")
	@NamedQuery( name="reserva.buscarUsuarioConReservas", query="select r from Reserva r where r.usuario.login = :login and r.estado = 'completed'" ),
	//@NamedQuery( name="reserva.buscarReservasPorUsuario", query="select r from Reserva r join r.mesa and r.usuario where user.login =:login" ),
})
public class Reserva {
	
	
	@Id
	@GeneratedValue
	Long id;
	@Temporal( TemporalType.DATE )
	Date fecha;
	String texto;
	
	// atributo del tipo enum EstadoReserva
	@Enumerated(EnumType.STRING)
	private EstadoReserva estado;
	
	@ManyToOne(cascade={CascadeType.ALL, CascadeType.REMOVE},fetch=FetchType.LAZY )
	@JoinColumn( name="usuario_id" )
	Usuario usuario;
	
	@ManyToOne(cascade={CascadeType.ALL, CascadeType.REMOVE},fetch=FetchType.LAZY )
	@JoinColumn( name="mesa_id" )
	Mesa mesa;
	
	public Reserva(){}
	
	

	public EstadoReserva getEstado() {
		return estado;
	}



	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	
}