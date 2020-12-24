package com.packt.modern.api.repository;

import com.packt.modern.api.entity.ShipmentEntity;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface ShipmentRepository extends ReactiveCrudRepository<ShipmentEntity, UUID> {

  @Query("SELECT s.* FROM ecomm.order o, ecomm.shipment s where o.shipment_id=s.id and o.id = :id")
  public Flux<ShipmentEntity> getShipmentByOrderId(String id);
}

