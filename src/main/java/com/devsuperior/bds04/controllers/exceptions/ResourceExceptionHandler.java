package com.devsuperior.bds04.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validationException(MethodArgumentNotValidException e, HttpServletRequest req){
		ValidationError error = new ValidationError();
		error.setError("Field validation");
		error.setMessage(e.getLocalizedMessage());
		error.setPath(req.getServletPath());
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).body(error);
	}
}
