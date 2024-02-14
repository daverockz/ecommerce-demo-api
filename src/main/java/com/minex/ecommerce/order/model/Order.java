package com.minex.ecommerce.order.model;

import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.minex.ecommerce.user.User;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "orders")
public class Order {
  @Id
  @SequenceGenerator(
    name = "order_sequence",
    sequenceName = "order_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "order_sequence"
  )
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User userId;
  private Double totalAmountDue;
  private String deliveryAddress;
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime lastModified;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<OrderItem> orderItems;

  public Order() {
  }

  public Order(User userId, Double totalAmountDue, String deliveryAddress, LocalDateTime createdAt, LocalDateTime lastModified, OrderStatus status, List<OrderItem> orderItems) {
    this.userId = userId;
    this.totalAmountDue = totalAmountDue;
    this.deliveryAddress = deliveryAddress;
    this.createdAt = createdAt;
    this.lastModified = lastModified;
    this.status = status;
    this.orderItems = orderItems;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  public Double getTotalAmountDue() {
    return totalAmountDue;
  }

  public void setTotalAmountDue(Double totalAmountDue) {
    this.totalAmountDue = totalAmountDue;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  @Override
  public String toString() {
    return "Order{" +
      "id=" + id +
      ", userId=" + userId +
      ", totalAmountDue=" + totalAmountDue +
      ", deliveryAddress='" + deliveryAddress + '\'' +
      ", createdAt=" + createdAt +
      ", lastModified=" + lastModified +
      ", status=" + status +
      ", orderItems=" + orderItems +
      '}';
  }

}
