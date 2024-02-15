package com.minex.ecommerce.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  private ProductService productService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    productService = new ProductService(productRepository);
  }

  @Test
  public void testGetProductsByName() {
    String name = "test";
    Pageable pageable = PageRequest.of(0, 10);
    Page<Product> page = mock(Page.class);

    when(productRepository.findByNameContainingIgnoreCase(name, pageable)).thenReturn(page);

    Page<Product> result = productService.getProducts(pageable, name);

    assertEquals(page, result);
    verify(productRepository, times(1)).findByNameContainingIgnoreCase(name, pageable);
  }

  @Test
  public void testGetAllProducts() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<Product> page = mock(Page.class);

    when(productRepository.findAll(pageable)).thenReturn(page);

    Page<Product> result = productService.getProducts(pageable, null);

    assertEquals(page, result);
    verify(productRepository, times(1)).findAll(pageable);
  }

  @Test
  public void testGetProduct() {
    Long productId = 1L;
    Product product = mock(Product.class);

    when(productRepository.findById(productId)).thenReturn(Optional.of(product));

    Product result = productService.getProduct(productId);

    assertEquals(product, result);
    verify(productRepository, times(1)).findById(productId);
  }

  @Test
  public void testGetProductNotFound() {
    Long productId = 1L;

    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> productService.getProduct(productId));
    verify(productRepository, times(1)).findById(productId);
  }

  @Test
  public void testAddNewProduct() {
    Product product = mock(Product.class);
    Product savedProduct = mock(Product.class);

    when(productRepository.save(product)).thenReturn(savedProduct);

    Product result = productService.addNewProduct(product);

    assertEquals(savedProduct, result);
    verify(productRepository, times(1)).save(product);
  }
}
