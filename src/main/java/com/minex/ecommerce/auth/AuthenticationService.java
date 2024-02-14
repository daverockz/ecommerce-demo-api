package com.minex.ecommerce.auth;

import com.minex.ecommerce.config.JwtService;
import com.minex.ecommerce.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final JwtService jwtService;

  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    // TODO: Implement authentication logic

    var user = repository.findByPhoneNumber(request.getPhoneNumber())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
  }

  // TODO: Implement refresh token logic
}
