package com.hebronmall.ecommerce_api.service;

import com.hebronmall.ecommerce_api.dto.ProductRequest;
import com.hebronmall.ecommerce_api.dto.ProductResponse;
import com.hebronmall.ecommerce_api.exception.ResourceNotFoundException;
import com.hebronmall.ecommerce_api.model.Product;
import com.hebronmall.ecommerce_api.model.Store;
import com.hebronmall.ecommerce_api.repository.ProductRepository;
import com.hebronmall.ecommerce_api.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with ID: " + request.storeId()));

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stockQuantity(request.stockQuantity() != null ? request.stockQuantity() : 0)
                .imageUrl(request.imageUrl())
                .store(store)
                .build();

        return mapToResponse(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return mapToResponse(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByStore(Long storeId) {
        if (!storeRepository.existsById(storeId)) {
            throw new ResourceNotFoundException("Store not found with ID: " + storeId);
        }
        return productRepository.findByStoreId(storeId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> searchProductsByName(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with ID: " + request.storeId()));

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        if (request.stockQuantity() != null) {
            product.setStockQuantity(request.stockQuantity());
        }
        product.setImageUrl(request.imageUrl());
        product.setStore(store);

        return mapToResponse(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImageUrl(),
                product.getStore().getId(),
                product.getStore().getName(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
