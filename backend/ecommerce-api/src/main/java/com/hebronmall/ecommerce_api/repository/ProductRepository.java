package com.hebronmall.ecommerce_api.repository;

import com.hebronmall.ecommerce_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find all products belonging to a specific store
    List<Product> findByStoreId(Long storeId);

    // Search products by name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Find products within a specific price range (e.g., for filtering in NGN)
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Find products in stock for a specific store
    List<Product> findByStoreIdAndStockQuantityGreaterThan(Long storeId, Integer minStock);
}
