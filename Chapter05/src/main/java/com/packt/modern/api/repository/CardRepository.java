package com.packt.modern.api.repository;

import com.packt.modern.api.entity.CardEntity;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface CardRepository extends ReactiveCrudRepository<CardEntity, UUID> {

}

