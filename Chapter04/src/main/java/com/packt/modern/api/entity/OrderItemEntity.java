package com.packt.modern.api.entity;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Entity
@Table(name = "order_item")
public class OrderItemEntity {

  @Id
  @GeneratedValue
  @Column(name = "ID", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "order_id")
  private UUID orderId;

  @Column(name = "item_id")
  private UUID itemId;

  public UUID getId() {
    return id;
  }

  public OrderItemEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public UUID getOrderId() {
    return orderId;
  }

  public OrderItemEntity setOrderId(UUID orderId) {
    this.orderId = orderId;
    return this;
  }

  public UUID getItemId() {
    return itemId;
  }

  public OrderItemEntity setItemId(UUID itemId) {
    this.itemId = itemId;
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
    OrderItemEntity that = (OrderItemEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId)
        && Objects.equals(itemId, that.itemId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderId, itemId);
  }

  @Override
  public String toString() {
    return "OrderItemEntity{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", itemId=" + itemId +
        '}';
  }
}
