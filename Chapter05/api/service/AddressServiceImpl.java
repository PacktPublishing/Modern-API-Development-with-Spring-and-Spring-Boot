package com.packt.modern.api.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.model.AddAddressReq;
import com.packt.modern.api.repository.AddressRepository;
import java.util.Optional;
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
    return repository.save(toEntity(addAddressReq));
  }

  @Override
  public Void deleteAddressesById(String id) {
    repository.deleteById(UUID.fromString(id));
    return null; // Humpy Dumpy way of returning Void
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
    return entity.setNumber(model.getNumber()).setResidency(model.getResidency())
        .setStreet(model.getStreet()).setCity(model.getCity()).setState(model.getState())
        .setCountry(model.getCountry()).setPincode(model.getPincode());
  }
  private AddressEntity toEntity(Mono<AddAddressReq> monoModel) {
    AddressEntity entity = new AddressEntity();
    monoModel.cache().subscribe(model -> BeanUtils.copyProperties(model, entity));
    return entity;
  }
}
