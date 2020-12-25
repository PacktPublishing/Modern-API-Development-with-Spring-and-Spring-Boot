package com.packt.modern.api.repository;

import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.entity.CardEntity;
import com.packt.modern.api.entity.UserEntity;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface UserRepository extends ReactiveCrudRepository<UserEntity, UUID> {

  @Query("SELECT a.* FROM ecomm.user u, ecomm.address a, ecomm.user_address ua where u.id=ua.user_id and a.id=ua.address_id and u.id = :id")
  Flux<AddressEntity> getAddressesByCustomerId(String id);

  @Query("SELECT c.* FROM ecomm.user u, ecomm.card c where u.id=c.user_id and u.id = :id")
  Mono<CardEntity> findCardByCustomerId(String id);
}

