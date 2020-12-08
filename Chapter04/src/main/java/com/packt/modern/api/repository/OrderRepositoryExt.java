package com.packt.modern.api.repository;

import com.packt.modern.api.entity.OrderEntity;
import com.packt.modern.api.model.NewOrder;
import java.util.Optional;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
public interface OrderRepositoryExt {
  Optional<OrderEntity> insert(NewOrder m);
}

