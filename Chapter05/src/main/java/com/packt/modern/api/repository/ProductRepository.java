package com.packt.modern.api.repository;

import com.packt.modern.api.entity.ProductEntity;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, UUID> {

  /*@Query("SELECT p.*, t.name as tags FROM ecomm.product p, ecomm.tag t, ecomm.product_tag pt where p.id = :id and p.id=pt.product_id and t.id = pt.tag_id")
  Mono<ProductEntity> getProduct(String id);*/
}
