package com.minex.ecommerce.order;

import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.minex.ecommerce.order.dto.CreateOrderItem;
import com.minex.ecommerce.order.dto.CreateOrderRequest;
import com.minex.ecommerce.order.model.Order;
import com.minex.ecommerce.order.model.OrderItem;
import com.minex.ecommerce.order.model.OrderStatus;
import com.minex.ecommerce.order.repository.OrderItemRepository;
import com.minex.ecommerce.order.repository.OrderRepository;
import com.minex.ecommerce.product.Product;
import com.minex.ecommerce.product.ProductService;
import com.minex.ecommerce.user.UserService;
import com.minex.ecommerce.user.model.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final OrderItemRepository orderItemRepository;


    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new EntityNotFoundException("Order with id " + orderId + " does not exist");
        }
        orderRepository.deleteById(orderId);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " does not exist"));
    }

    public Page<Order> getOrders(Integer page, Integer size) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderRepository.findByUserId(authenticatedUser, PageRequest.of(page, size));
    }

    @Transactional
    public Order createOrder(CreateOrderRequest checkoutRequest) {
        User user = userService.getOrCreateUser(checkoutRequest.getUser());
        Order order = createOrder(checkoutRequest, user);
        List<OrderItem> orderItems = createOrderItems(checkoutRequest, order);

        order.setOrderItems(orderItems);
        order.setTotalAmountDue(calculateTotalAmountDue(orderItems));
        order.setStatus(OrderStatus.PAID);

        return orderRepository.save(order);
    }

    private Order createOrder(CreateOrderRequest checkoutRequest, User user) {
        return Order.builder()
                .userId(user)
                .deliveryAddress(checkoutRequest.getDeliveryAddress())
                .createdAt(LocalDateTime.now())
                .lastModified(LocalDateTime.now())
                // You can chain other setters here if you have more fields to set
                .build();
    }
    

    private List<OrderItem> createOrderItems(CreateOrderRequest checkoutRequest, Order order) {
        // Correctly collect IDs into a Set instead of casting a List to a Set
        Set<Long> productIds = checkoutRequest.getCheckoutItems().stream()
                .map(CreateOrderItem::getProductId)
                .collect(Collectors.toSet());
    
        Map<Long, Product> productMap = productService.getProductsByIds(productIds);
    
        List<OrderItem> orderItems = new ArrayList<>();
        for (CreateOrderItem checkoutItem : checkoutRequest.getCheckoutItems()) {
            Product product = productMap.get(checkoutItem.getProductId());
            if (product != null) {
                orderItems.add(createOrderItem(checkoutItem, order, product));
            } else {
                log.warn("Product with ID {} not found, skipping item.", checkoutItem.getProductId());
            }
        }
        return orderItemRepository.saveAll(orderItems);
    }    

    private OrderItem createOrderItem(CreateOrderItem checkoutItem, Order order, Product product) {
        return OrderItem.builder()
                .order(order)
                .price(product.getPrice())
                .quantity(checkoutItem.getQuantity())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .subTotal(product.getPrice() * checkoutItem.getQuantity())
                .createdAt(LocalDateTime.now())
                .lastModified(LocalDateTime.now())
                .build();
    }
    

    private double calculateTotalAmountDue(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(OrderItem::getSubTotal)
                .sum();
    }
}
