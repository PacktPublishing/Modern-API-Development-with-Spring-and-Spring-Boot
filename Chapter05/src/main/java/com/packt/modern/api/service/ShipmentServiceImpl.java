package com.packt.modern.api.service;

import com.packt.modern.api.entity.ShipmentEntity;
import com.packt.modern.api.repository.ShipmentRepository;
import javax.validation.constraints.Min;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Service
public class ShipmentServiceImpl implements ShipmentService {

  private ShipmentRepository repository;

  public ShipmentServiceImpl(ShipmentRepository repository) {
    this.repository = repository;
  }

  @Override
  public Flux<ShipmentEntity> getShipmentByOrderId(
      @Min(value = 1L, message = "Invalid shipment ID.") String id) {
    return repository.getShipmentByOrderId(id);
  }
}
