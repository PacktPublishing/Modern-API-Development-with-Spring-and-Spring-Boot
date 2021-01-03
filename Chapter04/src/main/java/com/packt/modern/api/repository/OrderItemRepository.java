package com.packt.modern.api.repository;

import com.packt.modern.api.entity.OrderItemEntity;
import com.packt.modern.api.entity.TagEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;


/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
public interface OrderItemRepository extends CrudRepository<OrderItemEntity, UUID> {
}
