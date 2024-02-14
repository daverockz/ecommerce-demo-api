package com.minex.ecommerce.product;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }
  
  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  public Product getProduct(Long productId) {
    return productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product with id " + productId + " does not exist"));
  }

  public void addNewProduct(Product product) {
    productRepository.save(product);
  }

  public void deleteProduct(Long productId) {
    System.out.println(productId);
  }
  
  public void updateProduct(Long productId, String name, String description) {
    System.out.println(name + " " + description);
  }

}
