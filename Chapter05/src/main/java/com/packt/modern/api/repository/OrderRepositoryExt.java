package com.packt.modern.api.repository;

import com.packt.modern.api.entity.OrderEntity;
import com.packt.modern.api.model.NewOrder;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface OrderRepositoryExt {

  Mono<OrderEntity> insert(Mono<NewOrder> m);

  Mono<OrderEntity> updateMapping(OrderEntity orderEntity);
}

