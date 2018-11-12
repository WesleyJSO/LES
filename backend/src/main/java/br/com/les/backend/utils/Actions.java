package br.com.les.backend.utils;

public enum Actions {
	
    SAVE("Save"), 
    UPDATE("Update"), 
    DELETE("Delete"), 
	FIND("Find"),
	GET("Get"),
	NEW_PASSWORD("NewPassword"); 
	
	
	private String value;
	
	private Actions( String value ) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
