package com.hebronmall.ecommerce_api.controller;

import com.hebronmall.ecommerce_api.dto.auth.AdminRegisterRequest;
import com.hebronmall.ecommerce_api.dto.auth.StudentRegisterRequest;
import com.hebronmall.ecommerce_api.dto.auth.UserResponse;
import com.hebronmall.ecommerce_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // POST http://localhost:8080/api/v1/users/students
    @PostMapping("/students")
    public ResponseEntity<UserResponse> registerStudent(@Valid @RequestBody StudentRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerStudent(request));
    }

    // POST http://localhost:8080/api/v1/users/admins
    @PostMapping("/admins")
    public ResponseEntity<UserResponse> registerAdmin(@Valid @RequestBody AdminRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerAdministrator(request));
    }

    // GET http://localhost:8080/api/v1/users/students
    @GetMapping("/students")
    public ResponseEntity<List<UserResponse>> getAllStudents() {
        return ResponseEntity.ok(userService.getAllStudents());
    }

    // GET http://localhost:8080/api/v1/users/admins
    @GetMapping("/admins")
    public ResponseEntity<List<UserResponse>> getAllAdministrators() {
        return ResponseEntity.ok(userService.getAllAdministrators());
    }

    // GET http://localhost:8080/api/v1/users/1
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
