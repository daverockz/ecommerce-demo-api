package com.minex.ecommerce.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.minex.ecommerce.user.model.Role;
import com.minex.ecommerce.user.model.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User getUser(Long userId) {
    return userRepository.findById(userId)
      .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " does not exist"));
  }

  public User getUserByPhoneNumber(String phoneNumber) {
    return userRepository.findByPhoneNumber(phoneNumber)
      .orElseThrow(() -> new EntityNotFoundException("User with phone number " + phoneNumber + " does not exist"));
  }

  public User getOrCreateUser(User user) {
    Optional<User> existingUser = userRepository.findByPhoneNumber(user.getPhoneNumber());

    return existingUser.orElseGet(() -> {
        // Create a new user if not found
        user.setRole(Role.CUSTOMER);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    });
}
  

}
