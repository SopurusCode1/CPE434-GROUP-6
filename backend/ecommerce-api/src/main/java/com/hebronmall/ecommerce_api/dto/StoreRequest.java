package com.hebronmall.ecommerce_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record StoreRequest(
        @NotBlank(message = "Store name is required")
        @Size(max = 120, message = "Store name cannot exceed 120 characters")
        String name,

        @Size(max = 150, message = "Slug cannot exceed 150 characters")
        String slug,

        String description,

        @NotBlank(message = "Address is required")
        String address,

        @Size(max = 100, message = "City cannot exceed 100 characters")
        String city,

        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
        String phoneNumber,

        Boolean active // Optional on creation (defaults to true), useful for PUT updates
) {}
