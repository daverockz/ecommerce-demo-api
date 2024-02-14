package com.minex.ecommerce.user.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateUserDto {

  @NonNull
  private String name;

  @NonNull
  private String phoneNumber;

  @NonNull
  private String email;
  
}
