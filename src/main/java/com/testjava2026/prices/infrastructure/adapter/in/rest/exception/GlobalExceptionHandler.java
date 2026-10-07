package com.testjava2026.prices.infrastructure.adapter.in.rest.exception;

import com.testjava2026.prices.domain.exception.PriceNotFoundException;
import com.testjava2026.prices.infrastructure.adapter.in.rest.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<ApiError> handlePriceNotFound(PriceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiError(HttpStatus.NOT_FOUND.value(), "No se encontró tarifa aplicable"));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		if ("applicationDate".equals(ex.getName())) {
			return ResponseEntity.badRequest()
					.body(new ApiError(HttpStatus.BAD_REQUEST.value(), "Parámetro applicationDate inválido"));
		}
		return ResponseEntity.badRequest()
				.body(new ApiError(HttpStatus.BAD_REQUEST.value(), "Parámetro inválido"));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiError> handleMissingParameter(MissingServletRequestParameterException ex) {
		return ResponseEntity.badRequest()
				.body(new ApiError(HttpStatus.BAD_REQUEST.value(),
						"Falta el parámetro obligatorio: " + ex.getParameterName()));
	}
}
