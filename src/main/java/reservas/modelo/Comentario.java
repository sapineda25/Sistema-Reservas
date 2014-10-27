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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comentario {
	
	@Id
	@GeneratedValue
	Long id;
	@Temporal( TemporalType.DATE )
	Date fecha;
	String texto;
	Boolean Activo;
	@Enumerated(EnumType.STRING)
	private Calificacion calificacion;
	
	
	@ManyToOne(cascade={CascadeType.ALL, CascadeType.REMOVE},fetch=FetchType.LAZY )
	@JoinColumn( name="usuario_id" )
	Usuario autor;
	
	
	@ManyToOne(cascade={CascadeType.ALL, CascadeType.REMOVE},fetch=FetchType.LAZY )
	@JoinColumn( name="restaurante_id" )
	Restaurante restaurante;
	
	public Comentario(){}
	

	public Boolean getActivo() {
		return Activo;
	}

	public void setActivo(Boolean activo) {
		Activo = activo;
	}





	public Calificacion getCalificacion() {
		return calificacion;
	}



	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
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

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	

}
