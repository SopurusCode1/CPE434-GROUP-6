package com.hebronmall.ecommerce_api.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Student extends User {

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Matric number is required")
    private String matricNumber;
    
}