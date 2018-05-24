package com.altimetrik.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.altimetrik.dto.ErrorMessage;

/**
 * @author nmuthusamy
 *
 */
@ControllerAdvice
public class AltiException extends Exception {

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> error(Exception ex){
		
		ErrorMessage errorMessage = new ErrorMessage("Something went wrong", ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.OK);
		
	}
}
