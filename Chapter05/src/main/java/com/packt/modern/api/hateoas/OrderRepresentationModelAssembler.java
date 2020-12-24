package com.packt.modern.api.hateoas;

import com.packt.modern.api.entity.OrderEntity;
import com.packt.modern.api.model.Order;
import com.packt.modern.api.service.ItemService;
import java.time.ZoneOffset;
import java.util.Objects;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Component
public class OrderRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<OrderEntity, Order>, HateoasSupport {

  private static String serverUri = null;
  private UserRepresentationModelAssembler uAssembler;
  private AddressRepresentationModelAssembler aAssembler;
  private CardRepresentationModelAssembler cAssembler;
  private ShipmentRepresentationModelAssembler sAssembler;
  private ItemService itemService;

  public OrderRepresentationModelAssembler(UserRepresentationModelAssembler uAssembler,
      AddressRepresentationModelAssembler aAssembler, CardRepresentationModelAssembler cAssembler,
      ShipmentRepresentationModelAssembler sAssembler, ItemService itemService) {
    this.uAssembler = uAssembler;
    this.aAssembler = aAssembler;
    this.cAssembler = cAssembler;
    this.sAssembler = sAssembler;
    this.itemService = itemService;
  }

  private String getServerUri(@Nullable ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  /**
   * Coverts the Order entity to resource
   *
   * @param entity
   */
  @Override
  public Mono<Order> toModel(OrderEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  public Order entityToModel(OrderEntity entity, ServerWebExchange exchange) {
    Order resource = new Order();
    if(Objects.isNull(entity)) {
      return resource;
    }
    BeanUtils.copyProperties(entity, resource);
    resource.id(entity.getId().toString())
        .customer(uAssembler.entityToModel(entity.getUserEntity(), exchange))
        .address(aAssembler.entityToModel(entity.getAddressEntity(), exchange))
        .card(cAssembler.entityToModel(entity.getCardEntity(), exchange))
        .id(entity.getId().toString())
        .items(itemService.toModelList(entity.getItems()))
        .date(entity.getOrderDate().toInstant().atOffset(ZoneOffset.UTC));
    String serverUri = getServerUri(exchange);
    resource.add(Link.of(String.format("%s/api/v1/orders", serverUri)).withRel("orders"));
    resource.add(
        Link.of(String.format("%s/api/v1/Orders/%s", serverUri, entity.getId())).withSelfRel());
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   */
  public Flux<Order> toListModel(Flux<OrderEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
