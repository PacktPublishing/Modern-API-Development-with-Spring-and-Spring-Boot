package com.packt.modern.api.hateoas;

import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.model.Address;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Component
public class AddressRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<AddressEntity, Address> {

  /**
   * Coverts the Address entity to resource
   *
   * @param entity
   */
  @Override
  public Mono<Address> toModel(AddressEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity));
  }

  /*public Mono<Address> toModelFromMono(Mono<AddressEntity> entity, ServerWebExchange exchange) {
    AtomicReference<Mono<Address>> adddress = new AtomicReference<>(Mono.empty());
    entity.cache().subscribe(e -> adddress.set(toModel(e, exchange)));
    return adddress.get();
  }*/

  public Address entityToModel(AddressEntity entity) {
    Address resource = new Address();
    BeanUtils.copyProperties(entity, resource);
    resource.add(Link.of("/api/v1/addresses").withRel("addresses"));
    resource.add(Link.of(String.format("/api/v1/addresses/%s", entity.getId())).withRel("self"));
    return resource;
  }

  public Address getModel(Mono<Address> m) {
    AtomicReference<Address> model = new AtomicReference<>();
    m.cache().subscribe(i -> model.set(i));
    return model.get();
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   */
  public Flux<Address> toListModel(Flux<AddressEntity> entities) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e)));
  }
}
