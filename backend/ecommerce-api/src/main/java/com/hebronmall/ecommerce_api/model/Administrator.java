package com.hebronmall.ecommerce_api.model;

import jakarta.persistence.*;             
import lombok.*;                          
import lombok.experimental.SuperBuilder; 

@Entity
@Table(name = "administrators")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@NoArgsConstructor
// @AllArgsConstructor
@SuperBuilder

public class Administrator extends User{

}