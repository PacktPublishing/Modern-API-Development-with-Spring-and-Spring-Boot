package com.packt.modern.api.service;

import com.packt.modern.api.entity.AuthorizationEntity;
import com.packt.modern.api.model.PaymentReq;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface PaymentService {

  Mono<AuthorizationEntity> authorize(@Valid Mono<PaymentReq> paymentReq);
  Mono<AuthorizationEntity> getOrdersPaymentAuthorization(@NotNull String orderId);
}
