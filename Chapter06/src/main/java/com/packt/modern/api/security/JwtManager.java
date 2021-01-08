package com.packt.modern.api.security;

import static com.packt.modern.api.security.Constants.EXPIRATION_TIME;
import static com.packt.modern.api.security.Constants.ROLE_CLAIM;
import static com.packt.modern.api.security.Constants.SECRET_KEY;
import static java.util.stream.Collectors.toList;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter06 - Modern API Development with Spring and Spring Boot
 **/
@Component
public class JwtManager {

  private final RSAPrivateKey privateKey;
  private final RSAPublicKey publicKey;

  public JwtManager(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
    this.privateKey = privateKey;
    this.publicKey = publicKey;
  }

  public String create(UserDetails principal) {
    final long now = System.currentTimeMillis();
    return JWT.create()
        .withIssuer("Modern API Development with Spring and Spring Boot")
        .withSubject(principal.getUsername())
        .withClaim(ROLE_CLAIM,
            principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(toList()))
        .withIssuedAt(new Date(now))
        .withExpiresAt(new Date(now + EXPIRATION_TIME))
        //.sign(Algorithm.HMAC512(SECRET_KEY.getBytes(StandardCharsets.UTF_8)));
        .sign(Algorithm.RSA256(publicKey, privateKey));
  }
}
