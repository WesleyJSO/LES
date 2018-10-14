package br.com.les.backend.utils;

public enum RequestType {
	
	CHANGE_APPOINTMENT(1),
	WORK_OVERTIME(2),
	COMP_TIME(3),
	REALOCATION_DAYS(4);
	
	private int value;
	
	private RequestType ( int value ) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
