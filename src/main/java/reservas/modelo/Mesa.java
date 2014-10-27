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
	Boolean disponible;
	
	
		
	@OneToMany(cascade={ CascadeType.ALL, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="mesa" )
	List<Reserva> reservas = new ArrayList<Reserva>();
	
	@ManyToOne(cascade={CascadeType.ALL, CascadeType.REMOVE},fetch=FetchType.LAZY )
	@JoinColumn( name="tipo_id" )
	TipoMesa tipo;	
	
	
		
	public Mesa(){}
	
	
	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	

	public TipoMesa getTipo() {
		return tipo;
	}

	public void setTipo(TipoMesa tipo) {
		this.tipo = tipo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}


	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}	
	
}
