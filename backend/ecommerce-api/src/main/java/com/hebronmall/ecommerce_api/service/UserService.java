package com.hebronmall.ecommerce_api.service;

import com.hebronmall.ecommerce_api.dto.auth.AdminRegisterRequest;
import com.hebronmall.ecommerce_api.dto.auth.StudentRegisterRequest;
import com.hebronmall.ecommerce_api.dto.auth.UserResponse;
import com.hebronmall.ecommerce_api.exception.DuplicateResourceException;
import com.hebronmall.ecommerce_api.exception.ResourceNotFoundException;
import com.hebronmall.ecommerce_api.model.Administrator;
import com.hebronmall.ecommerce_api.model.Student;
import com.hebronmall.ecommerce_api.model.User;
import com.hebronmall.ecommerce_api.repository.AdministratorRepository;
import com.hebronmall.ecommerce_api.repository.StudentRepository;
import com.hebronmall.ecommerce_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final AdministratorRepository administratorRepository;

    @Transactional
    public UserResponse registerStudent(StudentRegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email '" + request.email() + "' is already registered");
        }
        if (studentRepository.existsByMatricNumber(request.matricNumber())) {
            throw new DuplicateResourceException("Matric number '" + request.matricNumber() + "' is already registered");
        }

        Student student = Student.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(request.password()) // Replace with passwordEncoder.encode(request.password()) when adding Spring Security
                .matricNumber(request.matricNumber())
                .build();

        return mapToResponse(studentRepository.save(student));
    }

    @Transactional
    public UserResponse registerAdministrator(AdminRegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email '" + request.email() + "' is already registered");
        }

        Administrator admin = Administrator.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(request.password()) // Replace with passwordEncoder.encode(request.password()) when adding Spring Security
                .build();

        return mapToResponse(administratorRepository.save(admin));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllAdministrators() {
        return administratorRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user) {
        if (user instanceof Student student) {
            return new UserResponse(
                    student.getId(),
                    student.getFullName(),
                    student.getEmail(),
                    "STUDENT",
                    student.getMatricNumber(),
                    student.getCreatedAt(),
                    student.getUpdatedAt()
            );
        }
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                "ADMINISTRATOR",
                null,
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
