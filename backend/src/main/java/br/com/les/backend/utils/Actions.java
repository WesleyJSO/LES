package br.com.les.backend.utils;

public enum Actions {
	
    SAVE("Save"), 
    UPDATE("Update"), 
    DELETE("Delete"), 
    SEARCH("Search"),
	FIND_ALL("FindAll"),
	SAVE_UPDATE("SaveUpdate"),
	FIND_BY_EMAIL_AND_PASSWORD("findByEmailAndPassword"),
	FIND_BY_ID("FindById"),
	NEW_PASSWORD("NewPassword");
	
	
	private String value;
	
	private Actions( String value ) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
