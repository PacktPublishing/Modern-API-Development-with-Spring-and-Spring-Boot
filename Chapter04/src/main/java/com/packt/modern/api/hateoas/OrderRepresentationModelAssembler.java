package com.packt.modern.api.hateoas;

import static java.util.stream.Collectors.toList;

import com.packt.modern.api.controller.OrderController;
import com.packt.modern.api.entity.OrderEntity;
import com.packt.modern.api.model.Order;
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
public class OrderRepresentationModelAssembler extends
    RepresentationModelAssemblerSupport<OrderEntity, Order> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and
   * resource type.
   */
  public OrderRepresentationModelAssembler() {
    super(OrderController.class, Order.class);
  }

  /**
   * Coverts the Order entity to resource
   * @param entity
   * @return
   */
  @Override
  public Order toModel(OrderEntity entity) {
    Order resource = createModelWithId(entity.getId(), entity);
    resource.setId(entity.getId().toString());
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   * @param entities
   * @return
   */
  public List<Order> toListModel(Iterable<OrderEntity> entities) {
    if (Objects.isNull(entities)) return Collections.emptyList();
    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e)).collect(toList());
  }

}
