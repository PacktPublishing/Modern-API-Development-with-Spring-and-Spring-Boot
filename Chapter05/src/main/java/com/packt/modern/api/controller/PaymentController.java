package com.packt.modern.api.controller;

import com.packt.modern.api.PaymentApi;
import com.packt.modern.api.hateoas.PaymentRepresentationModelAssembler;
import com.packt.modern.api.model.Authorization;
import com.packt.modern.api.model.PaymentReq;
import com.packt.modern.api.service.PaymentService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@RestController
public class PaymentController implements PaymentApi {

  private PaymentService service;
  private final PaymentRepresentationModelAssembler assembler;

  public PaymentController(PaymentService service, PaymentRepresentationModelAssembler assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @Override
  public Mono<ResponseEntity<Authorization>> authorize(@Valid Mono<PaymentReq> paymentReq, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<Authorization>> getOrdersPaymentAuthorization(
      @NotNull @Valid String id, ServerWebExchange exchange) {
    return null;
  }
}
