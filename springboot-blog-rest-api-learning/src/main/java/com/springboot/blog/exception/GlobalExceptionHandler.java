package com.springboot.blog.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.blog.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// handle specific exceptions

	@ExceptionHandler(ResourceNotFounException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
			ResourceNotFounException resourceNotFounException, WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), resourceNotFounException.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler(BlogAPIException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(BlogAPIException apiException,
			WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), apiException.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.OK);
	}

	// global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	 * customizing the validatorResponse
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});

		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Object> handleResourceNotFoundException(
//			MethodArgumentNotValidException methodArgumentNotValidException, WebRequest webRequest) {
//		Map<String, String> errors = new HashMap<>();
//
//		methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
//
//			String fieldName = ((FieldError) error).getField();
//			String message = error.getDefaultMessage();
//			errors.put(fieldName, message);
//		});
//
//		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//	}


	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> handleAccessDeniedException(
			AccessDeniedException accessDeniedException, WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), accessDeniedException.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

}
