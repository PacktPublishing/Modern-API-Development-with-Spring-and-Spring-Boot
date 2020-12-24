package com.packt.modern.api.service;

import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.model.AddAddressReq;
import java.util.Optional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface AddressService {
  public Mono<AddressEntity> createAddress(Mono<AddAddressReq> addAddressReq);
  public Void deleteAddressesById(String id);
  public Mono<AddressEntity> getAddressesById(String id);
  public Flux<AddressEntity> getAllAddresses();
}
