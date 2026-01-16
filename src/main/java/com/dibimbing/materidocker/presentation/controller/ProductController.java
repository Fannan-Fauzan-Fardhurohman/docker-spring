package com.dibimbing.materidocker.presentation.controller;

import com.dibimbing.materidocker.application.dto.request.CreateProductRequest;
import com.dibimbing.materidocker.application.dto.request.UpdateProductRequest;
import com.dibimbing.materidocker.application.dto.response.ProductResponse;
import com.dibimbing.materidocker.application.service.ProductService;
import com.dibimbing.materidocker.presentation.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<BaseResponse<ProductResponse>> createProduct(
            @Valid @RequestBody CreateProductRequest request) {

        log.info("Received request to create product: {}", request.getName());

        ProductResponse response = productService.createProduct(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success("Product created successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponse>> getProductById(
            @PathVariable String id) {

        log.info("Received request to get product with ID: {}", id);

        ProductResponse response = productService.getProductById(id);

        return ResponseEntity.ok(BaseResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductResponse>>> getAllProducts() {

        log.info("Received request to get all products");

        List<ProductResponse> responses = productService.getAllProducts();

        return ResponseEntity.ok(
                BaseResponse.success("Retrieved " + responses.size() + " products", responses));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponse>> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody UpdateProductRequest request) {

        log.info("Received request to update product with ID: {}", id);

        ProductResponse response = productService.updateProduct(id, request);

        return ResponseEntity.ok(BaseResponse.success("Product updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteProduct(@PathVariable String id) {

        log.info("Received request to delete product with ID: {}", id);

        productService.deleteProduct(id);

        return ResponseEntity.ok(BaseResponse.success("Product deleted successfully", null));
    }
}
