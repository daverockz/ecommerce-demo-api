package com.minex.ecommerce.order.dto;

import lombok.Data;

@Data
public class CheckoutItem {
  private Long productId;
  private Integer quantity;

  public CheckoutItem() {
  }

  public CheckoutItem(Long productId, Integer quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "CheckoutItem{" +
      "productId=" + productId +
      ", quantity=" + quantity +
      '}';
  }
}
