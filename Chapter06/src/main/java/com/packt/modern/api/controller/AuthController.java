package com.packt.modern.api.controller;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import com.packt.modern.api.UserApi;
import com.packt.modern.api.entity.UserEntity;
import com.packt.modern.api.exception.InvalidRefreshTokenException;
import com.packt.modern.api.model.RefreshToken;
import com.packt.modern.api.model.SignInReq;
import com.packt.modern.api.model.SignedInUser;
import com.packt.modern.api.model.User;
import com.packt.modern.api.security.JwtManager;
import com.packt.modern.api.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter06 - Modern API Development with Spring and Spring Boot
 **/
@RestController
public class AuthController implements UserApi {

  private final UserService service;
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtManager tokenManager;

  public AuthController(UserService service, UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder, JwtManager tokenManager) {
    this.service = service;
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
    this.tokenManager = tokenManager;
  }

  @Override
  public ResponseEntity<SignedInUser> getAccessToken(@Valid RefreshToken refreshToken) {
    return ok(service.getAccessToken(refreshToken).orElseThrow(InvalidRefreshTokenException::new));
  }

  @Override
  public ResponseEntity<SignedInUser> signIn(@Valid SignInReq signInReq) {
    UserEntity userEntity = service.findUserByUsername(signInReq.getUsername());
    if (passwordEncoder.matches(signInReq.getPassword(), userEntity.getPassword())) {
      return ok(service.getSignedInUser(userEntity));
    }
    throw new InsufficientAuthenticationException("Unauthorized.");
  }

  @Override
  public ResponseEntity<Void> signOut(@Valid RefreshToken refreshToken) {
    service.removeRefreshToken(refreshToken);
    return accepted().build();
  }

  @Override
  public ResponseEntity<SignedInUser> signUp(@Valid User user) {
    // Have a validation for all required fields.
    return status(HttpStatus.CREATED).body(service.createUser(user).get());
  }
}
