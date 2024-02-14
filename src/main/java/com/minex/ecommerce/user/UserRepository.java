package com.minex.ecommerce.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minex.ecommerce.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

  Optional<User> findByPhoneNumber(String phoneNumber);
  
}
