package com.hebronmall.ecommerce_api.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long productId,
        String productName,
        String storeName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {}
