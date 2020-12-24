package com.packt.modern.api.repository;

import com.packt.modern.api.entity.TagEntity;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface TagRepository extends ReactiveCrudRepository<TagEntity, UUID> {

  @Query("SELECT t.* FROM ecomm.product p, ecomm.tag t, ecomm.product_tag pt where p.id = :id and p.id=pt.product_id and t.id = pt.tag_id")
  Flux<TagEntity> findTagsByProductId(String id);
}
