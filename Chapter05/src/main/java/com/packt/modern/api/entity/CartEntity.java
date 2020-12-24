package com.packt.modern.api.entity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Table("ecomm.cart")
public class CartEntity {

  @Id
  @Column("id")
  private UUID id;

  /*@OneToOne
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID")*/
  private UserEntity user;

  /*@OneToMany(
      cascade = CascadeType.ALL
  )
  @JoinTable(
      name = "CART_ITEM",
      joinColumns = @JoinColumn(name = "CART_ID"),
      inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
  )*/
  private List<ItemEntity> items = Collections.emptyList();

  public UUID getId() {
    return id;
  }

  public CartEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public CartEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  public List<ItemEntity> getItems() {
    return items;
  }

  public CartEntity setItems(List<ItemEntity> items) {
    this.items = items;
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
    CartEntity that = (CartEntity) o;
    return user.equals(that.user) && Objects.equals(items, that.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, items);
  }

  @Override
  public String toString() {
    return "CartEntity{" +
        "id=" + id +
        ", user=" + user +
        ", items=" + items +
        '}';
  }
}
