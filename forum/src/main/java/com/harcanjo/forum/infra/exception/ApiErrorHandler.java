package com.harcanjo.forum.infra.exception;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.harcanjo.forum.domain.ValidationException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApiErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> handleError404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ValidationErrorDTO>> handleError400(MethodArgumentNotValidException ex) {
		var errors = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorDTO::new).toList());
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<String> handleErrorBusinessLogic(ValidationException ex) {		
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	private record ValidationErrorDTO(String field, String message) {
		public ValidationErrorDTO(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
}
