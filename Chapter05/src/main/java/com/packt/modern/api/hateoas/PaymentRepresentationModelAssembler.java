package com.packt.modern.api.hateoas;

import static java.util.stream.Collectors.toList;

import com.packt.modern.api.controller.PaymentController;
import com.packt.modern.api.entity.PaymentEntity;
import com.packt.modern.api.entity.PaymentEntity;
import com.packt.modern.api.model.Payment;
import com.packt.modern.api.model.Payment;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.StreamSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
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
public class PaymentRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<PaymentEntity, Payment> {

  /**
   * Coverts the Payment entity to resource
   * @param entity
   * @return
   */
  @Override
  public Mono<Payment> toModel(PaymentEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity));
  }

  public Payment entityToModel(PaymentEntity entity) {
    Payment resource = new Payment();
    if(Objects.isNull(entity)) {
      return resource;
    }
    BeanUtils.copyProperties(entity, resource);
    resource.add(Link.of("/api/v1/Payments").withRel("Payments"));
    resource.add(Link.of(String.format("/api/v1/Payments/%s", entity.getId())).withRel("self"));
    return resource;
  }

  public Payment getModel(Mono<Payment> m) {
    AtomicReference<Payment> model = new AtomicReference<>();
    m.cache().subscribe(i -> model.set(i));
    return model.get();
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   */
  public Flux<Payment> toListModel(Flux<PaymentEntity> entities) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e)));
  }
  
  /*public Payment toModel(PaymentEntity entity) {
    Payment resource = createModelWithId(entity.getId(), entity);
    resource.setId(entity.getId().toString());
    return resource;
  }

  public List<Payment> toListModel(Iterable<PaymentEntity> entities) {
    if (Objects.isNull(entities)) return Collections.emptyList();
    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e)).collect(toList());
  }*/
}
