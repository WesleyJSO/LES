package br.com.les.backend.entity;

public enum HourType {
	
	COMPENSATION("Compensação"),
	EXTRATIME("Hora extra"),
	BOTH("Ambos");
	
	private String value;
	
	private HourType (String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}