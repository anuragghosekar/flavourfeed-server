package com.demo.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
	public class ApplicationExceptionHandler {

	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(IllegalArgumentException.class)
	    public Map<String, String> handleInvalidArgument(Exception ex) {
	        Map<String, String> errorMap = new HashMap<>();
	        errorMap.put("errorMessage", ex.getMessage());
	        return errorMap;
	    }

	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ExceptionHandler({IOException.class, DataAccessException.class, IllegalStateException.class, NullPointerException.class,
	                       SecurityException.class, ConstraintViolationException.class})
	    public Map<String, String> handleInternalError(Exception ex) {
	        Map<String, String> errorMap = new HashMap<>();
	        errorMap.put("errorMessage", ex.getMessage());
	        return errorMap;
	    }

	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ExceptionHandler({NoSuchElementException.class})
	    public Map<String, String> handleNotFound(Exception ex) {
	        Map<String, String> errorMap = new HashMap<>();
	        errorMap.put("errorMessage", ex.getMessage());
	        return errorMap;
	    }

	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
	    public Map<String, String> handleHttpClientError(Exception ex) {
	        Map<String, String> errorMap = new HashMap<>();
	        errorMap.put("errorMessage", ex.getMessage());
	        return errorMap;
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach(error -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return errors;
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(HttpMessageNotReadableException.class)
	    public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
	        Map<String, String> errorMap = new HashMap<>();
	        errorMap.put("errorMessage", ex.getMessage());
	        return errorMap;
	    }
	    
	    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	    public Map<String, String> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
	        Map<String, String> errorMap = new HashMap<>();
	        errorMap.put("errorMessage", ex.getMessage());
	        return errorMap;
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MissingServletRequestParameterException.class)
	    public Map<String, String> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
	        Map<String, String> errorMap = new HashMap<>();
	        errorMap.put("errorMessage", ex.getMessage());
	        return errorMap;
	    }
 }