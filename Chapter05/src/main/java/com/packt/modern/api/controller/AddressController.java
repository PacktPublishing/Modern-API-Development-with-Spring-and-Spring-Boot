package com.packt.modern.api.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import com.packt.modern.api.AddressApi;
import com.packt.modern.api.hateoas.AddressRepresentationModelAssembler;
import com.packt.modern.api.model.AddAddressReq;
import com.packt.modern.api.model.Address;
import com.packt.modern.api.service.AddressService;
import org.springframework.http.HttpStatus;
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
public class AddressController implements AddressApi {

  private final AddressRepresentationModelAssembler assembler;
  private AddressService service;

  public AddressController(AddressService addressService,
      AddressRepresentationModelAssembler assembler) {
    this.service = addressService;
    this.assembler = assembler;
  }

  @Override
  public Mono<ResponseEntity<Address>> createAddress(Mono<AddAddressReq> addAddressReq,
      ServerWebExchange exchange) {
    return service.createAddress(addAddressReq)
            .map(a -> assembler.entityToModel(a, exchange)).map(e -> status(HttpStatus.CREATED).body(e));
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteAddressesById(String id, ServerWebExchange exchange) {
    return service.getAddressesById(id)
        .flatMap(a -> service.deleteAddressesById(a.getId()).then(Mono.just(status(HttpStatus.ACCEPTED).<Void>build())))
        .switchIfEmpty(Mono.just(notFound().build()));
  }

  @Override
  public Mono<ResponseEntity<Address>> getAddressesById(String id, ServerWebExchange exchange) {
    return service.getAddressesById(id).map(a -> assembler.entityToModel(a, exchange))
        .map(ResponseEntity::ok).defaultIfEmpty(notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<Address>>> getAllAddresses(ServerWebExchange exchange) {
    return Mono.just(ok(assembler.toListModel(service.getAllAddresses(), exchange)));
  }
}
