package com.dibimbing.materidocker.config;

import com.dibimbing.materidocker.infrastructure.exception.ProductAlreadyExistsException;
import com.dibimbing.materidocker.infrastructure.exception.ProductNotFoundException;
import com.dibimbing.materidocker.presentation.response.ErrorResponse;
import com.dibimbing.materidocker.presentation.response.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for centralized error handling
 * Follows Single Responsibility Principle - handles all exception responses
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        /**
         * Handle ProductNotFoundException
         */
        @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleProductNotFoundException(
                        ProductNotFoundException ex, HttpServletRequest request) {

                log.error("Product not found: {}", ex.getMessage());

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .status("error")
                                .message(ex.getMessage())
                                .error("Product Not Found")
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        /**
         * Handle ProductAlreadyExistsException
         */
        @ExceptionHandler(ProductAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleProductAlreadyExistsException(
                        ProductAlreadyExistsException ex, HttpServletRequest request) {

                log.error("Product already exists: {}", ex.getMessage());

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .status("error")
                                .message(ex.getMessage())
                                .error("Product Already Exists")
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        /**
         * Handle validation errors (Bean Validation)
         */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
                        MethodArgumentNotValidException ex, HttpServletRequest request) {

                log.error("Validation error occurred");

                Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach(error -> {
                        String fieldName = ((FieldError) error).getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                });

                ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                                .status("error")
                                .message("Validation failed")
                                .errors(errors)
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        /**
         * Handle generic exceptions
         */
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGenericException(
                        Exception ex, HttpServletRequest request) {

                log.error("Unexpected error occurred: ", ex);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .status("error")
                                .message("An unexpected error occurred")
                                .error(ex.getClass().getSimpleName())
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

        /**
         * Handle IllegalArgumentException
         */
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
                        IllegalArgumentException ex, HttpServletRequest request) {

                log.error("Invalid argument: {}", ex.getMessage());

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .status("error")
                                .message(ex.getMessage())
                                .error("Invalid Argument")
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
}
