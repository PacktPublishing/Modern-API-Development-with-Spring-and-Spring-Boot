package com.packt.modern.api.service;

import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.model.AddAddressReq;
import com.packt.modern.api.repository.AddressRepository;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Service
public class AddressServiceImpl implements AddressService {

  private AddressRepository repository;

  @Autowired
  public AddressServiceImpl(AddressRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<AddressEntity> createAddress(Mono<AddAddressReq> addAddressReq) {
    return addAddressReq.map(this::toEntity).flatMap(repository::save);
  }

  @Override
  public Mono<Void> deleteAddressesById(String id) {
    /*return Mono.just(UUID.fromString(id)).doOnError(ex -> new IllegalArgumentException("Illegarl UUID"))
    .map(uuid -> repository.deleteById(uuid)).then();*/
    return deleteAddressesById(UUID.fromString(id));
  }

  @Override
  public Mono<Void> deleteAddressesById(UUID id) {
    return repository.deleteById(id).then();
  }

  @Override
  public Mono<AddressEntity> getAddressesById(String id) {
    return repository.findById(UUID.fromString(id));
  }

  @Override
  public Flux<AddressEntity> getAllAddresses() {
    return repository.findAll();
  }

  private AddressEntity toEntity(AddAddressReq model) {
    AddressEntity entity = new AddressEntity();
    BeanUtils.copyProperties(model, entity);
    return entity;
  }

  private AddressEntity toEntity(Mono<AddAddressReq> monoModel) {
    AddressEntity entity = new AddressEntity();
    monoModel.cache().subscribe(model -> {
      BeanUtils.copyProperties(model, entity);
    });
    return entity;
  }
}
