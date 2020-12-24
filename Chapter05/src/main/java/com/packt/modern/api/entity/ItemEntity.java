package com.packt.modern.api.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Table("ecomm.item")
public class ItemEntity {

  @Id
  @Column("id")
  private UUID id;

  @Column("product_id")
  private UUID productId;
  /*@OneToOne
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")*/
  //private ProductEntity product;

  @Column("unit_price")
  private BigDecimal price;

  @Column("quantity")
  private int quantity;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "CART_ID", referencedColumnName = "ID")
//  private CartEntity cart;

  public UUID getId() {
    return id;
  }

  public ItemEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  /*public ProductEntity getProduct() {
    return product;
  }

  public ItemEntity setProduct(ProductEntity product) {
    this.product = product;
    return this;
  }*/

  public BigDecimal getPrice() {
    return price;
  }

  public ItemEntity setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public int getQuantity() {
    return quantity;
  }

  public ItemEntity setQuantity(int quantity) {
    this.quantity = quantity;
    return this;
  }

/*  public CartEntity getCart() {
    return cart;
  }

  public ItemEntity setCart(CartEntity cart) {
    this.cart = cart;
    return this;
  }*/

  public UUID getProductId() {
    return productId;
  }

  public ItemEntity setProductId(UUID productId) {
    this.productId = productId;
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
    ItemEntity that = (ItemEntity) o;
    return quantity == that.quantity && productId.equals(that.productId)
        && Objects.equals(price, that.price);// && Objects.equals(cart, that.cart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, price, quantity);//product, cart);
  }
}
