package com.packt.modern.api.repository;

import com.packt.modern.api.entity.PaymentEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}

