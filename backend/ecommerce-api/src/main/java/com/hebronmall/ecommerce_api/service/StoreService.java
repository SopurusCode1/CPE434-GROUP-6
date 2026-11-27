package com.hebronmall.ecommerce_api.service;

import com.hebronmall.ecommerce_api.dto.StoreRequest;
import com.hebronmall.ecommerce_api.dto.StoreResponse;
import com.hebronmall.ecommerce_api.exception.DuplicateResourceException;
import com.hebronmall.ecommerce_api.exception.ResourceNotFoundException;
import com.hebronmall.ecommerce_api.model.Store;
import com.hebronmall.ecommerce_api.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public StoreResponse createStore(StoreRequest request) {
        String slug = formatSlug(request.slug(), request.name());

        if (storeRepository.existsBySlug(slug)) {
            throw new DuplicateResourceException("Store slug '" + slug + "' is already in use");
        }

        Store store = Store.builder()
                .name(request.name())
                .slug(slug)
                .description(request.description())
                .address(request.address())
                .city(request.city())
                .phoneNumber(request.phoneNumber())
                .active(request.active() != null ? request.active() : true)
                .build();

        return mapToResponse(storeRepository.save(store));
    }

    @Transactional(readOnly = true)
    public List<StoreResponse> getAllActiveStores() {
        return storeRepository.findByActiveTrue().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public StoreResponse getStoreById(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with ID: " + id));
        return mapToResponse(store);
    }

    @Transactional(readOnly = true)
    public StoreResponse getStoreBySlug(String slug) {
        Store store = storeRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with slug: " + slug));
        return mapToResponse(store);
    }

    @Transactional
    public StoreResponse updateStore(Long id, StoreRequest request) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with ID: " + id));

        String updatedSlug = formatSlug(request.slug(), request.name());
        if (!store.getSlug().equals(updatedSlug) && storeRepository.existsBySlug(updatedSlug)) {
            throw new DuplicateResourceException("Store slug '" + updatedSlug + "' is already in use");
        }

        store.setName(request.name());
        store.setSlug(updatedSlug);
        store.setDescription(request.description());
        store.setAddress(request.address());
        store.setCity(request.city());
        store.setPhoneNumber(request.phoneNumber());
        if (request.active() != null) {
            store.setActive(request.active());
        }

        return mapToResponse(storeRepository.save(store));
    }

    @Transactional
    public void deleteStore(Long id) {
        if (!storeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Store not found with ID: " + id);
        }
        storeRepository.deleteById(id);
    }

    private String formatSlug(String providedSlug, String name) {
        String base = (providedSlug != null && !providedSlug.isBlank()) ? providedSlug : name;
        return base.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
    }

    private StoreResponse mapToResponse(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getSlug(),
                store.getDescription(),
                store.getAddress(),
                store.getCity(),
                store.getPhoneNumber(),
                store.isActive(),
                store.getCreatedAt(),
                store.getUpdatedAt()
        );
    }
}
