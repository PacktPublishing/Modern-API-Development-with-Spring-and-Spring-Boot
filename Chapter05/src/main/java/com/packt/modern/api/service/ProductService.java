package com.packt.modern.api.service;

import com.packt.modern.api.entity.ProductEntity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Validated
public interface ProductService {
  @NotNull Flux<ProductEntity> getAllProducts();
  Mono<ProductEntity> getProduct(@Min(value = 1L, message = "Invalid product ID.") String id);
}