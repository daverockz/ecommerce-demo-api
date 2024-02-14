package com.minex.ecommerce.auth;

import com.minex.ecommerce.config.JwtService;
import com.minex.ecommerce.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private UserRepository repository;
  private JwtService jwtService;

  @Autowired
  public AuthenticationService(UserRepository repository, JwtService jwtService) {
    this.repository = repository;
    this.jwtService = jwtService;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    // TODO: Implement authentication logic

    var user = repository.findByPhoneNumber(request.getPhoneNumber())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    AuthenticationResponse response = new AuthenticationResponse();
    response.setAccessToken(jwtToken);
    response.setRefreshToken(refreshToken);
    return response;
  }

  // TODO: Implement refresh token logic
}
