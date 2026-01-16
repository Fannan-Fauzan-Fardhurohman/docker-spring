package com.dibimbing.materidocker.infrastructure.exception;

/**
 * Exception thrown when attempting to create a product that already exists
 */
public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    public ProductAlreadyExistsException(String id, String message) {
        super(String.format("Product with ID %s %s", id, message));
    }
}
