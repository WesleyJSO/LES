package br.com.les.backend.entity;

public enum RequestStatus {
	
	SENT(1),
	APPROVED(2),
	DENIED(3);
	
	private Integer value;
	
	private RequestStatus (Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
}