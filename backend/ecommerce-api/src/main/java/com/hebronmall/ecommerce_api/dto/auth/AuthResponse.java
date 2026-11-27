package com.hebronmall.ecommerce_api.dto.auth;

public record AuthResponse(
        String token,
        String email
) {}
