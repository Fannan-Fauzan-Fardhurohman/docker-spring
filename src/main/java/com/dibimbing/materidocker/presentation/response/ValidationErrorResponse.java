package com.dibimbing.materidocker.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Validation error response structure for request validation failures
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {

    @Builder.Default
    private String status = "error";

    private String message;

    private Map<String, String> errors;

    private String path;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
