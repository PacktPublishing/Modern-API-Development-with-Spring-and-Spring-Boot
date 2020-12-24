package com.packt.modern.api.service;

import com.packt.modern.api.entity.OrderEntity;
import com.packt.modern.api.model.NewOrder;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface OrderService {

  Mono<OrderEntity> addOrder(@Valid Mono<NewOrder> newOrder);

  Mono<OrderEntity> updateMapping(@Valid OrderEntity orderEntity);

  Flux<OrderEntity> getOrdersByCustomerId(@NotNull @Valid String customerId);

  Mono<OrderEntity> getByOrderId(String id);
}
