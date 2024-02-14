package com.minex.ecommerce.product;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

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

  public Map<Long, Product> getProductsByIds(Set<Long> productIds) {
    List<Product> products = productRepository.findByIdIn(productIds);
    return products.stream().collect(Collectors.toMap(Product::getId, product -> product));
  }

}
