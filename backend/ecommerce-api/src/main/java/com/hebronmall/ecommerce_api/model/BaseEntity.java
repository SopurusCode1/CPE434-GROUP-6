package com.hebronmall.ecommerce_api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter // Lombok: generates all getters
@Setter // Lombok: generates all setters
@NoArgsConstructor // Lombok: generates required empty constructor
@AllArgsConstructor // Lombok: generates constructor with all fields
// @Builder // Lombok: lets you build objects cleanly (User.builder().email("...").build())
@SuperBuilder

public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreationTimestamp // Hibernate automatically sets this when first saved
    @Column(updatable = false)
    protected LocalDateTime createdAt;

    @UpdateTimestamp // Hibernate automatically updates this on every save
    protected LocalDateTime updatedAt;
}