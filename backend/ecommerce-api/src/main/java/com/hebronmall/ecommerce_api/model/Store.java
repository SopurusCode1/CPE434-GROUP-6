package com.hebronmall.ecommerce_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Store extends BaseEntity{
    @NotBlank(message = "Store name is required")
    @Column(nullable = false, length = 120)
    private String name;

    @Column(unique = true, length = 150)
    private String slug; // Useful for URLs like ://yourapp.com

    @Column(columnDefinition = "TEXT") // Allows longer text than standard VARCHAR(255)
    private String description;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String address;

    @Column(length = 100)
    private String city;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    @Column(length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    @Builder.Default // Ensures Lombok's @Builder respects the default value
    private boolean Active = true;

    // --- RELATIONSHIPS (Uncomment as you add other models) ---

    // Example 1: Many stores can belong to one owner (User)
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "owner_id", nullable = false)
    // private User owner;

    // Example 2: One store can have many products
    // @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    // @Builder.Default
    // private List<Product> products = new ArrayList<>();

}