package com.minex.ecommerce.order.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minex.ecommerce.order.model.Order;
import com.minex.ecommerce.user.model.User;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
      Page<Order> findByUserId(User user, Pageable pageable);
}
