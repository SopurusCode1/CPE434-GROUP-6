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

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected String fullName;
}


