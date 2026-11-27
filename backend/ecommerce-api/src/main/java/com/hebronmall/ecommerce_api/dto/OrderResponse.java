package com.hebronmall.ecommerce_api.dto;

import com.hebronmall.ecommerce_api.model.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long orderId,
        String studentName,
        String matricNumber,
        String deliveryAddress,
        OrderStatus status,
        BigDecimal totalAmount,
        List<OrderItemResponse> items,
        LocalDateTime createdAt
) {}
