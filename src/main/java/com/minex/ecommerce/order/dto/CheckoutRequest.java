package com.minex.ecommerce.order.dto;

import java.util.List;

import com.minex.ecommerce.user.User;

public class CheckoutRequest {
  private User user; 
  private List<CheckoutItem> checkoutItems;
  private String deliveryAddress;
  private String notes;
  private String paymentMethod;
  private String paymentPhoneNumber;

  public CheckoutRequest(User user, List<CheckoutItem> checkoutItems, String deliveryAddress, String notes, String paymentMethod, String paymentPhoneNumber) {
    this.user = user;
    this.checkoutItems = checkoutItems;
    this.deliveryAddress = deliveryAddress;
    this.notes = notes;
    this.paymentMethod = paymentMethod;
    this.paymentPhoneNumber = paymentPhoneNumber;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<CheckoutItem> getCheckoutItems() {
    return checkoutItems;
  }

  public void setCheckoutItems(List<CheckoutItem> checkoutItems) {
    this.checkoutItems = checkoutItems;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getPaymentPhoneNumber() {
    return paymentPhoneNumber;
  }

  public void setPaymentPhoneNumber(String paymentPhoneNumber) {
    this.paymentPhoneNumber = paymentPhoneNumber;
  }

  @Override
  public String toString() {
    return "CheckoutRequest [checkoutItems=" + checkoutItems + ", deliveryAddress=" + deliveryAddress + ", notes=" + notes + ", paymentMethod=" + paymentMethod + ", paymentPhoneNumber=" + paymentPhoneNumber + ", user=" + user + "]";
  }

}
