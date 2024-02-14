package com.minex.ecommerce.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class ProductConfig {

  @Bean
  CommandLineRunner commandLineRunner(ProductRepository productRepository) {
    return args -> {
      Product product1 = new Product(
        "Product 1",
        "Description 1",
        "https://placehold.co/270",
        1.00);
      Product product2 = new Product(
        "Product 2",
        "Description 2",
        "https://placehold.co/270",
        2.00);

        productRepository.saveAll(List.of(product1, product2));
    };
  }
  
}
