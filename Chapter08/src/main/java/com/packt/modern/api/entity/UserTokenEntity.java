package com.packt.modern.api.entity;

import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter08 - Modern API Development with Spring and Spring Boot
 **/
@Entity
@Table(name = "user_token")
public class UserTokenEntity {

  @Id
  @GeneratedValue
  @Column(name = "ID", updatable = false, nullable = false)
  private UUID id;

  @NotNull(message = "Refresh token is required.")
  @Basic(optional = false)
  @Column(name = "refresh_token")
  private String refreshToken;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;

  public UUID getId() {
    return id;
  }

  public UserTokenEntity setId(UUID id, UserEntity user, String refreshToken) {
    this.id = id;
    this.refreshToken = refreshToken;
    this.user = user;
    return this;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public UserTokenEntity setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public UserTokenEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }
}
