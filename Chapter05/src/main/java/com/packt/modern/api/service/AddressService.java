package com.packt.modern.api.service;

import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.model.AddAddressReq;
import java.util.Optional;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface AddressService {
  Mono<AddressEntity> createAddress(Mono<AddAddressReq> addAddressReq);
  Mono<Void> deleteAddressesById(String id);
  Mono<Void> deleteAddressesById(UUID id);
  Mono<AddressEntity> getAddressesById(String id);
  Flux<AddressEntity> getAllAddresses();
}
