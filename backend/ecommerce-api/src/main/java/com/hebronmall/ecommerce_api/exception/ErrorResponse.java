package com.hebronmall.ecommerce_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        int status,
        String error,
        String message,
        Map<String, String> fieldErrors, // Populated during @Valid failures
        LocalDateTime timestamp
) {}
