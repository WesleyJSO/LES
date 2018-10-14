package br.com.les.backend.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMessage {

	private Boolean hasError;
	private String message;
	
	public ResponseMessage(Boolean hasError, String message) {
		this.hasError = hasError;
		this.message = message;
	}
	
	public Boolean getHasError() {
		return hasError;
	}

	public String getMessage() {
		return message;
	}
	
	public static ResponseMessage create(boolean hasError, String message) {
		return new ResponseMessage(hasError, message);
	}
	
	public static ResponseEntity<?> create(HttpStatus status, String message) {
		ResponseMessage responseMessage = new ResponseMessage(status.value() >= 400, message);
		return ResponseEntity.status(status).body(responseMessage);
	}
	
	public static ResponseEntity<?> serverError(List<String> list) {
		ResponseMessage responseMessage = new ResponseMessage(true, list.toArray().toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	}
	
	public static ResponseEntity<?> serverError(String message) {
		ResponseMessage responseMessage = new ResponseMessage(true, message);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	}
}

