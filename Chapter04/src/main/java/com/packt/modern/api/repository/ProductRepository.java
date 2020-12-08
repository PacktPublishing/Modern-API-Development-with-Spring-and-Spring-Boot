package com.packt.modern.api.repository;

import com.packt.modern.api.entity.ProductEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {
}

