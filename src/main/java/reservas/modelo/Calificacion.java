package reservas.modelo;

import javax.persistence.Entity;

@Entity
public enum Calificacion {
	
	UNO("Pesimo","01"),
	DOS("Malo","02"),
	TRES("Regular","03"),
	CUATRO("Bueno","04"),
	CINCO("Excelente","05");
	
	private final String desc;
	private final String val;
	
	Calificacion(String descripcion, String valor){
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