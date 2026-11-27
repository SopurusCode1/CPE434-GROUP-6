package com.hebronmall.ecommerce_api.controller;

import com.hebronmall.ecommerce_api.dto.StoreRequest;
import com.hebronmall.ecommerce_api.dto.StoreResponse;
import com.hebronmall.ecommerce_api.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // POST http://localhost:8080/api/v1/stores
    @PostMapping
    public ResponseEntity<StoreResponse> createStore(@Valid @RequestBody StoreRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.createStore(request));
    }

    // GET http://localhost:8080/api/v1/stores
    @GetMapping
    public ResponseEntity<List<StoreResponse>> getAllActiveStores() {
        return ResponseEntity.ok(storeService.getAllActiveStores());
    }

    // GET http://localhost:8080/api/v1/stores/1
    @GetMapping("/{id}")
    public ResponseEntity<StoreResponse> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    // GET http://localhost:8080/api/v1/stores/slug/hebron-tech-hub
    @GetMapping("/slug/{slug}")
    public ResponseEntity<StoreResponse> getStoreBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(storeService.getStoreBySlug(slug));
    }

    // PUT http://localhost:8080/api/v1/stores/1
    @PutMapping("/{id}")
    public ResponseEntity<StoreResponse> updateStore(
            @PathVariable Long id,
            @Valid @RequestBody StoreRequest request
    ) {
        return ResponseEntity.ok(storeService.updateStore(id, request));
    }

    // DELETE http://localhost:8080/api/v1/stores/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
