package com.packt.modern.api.repository;

import com.packt.modern.api.entity.ItemEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
public interface ItemRepository extends CrudRepository<ItemEntity, UUID> {
  @Query(value = "select i.* from ecomm.cart c, ecomm.item i, ecomm.user u, ecomm.cart_item ci where u.id = :customerId and c.user_id=u.id and c.id=ci.cart_id and i.id=ci.item_id", nativeQuery=true)
  Iterable<ItemEntity> findByCustomerId(String customerId);

  @Modifying
  @Query(value = "delete from ecomm.cart_item where item_id in (:ids) and cart_id = :cartId", nativeQuery = true)
  void deleteCartItemJoinById(List<UUID> ids, UUID cartId);
}

