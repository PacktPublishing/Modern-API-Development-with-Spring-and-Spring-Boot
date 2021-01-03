package com.packt.modern.api.entity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
@Entity
@Table(name = "user")
public class UserEntity {
  @Id
  @GeneratedValue
  @Column(name = "ID", updatable = false, nullable = false)
  private UUID id;

  @NotNull(message = "User name is required.")
  @Basic(optional = false)
  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PHONE")
  private String phone;

  @Column(name = "USER_STATUS")
  private String userStatus;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(
      name = "USER_ADDRESS",
      joinColumns = @JoinColumn(name = "USER_ID"),
      inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID")
  )
  private List<AddressEntity> addresses = Collections.emptyList();;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
  private List<CardEntity> cards;

  @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
  private CartEntity cart;

  @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, orphanRemoval = true)
  private List<OrderEntity> orders;

  public UUID getId() {
    return id;
  }

  public UserEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserEntity setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserEntity setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserEntity setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public UserEntity setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getUserStatus() {
    return userStatus;
  }

  public UserEntity setUserStatus(String userStatus) {
    this.userStatus = userStatus;
    return this;
  }

  public List<AddressEntity> getAddresses() {
    return addresses;
  }

  public UserEntity setAddresses(
      List<AddressEntity> addresses) {
    this.addresses = addresses;
    return this;
  }

  public List<CardEntity> getCard() {
    return cards;
  }

  public UserEntity setCard(List<CardEntity> card) {
    this.cards = card;
    return this;
  }

  public CartEntity getCart() {
    return cart;
  }

  public UserEntity setCart(CartEntity cart) {
    this.cart = cart;
    return this;
  }

  public List<OrderEntity> getOrder() {
    return orders;
  }

  public UserEntity setOrder(List<OrderEntity> order) {
    this.orders = order;
    return this;
  }
}
