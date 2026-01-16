package com.dibimbing.materidocker.application.service.impl;

import com.dibimbing.materidocker.application.dto.request.CreateProductRequest;
import com.dibimbing.materidocker.application.dto.request.UpdateProductRequest;
import com.dibimbing.materidocker.application.dto.response.ProductResponse;
import com.dibimbing.materidocker.application.service.ProductService;
import com.dibimbing.materidocker.domain.entity.Product;
import com.dibimbing.materidocker.domain.repository.ProductRepository;
import com.dibimbing.materidocker.infrastructure.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of ProductService
 * Contains business logic for product operations
 * Following Single Responsibility Principle - focuses only on business logic
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        log.info("Creating new product with name: {}", request.getName());

        // Generate unique ID
        String productId = UUID.randomUUID().toString();

        // Build product entity
        Product product = Product.builder()
                .id(productId)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Save to repository
        Product savedProduct = productRepository.save(product);

        log.info("Product created successfully with ID: {}", savedProduct.getId());

        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse getProductById(String id) {
        log.info("Fetching product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id, "not found"));

        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("Fetching all products");

        List<Product> products = productRepository.findAll();

        log.info("Found {} products", products.size());

        return products.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(String id, UpdateProductRequest request) {
        log.info("Updating product with ID: {}", id);

        // Find existing product
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id, "not found"));

        // Update only non-null fields
        if (request.getName() != null) {
            existingProduct.setName(request.getName());
        }
        if (request.getDescription() != null) {
            existingProduct.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            existingProduct.setPrice(request.getPrice());
        }
        if (request.getStock() != null) {
            existingProduct.setStock(request.getStock());
        }

        existingProduct.setUpdatedAt(LocalDateTime.now());

        // Save updated product
        Product updatedProduct = productRepository.save(existingProduct);

        log.info("Product updated successfully with ID: {}", updatedProduct.getId());

        return mapToResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        log.info("Deleting product with ID: {}", id);

        // Verify product exists
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id, "not found");
        }

        productRepository.deleteById(id);

        log.info("Product deleted successfully with ID: {}", id);
    }

    /**
     * Maps Product entity to ProductResponse DTO
     * Following Single Responsibility - separate mapping logic
     */
    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
