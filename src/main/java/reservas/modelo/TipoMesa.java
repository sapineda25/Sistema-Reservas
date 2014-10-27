package reservas.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery( name="tipo.buscarPorDescripcion", query="select t from TipoMesa t where t.descripcion = :descripcion" )
})

public class TipoMesa {
	
	@Id
	Long id;
	int capacidad;
	String descripcion;
	
	
		
	@OneToMany(cascade={ CascadeType.ALL, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="tipo" )
	List<Mesa> mesas = new ArrayList<Mesa>();
	
	@ManyToOne(cascade={CascadeType.ALL, CascadeType.REMOVE},fetch=FetchType.LAZY )
	@JoinColumn( name="restaurante_id" )
	Restaurante restaurante;
	
		
	public TipoMesa(){}	


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<Mesa> getMesas() {
		return mesas;
	}


	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}


	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
}
