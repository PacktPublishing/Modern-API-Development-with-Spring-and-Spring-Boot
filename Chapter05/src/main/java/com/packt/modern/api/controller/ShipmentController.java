package com.packt.modern.api.controller;

import com.packt.modern.api.ShipmentApi;
import com.packt.modern.api.hateoas.ShipmentRepresentationModelAssembler;
import com.packt.modern.api.model.Shipment;
import com.packt.modern.api.service.ShipmentService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@RestController
public class ShipmentController implements ShipmentApi {

  private final ShipmentRepresentationModelAssembler assembler;
  private ShipmentService service;

  public ShipmentController(ShipmentService service,
      ShipmentRepresentationModelAssembler assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @Override
  public Mono<ResponseEntity<Flux<Shipment>>> getShipmentByOrderId(@NotNull @Valid String id,
      ServerWebExchange exchange) {
    return Mono
        .just(ResponseEntity.ok(assembler.toListModel(service.getShipmentByOrderId(id), exchange)));
  }
}
