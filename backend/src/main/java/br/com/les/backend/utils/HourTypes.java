package br.com.les.backend.utils;

public enum HourTypes {

	OVERTIME("Hora Extra"), 
    BANKED_HOURS("Banco de Horas"), 
    BOTH("Ambos");
	
	private String value;
	
	private HourTypes( String value ) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
