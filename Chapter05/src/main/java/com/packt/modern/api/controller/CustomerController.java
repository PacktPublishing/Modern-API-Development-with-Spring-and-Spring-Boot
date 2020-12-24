package com.packt.modern.api.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import com.packt.modern.api.CustomerApi;
import com.packt.modern.api.exception.ResourceNotFoundException;
import com.packt.modern.api.hateoas.AddressRepresentationModelAssembler;
import com.packt.modern.api.hateoas.CardRepresentationModelAssembler;
import com.packt.modern.api.hateoas.UserRepresentationModelAssembler;
import com.packt.modern.api.model.Address;
import com.packt.modern.api.model.Card;
import com.packt.modern.api.model.User;
import com.packt.modern.api.service.UserService;
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
public class CustomerController implements CustomerApi {

  private final UserRepresentationModelAssembler assembler;
  private final AddressRepresentationModelAssembler addrAssembler;
  private final CardRepresentationModelAssembler cardAssembler;
  private UserService service;

  public CustomerController(UserService service, UserRepresentationModelAssembler assembler,
      AddressRepresentationModelAssembler addrAssembler,
      CardRepresentationModelAssembler cardAssembler) {
    this.service = service;
    this.assembler = assembler;
    this.addrAssembler = addrAssembler;
    this.cardAssembler = cardAssembler;
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteCustomerById(String id, ServerWebExchange exchange) {
    return service.getCustomerById(id)
        .flatMap(c -> service.deleteCustomerById(c.getId())
            .then(Mono.just(status(HttpStatus.ACCEPTED).<Void>build())))
        .switchIfEmpty(Mono.just(notFound().build()));
  }

  @Override
  public Mono<ResponseEntity<Flux<Address>>> getAddressesByCustomerId(String id,
      ServerWebExchange exchange) {

    return Mono.just(ok(service.getAddressesByCustomerId(id)
        .map(c -> addrAssembler.entityToModel(c, exchange))
        .switchIfEmpty(
            Mono.error(new ResourceNotFoundException("No address found for given customer")))));
  }

  @Override
  public Mono<ResponseEntity<Flux<User>>> getAllCustomers(ServerWebExchange exchange) {
    return Mono.just(ok(assembler.toListModel(service.getAllCustomers(), exchange)));
  }

  @Override
  public Mono<ResponseEntity<Card>> getCardByCustomerId(String id, ServerWebExchange exchange) {
    return service.getCardByCustomerId(id).map(c -> cardAssembler.entityToModel(c, exchange))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(notFound().build());
  }

  @Override
  public Mono<ResponseEntity<User>> getCustomerById(String id, ServerWebExchange exchange) {
    return service.getCustomerById(id).map(c -> assembler.entityToModel(c, exchange))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(notFound().build());
  }
}
