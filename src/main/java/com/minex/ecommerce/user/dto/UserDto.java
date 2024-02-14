package com.minex.ecommerce.user.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {

  private Long id;
  private String name;
  private String phoneNumber;
  private String email;

  private String role;

  private LocalDateTime createdAt;

  private LocalDateTime lastModified;
  
}
