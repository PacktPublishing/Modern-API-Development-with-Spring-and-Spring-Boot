package com.packt.modern.api.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
@Entity
@Table(name = "card")
public class CardEntity {
  @Id
  @GeneratedValue
  @Column(name = "ID", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "NUMBER")
  private String number;

  @Column(name = "EXPIRES")
  private String expires;

  @Column(name = "CVV")
  private String cvv;

  @OneToOne
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
  private UserEntity user;

  @OneToMany(mappedBy = "cardEntity", fetch = FetchType.LAZY, orphanRemoval = true)
  private List<OrderEntity> orders;

  public UUID getId() {
    return id;
  }

  public CardEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public CardEntity setNumber(String number) {
    this.number = number;
    return this;
  }

  public String getExpires() {
    return expires;
  }

  public CardEntity setExpires(String expires) {
    this.expires = expires;
    return this;
  }

  public String getCvv() {
    return cvv;
  }

  public CardEntity setCvv(String cvv) {
    this.cvv = cvv;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public CardEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  public List<OrderEntity> getOrderEntity() {
    return orders;
  }

  public CardEntity setOrderEntity(List<OrderEntity> orders) {
    this.orders = orders;
    return this;
  }
}
