package com.packt.modern.api.repository;

import com.packt.modern.api.entity.AddressEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface AddressRepository extends ReactiveCrudRepository<AddressEntity, UUID> {
}

