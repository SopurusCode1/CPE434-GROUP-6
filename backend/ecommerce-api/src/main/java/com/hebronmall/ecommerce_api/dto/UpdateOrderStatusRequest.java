package com.hebronmall.ecommerce_api.dto;

import com.hebronmall.ecommerce_api.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(
        @NotNull(message = "Order status is required")
        OrderStatus status
) {}
