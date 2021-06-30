package com.mt.flightmanagement.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyExceptionHandlers {


	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> resourceNotFoundHandling(Exception exception, WebRequest request, Model model) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(),
				request.getDescription(false));
		model.addAttribute("error", errorDetails);
		return ResponseEntity.ok().body(errorDetails);
	}
	
	@ExceptionHandler(value = ApplicationException.class)
	public ResponseEntity<Object> exception(ApplicationException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException ex){
		ErrorDetails details = new ErrorDetails(new Date(),"Validation Error",
				ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST);
	}
}
