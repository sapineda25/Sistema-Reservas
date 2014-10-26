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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@NamedQueries({
	@NamedQuery( name="usuario.buscarPorLogin", query="select u from Usuario u where u.login = :login" ),
	@NamedQuery( name="usuario.validarUsuarioAdministrador", query="select u from Usuario u where u.login = :login and u.tipo = 'Admin' " )
})

public class Usuario {
		
	@Id
	String login;
	String codigoValidacion;
	String nombre;
	String apellido;
	String telefono;
	String correo;
	String password;
	Boolean activo;
	// atributo del tipo enum EstadoReserva
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	@OneToMany(cascade={ CascadeType.ALL, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="usuario" )
	List<Reserva> reservas = new ArrayList<Reserva>();
		
	@OneToMany(cascade={ CascadeType.ALL, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="autor" )
	List<Comentario> comentarios = new ArrayList<Comentario>();
	
	public Usuario(){}
	
	
	

	public Boolean getActivo() {
		return activo;
	}




	public void setActivo(Boolean activo) {
		this.activo = activo;
	}




	public String getLogin() {
		return login;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCodigoValidacion() {
		return codigoValidacion;
	}

	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
}
