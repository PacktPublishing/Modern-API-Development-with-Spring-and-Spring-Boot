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
@Table("ecomm.address")
public class AddressEntity {
  @Id
  @Column("id")
  private UUID id;

  @Column("number")
  private String number;

  @Column("residency")
  private String residency;

  @Column("street")
  private String street;

  @Column("city")
  private String city;

  @Column("state")
  private String state;

  @Column("country")
  private String country;

  @Column("pincode")
  private String pincode;

  /*@OneToOne(mappedBy = "addressEntity")
  private OrderEntity orderEntity;*/

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

  @Override
  public String toString() {
    return "AddressEntity{" +
        "id=" + id +
        ", number='" + number + '\'' +
        ", residency='" + residency + '\'' +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", country='" + country + '\'' +
        ", pincode='" + pincode + '\'' +
        '}';
  }
}
