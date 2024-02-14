package com.minex.ecommerce.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
  @Id
  @SequenceGenerator(
    name = "product_sequence",
    sequenceName = "product_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "product_sequence"
  )
  private Long id;
  private String name;
  private String description;
  private String imageUrl;
  private Double price;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime lastModified;

  public Product(String name, String description, String imageUrl, Double price) {
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.price = price;
    this.createdAt = LocalDateTime.now();
    this.lastModified = LocalDateTime.now();
  }

}
