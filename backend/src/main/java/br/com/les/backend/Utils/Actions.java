package br.com.les.backend.Utils;

public enum Actions {
	
    SAVE("Save"), 
    UPDATE("Update"), 
    DELETE("Delete"), 
    SEARCH("Search");
	
	private String value;
	
	private Actions( String value ) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
