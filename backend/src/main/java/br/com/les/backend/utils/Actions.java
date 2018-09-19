package br.com.les.backend.utils;

public enum Actions {
	
    SAVE("Save"), 
    UPDATE("Update"), 
    DELETE("Delete"), 
    SEARCH("Search"),
	FIND_ALL("FindAll"),
	SAVE_UPDATE("SaveUpdate"),
	FIND_BY_USER("FindByUser"),
	FIND_BY_LOGIN("FindByLogin"),
	SENT_PASSWORD("SentChangePasswordEmail");
	
	
	private String value;
	
	private Actions( String value ) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
