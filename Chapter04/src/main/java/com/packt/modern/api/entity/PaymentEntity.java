package com.packt.modern.api.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
@Entity
@Table(name = "payment")
public class PaymentEntity {

  @Id
  @GeneratedValue
  @Column(name = "ID", updatable = false, nullable = false)
  private UUID id;

  @Column(name="AUTHORIZED")
  private boolean authorized;

  @Column(name = "MESSAGE")
  private String message;

  @OneToOne(mappedBy = "paymentEntity")
  private OrderEntity orderEntity;

  public UUID getId() {
    return id;
  }

  public PaymentEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public PaymentEntity setAuthorized(boolean authorized) {
    this.authorized = authorized;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public PaymentEntity setMessage(String message) {
    this.message = message;
    return this;
  }
}
