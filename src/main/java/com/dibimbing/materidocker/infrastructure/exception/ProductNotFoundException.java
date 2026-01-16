package com.dibimbing.materidocker.infrastructure.exception;

/**
 * Exception thrown when a product is not found
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String id, String message) {
        super(String.format("Product with ID %s %s", id, message));
    }
}
