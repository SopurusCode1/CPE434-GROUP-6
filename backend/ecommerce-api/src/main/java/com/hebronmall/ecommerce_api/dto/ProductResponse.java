package com.hebronmall.ecommerce_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String imageUrl,
        Long storeId,
        String storeName, // Pulled safely from product.getStore().getName()
        LocalDateTime createdAt
) {}
