package com.packt.modern.api.repository;

import com.packt.modern.api.entity.OrderEntity;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Repository
public interface OrderRepository extends ReactiveCrudRepository<OrderEntity, UUID>,
    OrderRepositoryExt {

  @Query("select o.* from ecomm.orders o join ecomm.user u on o.customer_id = u.id where u.id = :custId")
  Flux<OrderEntity> findByCustomerId(String custId);
}
