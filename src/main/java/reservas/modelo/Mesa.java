package reservas.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Mesa {
	
	@Id
	@GeneratedValue
	Long id;
	int Capacidad;
	Boolean Reservada;
	
		
	@OneToMany(cascade={ CascadeType.ALL, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="mesa" )
	List<Reserva> reservas = new ArrayList<Reserva>();
	
	@ManyToOne(cascade={CascadeType.ALL, CascadeType.REMOVE},fetch=FetchType.LAZY )
	@JoinColumn( name="restaurante_id" )
	Restaurante restaurante;
	
		
	public Mesa(){}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getCapacidad() {
		return Capacidad;
	}


	public void setCapacidad(int capacidad) {
		Capacidad = capacidad;
	}


	public Boolean getReservada() {
		return Reservada;
	}


	public void setReservada(Boolean reservada) {
		Reservada = reservada;
	}


	public List<Reserva> getReservas() {
		return reservas;
	}


	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}


	public Restaurante getRestaurante() {
		return restaurante;
	}


	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
		

}
