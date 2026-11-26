package com.hebronmall.ecommerce_api.repository;

import com.hebronmall.ecommerce_api.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    // Look up a store by its URL slug (e.g., "hebron-gadgets")
    Optional<Store> findBySlug(String slug);

    // Check if a slug is already taken before creating/updating a store
    boolean existsBySlug(String slug);

    // Get all currently active stores
    List<Store> findByActiveTrue();

    // Search active stores by name
    List<Store> findByNameContainingIgnoreCaseAndActiveTrue(String name);
}
