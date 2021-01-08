package com.packt.modern.api.entity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class ProductEntity {

  @Id
  @GeneratedValue
  @Column(name = "ID", updatable = false, nullable = false)
  private UUID id;

  @NotNull(message = "Product name is required.")
  @Basic(optional = false)
  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "PRICE")
  private BigDecimal price;

  @Column(name = "COUNT")
  private int count;

  @Column(name = "IMAGE_URL")
  private String imageUrl;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "PRODUCT_TAG",
      joinColumns = @JoinColumn(name = "PRODUCT_ID"),
      inverseJoinColumns = @JoinColumn(name = "TAG_ID")
  )
  private List<TagEntity> tags = Collections.emptyList();;

  @OneToMany(mappedBy = "product")
  private List<ItemEntity> items;

  public ProductEntity(UUID id, @NotNull(message = "Product name is required.") String name,
      String description, BigDecimal price, int count, String imageUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.count = count;
    this.imageUrl = imageUrl;
  }

  public ProductEntity() {
  }

  public UUID getId() {
    return id;
  }

  public ProductEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ProductEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ProductEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public ProductEntity setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public int getCount() {
    return count;
  }

  public ProductEntity setCount(int count) {
    this.count = count;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public ProductEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public List<TagEntity> getTags() {
    return tags;
  }

  public ProductEntity setTags(List<TagEntity> tags) {
    this.tags = tags;
    return this;
  }

  public List<ItemEntity> getItem() {
    return items;
  }

  public ProductEntity setItem(List<ItemEntity> item) {
    this.items = item;
    return this;
  }
}