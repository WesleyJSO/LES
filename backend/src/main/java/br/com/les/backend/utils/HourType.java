package br.com.les.backend.utils;

public enum HourType {

	OVERTIME("Hora Extra"), 
    BANKED_HOURS("Banco de Horas"), 
    OVERTIME_AND_BANKED_HOURS("Hora Extra e Banco de Horas");
	
	private String value;
	
	private HourType( String value ) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
