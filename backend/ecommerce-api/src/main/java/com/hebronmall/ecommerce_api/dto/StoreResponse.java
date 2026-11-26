package com.hebronmall.ecommerce_api.dto;

import java.time.LocalDateTime;

public record StoreResponse(
        Long id,
        String name,
        String slug,
        String description,
        String address,
        String city,
        String phoneNumber,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
