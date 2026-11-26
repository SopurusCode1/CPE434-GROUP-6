package com.hebronmall.ecommerce_api.repository;

import com.hebronmall.ecommerce_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Used during login (Spring Security) to find any user type by email
    Optional<User> findByEmail(String email);

    // Used during registration to prevent duplicate emails across all user roles
    boolean existsByEmail(String email);
}
