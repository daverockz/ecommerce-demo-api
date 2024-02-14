package com.minex.ecommerce.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.minex.ecommerce.order.dto.CheckoutItem;
import com.minex.ecommerce.order.dto.CheckoutRequest;
import com.minex.ecommerce.order.model.Order;
import com.minex.ecommerce.order.model.OrderItem;
import com.minex.ecommerce.order.model.OrderStatus;
import com.minex.ecommerce.order.repository.OrderItemRepository;
import com.minex.ecommerce.order.repository.OrderRepository;
import com.minex.ecommerce.product.Product;
import com.minex.ecommerce.product.ProductService;
import com.minex.ecommerce.user.User;
import com.minex.ecommerce.user.UserService;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, 
    UserService userService, 
    ProductService productService, 
    OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.orderItemRepository = orderItemRepository;
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalStateException("Order with id " + orderId + " does not exist");
        }
        orderRepository.deleteById(orderId);
    }

    public void updateOrder(Long orderId, Order order) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order with id " + orderId + " does not exist"));

        // Update existingOrder properties if needed

        orderRepository.save(existingOrder);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order with id " + orderId + " does not exist"));
    }

    public List<Order> getOrders() {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderRepository.findByUserId(authenticatedUser);
    }

    @Transactional
    public Order checkout(CheckoutRequest checkoutRequest) {
        User user = userService.getOrCreateUser(checkoutRequest.getUser());
        Order order = createOrder(checkoutRequest, user);
        List<OrderItem> orderItems = createOrderItems(checkoutRequest, order);

        order.setTotalAmountDue(calculateTotalAmountDue(orderItems));
        order.setOrderItems(orderItems);
        order.setCreatedAt(LocalDateTime.now());
        order.setLastModified(LocalDateTime.now());
        order.setStatus(OrderStatus.PAID);

        return orderRepository.save(order);
    }

    private Order createOrder(CheckoutRequest checkoutRequest, User user) {
        Order order = new Order();
        order.setUserId(user);
        order.setDeliveryAddress(checkoutRequest.getDeliveryAddress());
        // Set other order details...
        return order;
    }

    private List<OrderItem> createOrderItems(CheckoutRequest checkoutRequest, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CheckoutItem checkoutItem : checkoutRequest.getCheckoutItems()) {
            Product product = productService.getProduct(checkoutItem.getProductId());

            if (product != null) {
                OrderItem orderItem = createOrderItem(checkoutItem, order, product);
                orderItems.add(orderItem);
            }
            // Handle the case where the product doesn't exist
        }
        return orderItemRepository.saveAll(orderItems);
    }

    private OrderItem createOrderItem(CheckoutItem checkoutItem, Order order, Product product) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setPrice(product.getPrice());
        orderItem.setQuantity(checkoutItem.getQuantity());
        orderItem.setName(product.getName());
        orderItem.setDescription(product.getDescription());
        orderItem.setImageUrl(product.getImageUrl());
        orderItem.setSubTotal(orderItem.getPrice() * orderItem.getQuantity());
        orderItem.setCreatedAt(LocalDateTime.now());
        orderItem.setLastModified(LocalDateTime.now());
        // Set other order item details...
        return orderItem;
    }

    private double calculateTotalAmountDue(List<OrderItem> orderItems) {
        return orderItems.stream().mapToDouble(OrderItem::getSubTotal).sum();
    }
}
