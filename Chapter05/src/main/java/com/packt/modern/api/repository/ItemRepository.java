package com.packt.modern.api.repository;

import com.packt.modern.api.entity.CartEntity;
import com.packt.modern.api.entity.ItemEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface ItemRepository extends ReactiveCrudRepository<ItemEntity, UUID> {
  @Query("select i.* from ecomm.cart c, ecomm.item i, ecomm.user u, ecomm.cart_item ci where u.id = :customerId and c.user_id=u.id and c.id=ci.cart_id and i.id=ci.item_id")
  Flux<ItemEntity> findByCustomerId(String customerId);

  @Query("delete from ecomm.cart_item where item_id in (:ids) and cart_id = :cartId")
  Mono<Void> deleteCartItemJoinById(List<String> ids, String cartId);

  @Query("delete from ecomm.item where id in (:ids)")
  Mono<Void> deleteByIds(List<String> ids);

  @Query("insert into ecomm.cart_item(cart_id, item_id) values(:cartId, :itemId)")
  Mono<Void> saveMapping(String cartId, String itemId);

}

