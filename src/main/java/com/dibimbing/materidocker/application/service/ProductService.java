package com.dibimbing.materidocker.application.service;

import com.dibimbing.materidocker.application.dto.request.CreateProductRequest;
import com.dibimbing.materidocker.application.dto.request.UpdateProductRequest;
import com.dibimbing.materidocker.application.dto.response.ProductResponse;

import java.util.List;

/**
 * Service interface defining business operations for Product
 * Following Interface Segregation Principle
 */
public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);

    ProductResponse getProductById(String id);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(String id, UpdateProductRequest request);

    void deleteProduct(String id);
}
