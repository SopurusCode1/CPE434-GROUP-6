package com.hebronmall.ecommerce_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter // Lombok: generates all getters
@Setter // Lombok: generates all setters
@NoArgsConstructor // Lombok: generates required empty constructor
@AllArgsConstructor // Lombok: generates constructor with all fields
@SuperBuilder

public abstract class User extends BaseEntity {

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(nullable = false, unique = true)
    protected String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    protected String password;

    @NotBlank(message = "Full name is required")
    @Column(nullable = false)
    protected String fullName;
}


