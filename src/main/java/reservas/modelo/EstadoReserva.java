package reservas.modelo;

import javax.persistence.Entity;


//No estoy muy seguro de que esta clase tambien lleve el @
@Entity
public enum EstadoReserva {
	created("CREADA","01"),
	completed("CUMPLIDA","02"),
	incomplete("INCUMPLIDA","03"),
	canceled("CANCELADA", "04");
	
	private final String desc;
	private final String val;
	
	EstadoReserva(String descripcion, String valor){
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
