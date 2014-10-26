package reservas.modelo;

import javax.persistence.Entity;

@Entity
public enum TipoUsuario {
	Admin("Administrador","01"),
	User("Usuario Registrado", "02"),
	AdminRes("Administrador Restaurante", "03");
	
	private final String desc;
	private final String val;
	
	TipoUsuario(String descripcion, String valor){
		desc = descripcion;
		val = valor;
	}

	public String getDesc() {
		return desc;
	}

	public String getVal() {
		return val;
	}

}
