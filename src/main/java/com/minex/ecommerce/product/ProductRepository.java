package com.minex.ecommerce.product;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
  List<Product> findByIdIn(Set<Long> ids);
}
