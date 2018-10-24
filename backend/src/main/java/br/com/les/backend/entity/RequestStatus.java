package br.com.les.backend.entity;

public enum RequestStatus {
	
	SENT(1),
	APPROVED(2),
	DENIED(3);
	
	private int value;
	
	private RequestStatus (int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}