package com.packt.modern.api.entity;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
@Entity
@Table(name = "shipment")
public class ShipmentEntity {
  @Id
  @GeneratedValue
  @Column(name = "ID", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "EST_DELIVERY_DATE")
  private Timestamp estDeliveryDate;

  @Column(name = "CARRIER")
  private String carrier;

  public UUID getId() {
    return id;
  }

  public ShipmentEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public Timestamp getEstDeliveryDate() {
    return estDeliveryDate;
  }

  public ShipmentEntity setEstDeliveryDate(Timestamp estDeliveryDate) {
    this.estDeliveryDate = estDeliveryDate;
    return this;
  }

  public String getCarrier() {
    return carrier;
  }

  public ShipmentEntity setCarrier(String carrier) {
    this.carrier = carrier;
    return this;
  }

}
