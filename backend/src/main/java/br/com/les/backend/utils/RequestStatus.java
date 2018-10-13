package br.com.les.backend.utils;

public enum RequestStatus {
	
	SENT("Enviada"),
	APPROVED("Aprovada"),
	DENIED("Rejeitada");
	
	private String value;
	
	private RequestStatus (String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
