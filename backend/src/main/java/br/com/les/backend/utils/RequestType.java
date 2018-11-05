package br.com.les.backend.utils;

public class RequestType {
	public static final int WORK_OVERTIME = 1;
	public static final int COMP_TIME = 2;
	public static final int CHANGE_APPOINTMENT = 3;
	public static final int REALOCATE_DAY = 4;
	
	private int value;
	
	private RequestType (int value ) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
