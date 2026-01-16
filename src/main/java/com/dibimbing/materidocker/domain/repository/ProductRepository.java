package com.dibimbing.materidocker.domain.repository;

import com.dibimbing.materidocker.domain.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface defining data access operations for Product
 * Following Dependency Inversion Principle - domain layer defines the contract
 */
public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();

    void deleteById(String id);

    boolean existsById(String id);
}
