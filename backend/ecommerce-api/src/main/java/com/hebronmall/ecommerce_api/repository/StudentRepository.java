package com.hebronmall.ecommerce_api.repository;

import com.hebronmall.ecommerce_api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByMatricNumber(String matricNumber);
    boolean existsByMatricNumber(String matricNumber);
}
