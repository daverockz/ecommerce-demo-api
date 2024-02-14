package com.minex.ecommerce.order.dto;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class CreateOrderItemDto {

  @NonNull
  private Long productId;

  @NonNull
  private Integer quantity;
  
}
