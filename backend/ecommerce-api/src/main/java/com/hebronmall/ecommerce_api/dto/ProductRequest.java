package com.hebronmall.ecommerce_api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Product name is required")
        @Size(max = 150, message = "Product name cannot exceed 150 characters")
        String name,

        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
        BigDecimal price,

        @Min(value = 0, message = "Stock cannot be negative")
        Integer stockQuantity,

        String imageUrl,

        @NotNull(message = "Store ID is required")
        Long storeId // Client only sends the ID, not the whole Store object
) {}
