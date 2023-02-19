package com.word.collection.handler.ex;

import java.util.Map;

import lombok.Getter;

public class CustomUserCheckApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Map<String, String> errorMap;
	
	public CustomUserCheckApiException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
	
	public CustomUserCheckApiException(String message) {
		super(message);
	}
	

}
