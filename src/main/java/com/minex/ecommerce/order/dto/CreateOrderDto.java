package com.minex.ecommerce.order.dto;

import java.util.List;

import com.minex.ecommerce.user.model.User;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderDto {

  @NotNull
  private User user; 

  @NotNull
  private List<CreateOrderItemDto> checkoutItems;

  @NotNull
  private String deliveryAddress;

  @NotNull
  private String notes;

  @NotNull
  private String paymentMethod;

  @NotNull
  private String paymentPhoneNumber;

}
