package com.packt.modern.api.hateoas;

import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.model.Address;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
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
public class AddressRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<AddressEntity, Address>, HateoasSupport {

  private static String serverUri = null;

  /**
   * Coverts the Address entity to resource
   *
   * @param entity
   */
  @Override
  public Mono<Address> toModel(AddressEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  private String getServerUri(@Nullable ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }


  public Address entityToModel(AddressEntity entity, ServerWebExchange exchange) {
    Address resource = new Address();
    if(Objects.isNull(entity)) {
      return resource;
    }
    BeanUtils.copyProperties(entity, resource);
    resource.setId(entity.getId().toString());
    String serverUri = getServerUri(exchange);
    resource.add(Link.of(String.format("%s/api/v1/addresses", serverUri)).withRel("addresses"));
    resource.add(
        Link.of(String.format("%s/api/v1/addresses/%s", serverUri, entity.getId())).withSelfRel());
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
  public Flux<Address> toListModel(Flux<AddressEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
