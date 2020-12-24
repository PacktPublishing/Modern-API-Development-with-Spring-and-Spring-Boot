package com.packt.modern.api.service;

import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.entity.CardEntity;
import com.packt.modern.api.entity.UserEntity;
import com.packt.modern.api.repository.UserRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Service
public class UserServiceImpl implements UserService {

  private UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<Void> deleteCustomerById(String id) {
    return deleteCustomerById(UUID.fromString(id));
  }

  @Override
  public Mono<Void> deleteCustomerById(UUID id) {
    return repository.deleteById(id).then();
  }

  @Override
  public Flux<AddressEntity> getAddressesByCustomerId(String id) {
    return repository.getAddressesByCustomerId(id);
  }

  @Override
  public Flux<UserEntity> getAllCustomers() {
    return repository.findAll();
  }

  @Override
  public Mono<CardEntity> getCardByCustomerId(String id) {
    return repository.findCardByCustomerId(id);
  }

  @Override
  public Mono<UserEntity> getCustomerById(String id) {
    return repository.findById(UUID.fromString(id));
  }
}
