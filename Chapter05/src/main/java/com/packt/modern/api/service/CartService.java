package com.packt.modern.api.service;

import com.packt.modern.api.entity.CartEntity;
import com.packt.modern.api.model.Item;
import java.util.List;
import javax.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public interface CartService {

  Flux<Item> addCartItemsByCustomerId(CartEntity cartEntity, @Valid Mono<Item> item);

  Flux<Item> addOrReplaceItemsByCustomerId(CartEntity cartEntity, @Valid Mono<Item> newItem);

  Mono<Void> deleteCart(String customerId, String cartId);

  Mono<Void> deleteItemFromCart(CartEntity cartEntity, String itemId);

  Mono<CartEntity> getCartByCustomerId(String customerId);
}
