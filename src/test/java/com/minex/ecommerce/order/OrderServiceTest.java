package com.minex.ecommerce.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.minex.ecommerce.order.dto.CreateOrderDto;
import com.minex.ecommerce.order.model.Order;
import com.minex.ecommerce.order.repository.OrderItemRepository;
import com.minex.ecommerce.order.repository.OrderRepository;
import com.minex.ecommerce.product.ProductService;
import com.minex.ecommerce.user.UserService;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private UserService userService;

  @Mock
  private ProductService productService;

  @Mock
  private OrderItemRepository orderItemRepository;


  private OrderService orderService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    orderService = new OrderService(orderRepository, userService, productService, orderItemRepository);
  }

  @Test
  public void testGetOrderById() {
    Long orderId = 1L;
    Order order = mock(Order.class);

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    Order result = orderService.getOrder(orderId);

    assertEquals(order, result);
    verify(orderRepository, times(1)).findById(orderId);
  }

  @Test
  public void testGetOrderNotFound() {
    Long orderId = 1L;

    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> orderService.getOrder(orderId));
    verify(orderRepository, times(1)).findById(orderId);
  }

  // @Test
  // public void testAddNewOrder() {
  //   CreateOrderDto createOrderDto = mock(CreateOrderDto.class);
  //   Order savedOrder = mock(Order.class);

  //   when(orderRepository.save(savedOrder)).thenReturn(savedOrder);

  //   Order result = orderService.createOrder(createOrderDto);

  //   assertEquals(savedOrder, result);
  //   verify(orderRepository, times(1)).save(savedOrder);
  // }
}
