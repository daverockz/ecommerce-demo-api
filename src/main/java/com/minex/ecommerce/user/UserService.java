package com.minex.ecommerce.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;


@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public void addUser(User user) {
    userRepository.save(user);
  }

  public void deleteUser(Long userId) {
    boolean exists = userRepository.existsById(userId);
    if (!exists) {
      throw new IllegalStateException("User with id " + userId + " does not exist");
    }
    userRepository.deleteById(userId);
  }

  public void updateUser(Long userId, User user) {
    User existingUser = userRepository.findById(userId)
      .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist"));

    existingUser.setName(user.getName());
    existingUser.setPhoneNumber(user.getPhoneNumber());
    existingUser.setEmail(user.getEmail());

    userRepository.save(existingUser);
  }

  public User getUser(Long userId) {
    return userRepository.findById(userId)
      .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist"));
  }

  public User getUserByPhoneNumber(String phoneNumber) {
    return userRepository.findByPhoneNumber(phoneNumber)
      .orElseThrow(() -> new IllegalStateException("User with phone number " + phoneNumber + " does not exist"));
  }

  public User getOrCreateUser(User user) {
    Optional<User> existingUser = userRepository.findByPhoneNumber(user.getPhoneNumber());

    return existingUser.orElseGet(() -> {
        // Create a new user if not found
        user.setRole(Role.CUSTOMER);
        user.setCreatedAt(Instant.now());
        return userRepository.save(user);
    });
}
  

}
