package com.packt.modern.api.entity;

import java.util.Objects;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Table("ecomm.card")
public class CardEntity {
  @Id
  @Column("id")
  private UUID id;

  @Column("number")
  private String number;

  @Column("expires")
  private String expires;

  @Column("cvv")
  private String cvv;

  @Column("user_id")
  private String userId;

  /*@OneToOne
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID")*/
  private UserEntity user;

  // @OneToOne(mappedBy = "cardEntity")
  private OrderEntity orderEntity;

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

  public OrderEntity getOrderEntity() {
    return orderEntity;
  }

  public CardEntity setOrderEntity(OrderEntity orderEntity) {
    this.orderEntity = orderEntity;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public CardEntity setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardEntity that = (CardEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(number, that.number)
        && Objects.equals(expires, that.expires) && Objects.equals(cvv, that.cvv)
        && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, number, expires, cvv, userId);
  }

  @Override
  public String toString() {
    return "CardEntity{" +
        "id=" + id +
        ", number='" + number + '\'' +
        ", expires='" + expires + '\'' +
        ", cvv='" + cvv + '\'' +
        ", userId='" + userId + '\'' +
        ", user=" + user +
        ", orderEntity=" + orderEntity +
        '}';
  }
}
