package com.packt.modern.api.entity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
@Entity
@Table(name = "cart")
public class CartEntity {

  @Id
  @GeneratedValue
  @Column(name = "ID", updatable = false, nullable = false)
  private UUID id;

  @OneToOne
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
  private UserEntity user;

  @ManyToMany(
      cascade = CascadeType.ALL
  )
  @JoinTable(
      name = "CART_ITEM",
      joinColumns = @JoinColumn(name = "CART_ID"),
      inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
  )
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
}
