package com.roboto.clients.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.roboto.clients.validators.ApiErrorsView;
import com.roboto.clients.validators.ApiFieldError;
import com.roboto.clients.validators.ApiGlobalError;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public RestResponseEntityExceptionHandler() {
		super();
	}

	@ExceptionHandler({ NullPointerException.class })
	public ResponseEntity<Object> handleNullPointerException(final NullPointerException ex, final WebRequest request) {
		StackTraceElement[] stackTrace = ex.getStackTrace();
		for (StackTraceElement stackTraceElement : stackTrace) {
			System.out.println("ROBOTO::" + stackTraceElement.getClassName());
		}

		ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<Object>(apiException, new HttpHeaders(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(final EmptyResultDataAccessException ex,
			final WebRequest request) {
		ApiException apiException = new ApiException(ex.getMessage(), ex.getRootCause(), HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<Object>(apiException, new HttpHeaders(), HttpStatus.BAD_REQUEST);

	}

	/*
	 * @ExceptionHandler({ ConstraintViolationException.class }) public
	 * ResponseEntity<Object>
	 * handleConstraintViolationException(ConstraintViolationException ex,
	 * HttpHeaders headers, HttpStatus status, WebRequest request) { Map<String,
	 * Object> body = new LinkedHashMap<>(); body.put("timestamp", new Date());
	 * body.put("status", status.value());
	 * 
	 * // Get all errors List<String> errors =
	 * ex.getConstraintViolations().stream().map(x ->
	 * x.getInvalidValue().toString()) .collect(Collectors.toList());
	 * 
	 * body.put("errors", errors);
	 * 
	 * return new ResponseEntity<>(body, headers, status);
	 * 
	 * }
	 */

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();

		List<ApiFieldError> apiFieldErrors = bindingResult.getFieldErrors().stream()
				.map(fieldError -> new ApiFieldError(fieldError.getField(), fieldError.getCode(),
						fieldError.getRejectedValue()))
				.collect(Collectors.toList());

		List<ApiGlobalError> apiGlobalErrors = bindingResult.getGlobalErrors().stream()
				.map(globalError -> new ApiGlobalError(globalError.getCode())).collect(Collectors.toList());

		ApiErrorsView apiErrorsView = new ApiErrorsView(apiFieldErrors, apiGlobalErrors);

		return new ResponseEntity<>(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getConstraintViolations().stream().map(x -> x.getInvalidValue().toString())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}

	/*
	 * @ExceptionHandler({ Exception.class }) public ResponseEntity<Object>
	 * handleAll(Exception ex, WebRequest request) { ApiError apiError = new
	 * ApiError( HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
	 * "error occurred"); return new ResponseEntity<Object>( apiError, new
	 * HttpHeaders(), apiError.getStatus()); }
	 */

}
