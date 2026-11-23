package com.hebronmall.ecommerce_api.model;

@Entity
@Table(name = "administrators")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@NoArgsConstructor
// @AllArgsConstructor
@SuperBuilder

public class Administrators extends User{

}