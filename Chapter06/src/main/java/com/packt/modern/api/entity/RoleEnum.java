package com.packt.modern.api.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter06 - Modern API Development with Spring and Spring Boot
 **/
public enum RoleEnum implements GrantedAuthority {
  USER(Const.USER),

  ADMIN(Const.ADMIN),

  MANAGER(Const.MANAGER);

  private String authority;

  RoleEnum(String authority) {
    this.authority = authority;
  }

  @JsonCreator
  public static RoleEnum fromAuthority(String authority) {
    for (RoleEnum b : RoleEnum.values()) {
      if (b.authority.equals(authority)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + authority + "'");
  }

  @Override
  public String toString() {
    return String.valueOf(authority);
  }

  @Override
  @JsonValue
  public String getAuthority() {
    return authority;
  }

  public class Const {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String MANAGER = "ROLE_MANAGER";
  }
}
