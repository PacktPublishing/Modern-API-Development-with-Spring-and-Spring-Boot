package com.packt.modern.api.repository;

import com.packt.modern.api.entity.AuthorizationEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface AuthorizationRepository extends ReactiveCrudRepository<AuthorizationEntity, UUID> {
}

