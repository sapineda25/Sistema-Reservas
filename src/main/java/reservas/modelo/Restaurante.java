package reservas.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
	@NamedQuery( name="restaurante.buscarPorNit", query="select r from Restaurante r where r.nit = :nit" ),
	@NamedQuery( name="restaurante.buscarPorNombre", query="select r from Restaurante r where r.nombre like '%:nombre%'" ),
	@NamedQuery(name="restaurante.buscarPorEspecialidad", query="select r from Restaurante r where r.especialidad = :especialidad") 
	
})
public class Restaurante {
	@Id
	int nit;
	String nombre;
	String direccion;
	String telefono;
	String especialidad;
	String geocode;
	Boolean activo;
	
	
	@OneToMany(cascade={ CascadeType.ALL, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="restaurante" )
	List<Comentario> comentarios = new ArrayList<Comentario>();

	@OneToMany(cascade={ CascadeType.ALL, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="restaurante" )
	List<TipoMesa> mesas = new ArrayList<TipoMesa>();
	
	
	public Restaurante(){}


	public int getNit() {
		return nit;
	}


	public void setNit(int nit) {
		this.nit = nit;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}


	public String getGeocode() {
		return geocode;
	}


	public void setGeocode(String geocode) {
		this.geocode = geocode;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public List<Comentario> getComentarios() {
		return comentarios;
	}


	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}


	public List<TipoMesa> getMesas() {
		return mesas;
	}


	public void setMesas(List<TipoMesa> mesas) {
		this.mesas = mesas;
	}
	

}
