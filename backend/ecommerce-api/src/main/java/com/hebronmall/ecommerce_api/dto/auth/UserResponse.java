package com.hebronmall.ecommerce_api.dto.auth;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        String role,         // "STUDENT" or "ADMINISTRATOR"
        String matricNumber, // Populated for students, null for admins
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
