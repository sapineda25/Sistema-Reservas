package reservas.presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import base.presentacion.utils.FacesUtils;
import reservas.logica.ServicioUsuario;
import reservas.modelo.TipoUsuario;


@ManagedBean
@RequestScoped
public class CrearUsuario {
	
	// propiedades del formulario
	
	String correo;
	String codigoValidacion;
	String nombre;
	String apellido;
	String telefono;	
	String password;
	TipoUsuario tipo;
	boolean activo; 
	
	//EJB
	
	@EJB
	ServicioUsuario servicioUsuario;
	
	public CrearUsuario(){}
	
	
	public String grabar(){
		
		try{
			// ejecuta el caso de uso 
			servicioUsuario.agregarUsuario(correo, codigoValidacion, nombre, apellido, telefono, password, tipo, activo);
			// muestra un mensaje de éxito
			FacesUtils.addSuccessMessage( "Usuario creado !!");
			// va al index
			return "";
			
		}catch (Exception e) {
			
			// muestra el mensaje de error
			FacesUtils.addErrorMessage( "Error creando usuario : " + e.getMessage());
			// sigue en la misma página
			return "";
		}
		
	}

	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getCodigoValidacion() {
		return codigoValidacion;
	}


	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}


	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}


	public TipoUsuario getTipo() {
		return tipo;
	}


	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
}
