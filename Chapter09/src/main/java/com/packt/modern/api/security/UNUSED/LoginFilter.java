package com.packt.modern.api.security.UNUSED;

import static com.packt.modern.api.security.Constants.TOKEN_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packt.modern.api.model.SignInReq;
import com.packt.modern.api.model.SignedInUser;
import com.packt.modern.api.security.JwtManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.server.MethodNotAllowedException;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter09 - Modern API Development with Spring and Spring Boot
 **/
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtManager tokenManager;
  private final ObjectMapper mapper;

  public LoginFilter(AuthenticationManager authenticationManager, JwtManager tokenManager,
      ObjectMapper mapper) {
    this.authenticationManager = authenticationManager;
    this.tokenManager = tokenManager;
    this.mapper = mapper;
    super.setFilterProcessesUrl(TOKEN_URL);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
      HttpServletResponse res) throws AuthenticationException {
    if (!req.getMethod().equals(HttpMethod.POST.name())) {
      throw new MethodNotAllowedException(req.getMethod(), List.of(HttpMethod.POST));
    }
    try (InputStream is = req.getInputStream()) {
      SignInReq user = new ObjectMapper().readValue(is, SignInReq.class);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              user.getUsername(),
              user.getPassword(),
              Collections.emptyList())
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain,
      Authentication auth) throws IOException {
    User principal = (User) auth.getPrincipal();
    String token = tokenManager.create(principal);
    SignedInUser user = new SignedInUser().username(principal.getUsername()).accessToken(token);
    res.setContentType(APPLICATION_JSON_VALUE);
    res.setCharacterEncoding("UTF-8");
    res.getWriter().print(mapper.writeValueAsString(user));
    res.getWriter().flush();
  }
}
