package com.packt.modern.api.entity;

import com.packt.modern.api.model.Order.StatusEnum;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Table("ecomm.orders")
public class OrderEntity {

  @Id
  @Column("id")
  private UUID id;

  @Column("customer_id")
  private UUID customerId;

  @Column("address_id")
  private UUID addressId;

  @Column("card_id")
  private UUID cardId;

  @Column("order_date")
  private Timestamp orderDate;

  @Column("total")
  private BigDecimal total;

  @Column("payment_id")
  private UUID paymentId;

  @Column("shipment_id")
  private UUID shipmentId;

  @Column("status")
  private StatusEnum status;

  private UUID cartId;

  private UserEntity userEntity;

  private AddressEntity addressEntity;

  private PaymentEntity paymentEntity;

  private List<ShipmentEntity> shipments = Collections.emptyList();

  private CardEntity cardEntity;

  private List<ItemEntity> items = Collections.emptyList();

  private AuthorizationEntity authorizationEntity;

  public UUID getId() {
    return id;
  }

  public OrderEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public OrderEntity setTotal(BigDecimal total) {
    this.total = total;
    return this;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public OrderEntity setStatus(StatusEnum status) {
    this.status = status;
    return this;
  }

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public OrderEntity setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
    return this;
  }

  public AddressEntity getAddressEntity() {
    return addressEntity;
  }

  public OrderEntity setAddressEntity(AddressEntity addressEntity) {
    this.addressEntity = addressEntity;
    return this;
  }

  public PaymentEntity getPaymentEntity() {
    return paymentEntity;
  }

  public OrderEntity setPaymentEntity(PaymentEntity paymentEntity) {
    this.paymentEntity = paymentEntity;
    return this;
  }

  public List<ShipmentEntity> getShipments() {
    return shipments;
  }

  public OrderEntity setShipments(List<ShipmentEntity> shipments) {
    this.shipments = shipments;
    return this;
  }

  public CardEntity getCardEntity() {
    return cardEntity;
  }

  public OrderEntity setCardEntity(CardEntity cardEntity) {
    this.cardEntity = cardEntity;
    return this;
  }

  public Timestamp getOrderDate() {
    return orderDate;
  }

  public OrderEntity setOrderDate(Timestamp orderDate) {
    this.orderDate = orderDate;
    return this;
  }

  public List<ItemEntity> getItems() {
    return items;
  }

  public OrderEntity setItems(List<ItemEntity> items) {
    this.items = items;
    return this;
  }

  public AuthorizationEntity getAuthorizationEntity() {
    return authorizationEntity;
  }

  public OrderEntity setAuthorizationEntity(
      AuthorizationEntity authorizationEntity) {
    this.authorizationEntity = authorizationEntity;
    return this;
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public OrderEntity setCustomerId(UUID customerId) {
    this.customerId = customerId;
    return this;
  }

  public UUID getAddressId() {
    return addressId;
  }

  public OrderEntity setAddressId(UUID addressId) {
    this.addressId = addressId;
    return this;
  }

  public UUID getCardId() {
    return cardId;
  }

  public OrderEntity setCardId(UUID cardId) {
    this.cardId = cardId;
    return this;
  }

  public UUID getPaymentId() {
    return paymentId;
  }

  public OrderEntity setPaymentId(UUID paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  public UUID getShipmentId() {
    return shipmentId;
  }

  public OrderEntity setShipmentId(UUID shipmentId) {
    this.shipmentId = shipmentId;
    return this;
  }

  public UUID getCartId() {
    return cartId;
  }

  public OrderEntity setCartId(UUID cartId) {
    this.cartId = cartId;
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
    OrderEntity entity = (OrderEntity) o;
    return Objects.equals(id, entity.id) && Objects
        .equals(customerId, entity.customerId) && Objects
        .equals(addressId, entity.addressId) && Objects.equals(cardId, entity.cardId)
        && Objects.equals(orderDate, entity.orderDate) && Objects
        .equals(total, entity.total) && Objects.equals(paymentId, entity.paymentId)
        && Objects.equals(shipmentId, entity.shipmentId) && status == entity.status;
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, customerId, addressId, cardId, orderDate, total, paymentId, shipmentId, status);
  }

  @Override
  public String toString() {
    return "OrderEntity{" +
        "id=" + id +
        ", customerId=" + customerId +
        ", addressId=" + addressId +
        ", cardId=" + cardId +
        ", orderDate=" + orderDate +
        ", total=" + total +
        ", paymentId=" + paymentId +
        ", shipmentId=" + shipmentId +
        ", status=" + status +
        ", cartId=" + cartId +
        ", userEntity=" + userEntity +
        ", addressEntity=" + addressEntity +
        ", paymentEntity=" + paymentEntity +
        ", shipments=" + shipments +
        ", cardEntity=" + cardEntity +
        ", items=" + items +
        ", authorizationEntity=" + authorizationEntity +
        '}';
  }
}
