package com.minex.ecommerce.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String userId;
    private Double totalAmountDue;
    private String deliveryAddress;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private String status;
    private List<OrderItemDto> orderItems;
}
