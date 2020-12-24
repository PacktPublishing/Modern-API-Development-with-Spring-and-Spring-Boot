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

  public Flux<Item> addCartItemsByCustomerId(CartEntity cartEntity, @Valid Mono<Item> item);

  public Flux<Item> addOrReplaceItemsByCustomerId(CartEntity cartEntity, @Valid Mono<Item> newItem);

  public Mono<Void> deleteCart(String customerId, String cartId);

  public Mono<Void> deleteItemFromCart(CartEntity cartEntity, String itemId);

  public Mono<CartEntity> getCartByCustomerId(String customerId);
}
