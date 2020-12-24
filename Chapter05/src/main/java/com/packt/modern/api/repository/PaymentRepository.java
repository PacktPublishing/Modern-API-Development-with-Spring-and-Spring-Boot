package com.packt.modern.api.repository;

import com.packt.modern.api.entity.PaymentEntity;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface PaymentRepository extends ReactiveCrudRepository<PaymentEntity, UUID> {

}

