package com.minex.ecommerce.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<Page<Product>> getProducts(
          @RequestParam(defaultValue = "0") Integer page,
          @RequestParam(defaultValue = "10") Integer size,
          @RequestParam(defaultValue = "id") String sortBy,
          @RequestParam(defaultValue = "ASC") String direction,
          @RequestParam(required = false) String name) {
      Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
      Pageable pageable = PageRequest.of(page, size, sort);
      return ResponseEntity.ok(productService.getProducts(pageable, name));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
      return ResponseEntity.ok(productService.getProduct(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
      productService.deleteProduct(id);
      return ResponseEntity.noContent().build();
  }
  
}
