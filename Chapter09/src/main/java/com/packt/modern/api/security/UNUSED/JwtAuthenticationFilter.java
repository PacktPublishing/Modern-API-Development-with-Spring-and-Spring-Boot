package com.packt.modern.api.security.UNUSED;

import static com.packt.modern.api.security.Constants.AUTHORIZATION;
import static com.packt.modern.api.security.Constants.ROLE_CLAIM;
import static com.packt.modern.api.security.Constants.SECRET_KEY;
import static com.packt.modern.api.security.Constants.TOKEN_PREFIX;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter09 - Modern API Development with Spring and Spring Boot
 **/
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(AUTHORIZATION);
    if (Objects.isNull(header) || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }

    Optional<UsernamePasswordAuthenticationToken> authentication = getAuthentication(req);
    authentication.ifPresentOrElse(e -> SecurityContextHolder.getContext().setAuthentication(e),
        SecurityContextHolder::clearContext);
    chain.doFilter(req, res);
  }

  private Optional<UsernamePasswordAuthenticationToken> getAuthentication(
      HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION);
    if (Objects.nonNull(token)) {
      DecodedJWT jwt = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
          .build()
          .verify(token.replace(TOKEN_PREFIX, ""));
      String user = jwt.getSubject();
      @SuppressWarnings("unchecked")
      List<String> authorities = (List) jwt.getClaim(ROLE_CLAIM);
      if (Objects.nonNull(user)) {
        return Optional.of(new UsernamePasswordAuthenticationToken(
            user, null, Objects.nonNull(authorities) ? authorities.stream().map(
            SimpleGrantedAuthority::new).collect(Collectors.toList()) : Collections.emptyList()));
      }
    }
    return Optional.empty();
  }
}
