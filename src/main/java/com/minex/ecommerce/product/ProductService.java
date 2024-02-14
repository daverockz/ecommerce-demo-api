package com.minex.ecommerce.product;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Page<Product> getProducts(Pageable pageable, String name) {
    if (name != null && !name.isEmpty()) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    } else {
        return productRepository.findAll(pageable);
    }
}

  public Product getProduct(Long productId) {
    return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " does not exist"));
  }

  public Product addNewProduct(Product product) {
    return productRepository.save(product);
  }

  public void deleteProduct(Long productId) {
    productRepository.deleteById(productId);
  }

  public Map<Long, Product> getProductsByIds(Set<Long> productIds) {
    List<Product> products = productRepository.findByIdIn(productIds);
    return products.stream().collect(Collectors.toMap(Product::getId, product -> product));
  }

}
