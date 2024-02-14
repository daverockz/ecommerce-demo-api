package com.minex.ecommerce.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minex.ecommerce.order.dto.CheckoutRequest;
import com.minex.ecommerce.order.model.Order;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/orders")
public class OrderController {
    
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
      return orderService.getOrders();
    }

    @GetMapping(path = "{orderId}")
    public Order getOrder(@PathVariable("orderId") Long orderId) {
      return orderService.getOrder(orderId);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestBody CheckoutRequest checkoutRequest) {
        return ResponseEntity.ok(orderService.checkout(checkoutRequest));
    }
  
}
