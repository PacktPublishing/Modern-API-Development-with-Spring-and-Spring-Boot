package com.packt.modern.api.hateoas;

import com.packt.modern.api.entity.UserEntity;
import com.packt.modern.api.model.User;
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
public class UserRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<UserEntity, User>, HateoasSupport {

  private static String serverUri = null;

  private String getServerUri(@Nullable ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  /**
   * Coverts the User entity to resource
   *
   * @param entity
   */
  @Override
  public Mono<User> toModel(UserEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  public User entityToModel(UserEntity entity, ServerWebExchange exchange) {
    User resource = new User();
    if (Objects.isNull(entity)) {
      return resource;
    }
    BeanUtils.copyProperties(entity, resource);
    resource.setId(entity.getId().toString());
    String serverUri = getServerUri(exchange);
    resource.add(Link.of(String.format("%s/api/v1/customers", serverUri)).withRel("customers"));
    resource
        .add(Link.of(String.format("%s/api/v1/customers/%s", serverUri, entity.getId())).withSelfRel());
    resource
        .add(Link.of(String.format("%s/api/v1/customers/%s/addresses", serverUri, entity.getId())).withRel("self_addresses"));
    return resource;
  }

  public User getModel(Mono<User> m, ServerWebExchange exchange) {
    AtomicReference<User> model = new AtomicReference<>();
    m.cache().subscribe(i -> model.set(i));
    return model.get();
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   */
  public Flux<User> toListModel(Flux<UserEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
