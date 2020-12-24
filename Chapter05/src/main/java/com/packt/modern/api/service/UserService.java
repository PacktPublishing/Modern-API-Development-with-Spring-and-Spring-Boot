package com.packt.modern.api.service;

import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.entity.CardEntity;
import com.packt.modern.api.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface UserService {
  Mono<Void> deleteCustomerById(String id);
  Mono<Void> deleteCustomerById(UUID id);
  Flux<AddressEntity> getAddressesByCustomerId(String id);
  Flux<UserEntity> getAllCustomers();
  Mono<CardEntity> getCardByCustomerId(String id);
  Mono<UserEntity> getCustomerById(String id);
}
