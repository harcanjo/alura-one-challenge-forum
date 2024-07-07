package com.harcanjo.forum.domain;

public class ValidationException extends RuntimeException {
	
	public ValidationException(String message) {
		super(message);
	}
}
