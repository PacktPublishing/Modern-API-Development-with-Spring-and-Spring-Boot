package com.packt.modern.api.hateoas;

import static java.util.stream.Collectors.toList;

import com.packt.modern.api.controller.ProductController;
import com.packt.modern.api.entity.ProductEntity;
import com.packt.modern.api.model.Product;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
@Component
public class ProductRepresentationModelAssembler extends
    RepresentationModelAssemblerSupport<ProductEntity, Product> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and
   * resource type.
   */
  public ProductRepresentationModelAssembler() {
    super(ProductController.class, Product.class);
  }

  /**
   * Coverts the Product entity to resource
   * @param entity
   * @return
   */
  @Override
  public Product toModel(ProductEntity entity) {
    Product resource = createModelWithId(entity.getId(), entity);
    resource.setId(entity.getId().toString());
    resource.setName(entity.getName());
    resource.setDescription(entity.getDescription());
    resource.setPrice(entity.getPrice());
    resource.setCount(entity.getCount());
    resource.setImageUrl(entity.getImageUrl());
    resource.setTag(entity.getTags().stream().map(t -> t.getName()).collect(toList()));
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   * @param entities
   * @return
   */
  public List<Product> toListModel(Iterable<ProductEntity> entities) {
    if (Objects.isNull(entities)) return Collections.emptyList();
    return StreamSupport.stream(entities.spliterator(), false).map(p -> toModel(p)).collect(toList());
  }
}
