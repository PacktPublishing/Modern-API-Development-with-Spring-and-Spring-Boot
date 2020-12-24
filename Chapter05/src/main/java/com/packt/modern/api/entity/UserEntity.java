package com.packt.modern.api.entity;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Table("ecomm.user")
public class UserEntity {

  @Id
  @Column("id")
  private UUID id;

  @NotNull(message = "User name is required.")
  @Column("username")
  private String username;

  @Column("password")
  private String password;

  @Column("first_name")
  private String firstName;

  @Column("last_name")
  private String lastName;

  @Column("email")
  private String email;

  @Column("phone")
  private String phone;

  @Column("user_status")
  private String userStatus;

  /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(
      name = "USER_ADDRESS",
      joinColumns = @JoinColumn(name = "USER_ID"),
      inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID")
  )*/
  //private Flux<AddressEntity> addresses = Flux.empty();

  //  @OneToOne(mappedBy = "user")
  private CardEntity card;

  //  @OneToOne(mappedBy = "user")
  private CartEntity cart;

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

  /*public Flux<AddressEntity> getAddresses() {
    return addresses;
  }

  public UserEntity setAddresses(
      Flux<AddressEntity> addresses) {
    this.addresses = addresses;
    return this;
  }*/

  public CardEntity getCard() {
    return card;
  }

  public UserEntity setCard(CardEntity card) {
    this.card = card;
    return this;
  }

  public CartEntity getCart() {
    return cart;
  }

  public UserEntity setCart(CartEntity cart) {
    this.cart = cart;
    return this;
  }
}
