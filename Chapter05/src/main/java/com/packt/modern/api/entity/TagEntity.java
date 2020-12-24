package com.packt.modern.api.entity;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/

@Table("ecomm.tag")
public class TagEntity {

  @Id
  @Column("id")
  private UUID id;

  @NotNull(message = "Product name is required.")
//  @Basic(optional = false)
  @Column("name")
  private String name;

  public UUID getId() {
    return id;
  }

  public TagEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public TagEntity setName(String name) {
    this.name = name;
    return this;
  }
}
