package com.minex.ecommerce.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

  Optional<User> findByPhoneNumber(String phoneNumber);
  
}
