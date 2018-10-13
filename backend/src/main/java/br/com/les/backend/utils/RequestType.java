package br.com.les.backend.utils;

public enum RequestType {
	
	APPOINTMENT(1),
	ABSENSE_WITH_PERMISSION(2),
	WORK_OVERTIME(3),
	COMP_TIME(4),
	REALOCATION_DAYS(5);
	
	private int value;
	
	private RequestType ( int value ) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
