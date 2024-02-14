package com.minex.ecommerce.order.dto;

import java.time.LocalDateTime; 

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Integer quantity;
    private Double subTotal;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;

}
