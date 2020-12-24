package com.packt.modern.api.entity;

import java.util.UUID;
/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;*/
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
//@Entity
@Table("address")
public class AddressEntity {
  @Id
  @GeneratedValue
  @Column(name = "ID", updatable = false, nullable = false)
  private UUID id;

  @Column("NUMBER")
  private String number;

  @Column("RESIDENCY")
  private String residency;

  @Column("STREET")
  private String street;

  @Column("CITY")
  private String city;

  @Column("STATE")
  private String state;

  @Column("COUNTRY")
  private String country;

  @Column("PINCODE")
  private String pincode;

  @OneToOne(mappedBy = "addressEntity")
  private OrderEntity orderEntity;

  public UUID getId() {
    return id;
  }

  public AddressEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public AddressEntity setNumber(String number) {
    this.number = number;
    return this;
  }

  public String getResidency() {
    return residency;
  }

  public AddressEntity setResidency(String residency) {
    this.residency = residency;
    return this;
  }

  public String getStreet() {
    return street;
  }

  public AddressEntity setStreet(String street) {
    this.street = street;
    return this;
  }

  public String getCity() {
    return city;
  }

  public AddressEntity setCity(String city) {
    this.city = city;
    return this;
  }

  public String getState() {
    return state;
  }

  public AddressEntity setState(String state) {
    this.state = state;
    return this;
  }

  public String getCountry() {
    return country;
  }

  public AddressEntity setCountry(String country) {
    this.country = country;
    return this;
  }

  public String getPincode() {
    return pincode;
  }

  public AddressEntity setPincode(String pincode) {
    this.pincode = pincode;
    return this;
  }
}
