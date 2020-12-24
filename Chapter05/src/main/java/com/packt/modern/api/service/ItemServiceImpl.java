package com.packt.modern.api.service;

import static java.util.stream.Collectors.toList;

import com.packt.modern.api.entity.CartEntity;
import com.packt.modern.api.entity.ItemEntity;
import com.packt.modern.api.model.Item;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Service
public class ItemServiceImpl implements ItemService {

  @Override
  public Mono<ItemEntity> toEntity(Mono<Item> model) {
    return model.map(m ->
        new ItemEntity()//.setProduct(new ProductEntity().setId(UUID.fromString(m.getId())))
            .setPrice(m.getUnitPrice())
            .setQuantity(m.getQuantity())
    );
  }

  /*@Override
  public Mono<Item> toModel(Mono<ItemEntity> entity) {
    return entity.map(e -> new Item().id(e.getProduct().getId().toString()).unitPrice(e.getPrice()).quantity(e.getQuantity()));
  }

  @Override
  public Mono<Item> toModel(ItemEntity e) {
    Item m = new Item();
    m.id(e.getProduct().getId().toString()).unitPrice(e.getPrice()).quantity(e.getQuantity());
    return Mono.just(m);
  }*/

  @Override
  public Mono<List<Item>> fluxTolList(Flux<ItemEntity> items) {
    if (Objects.isNull(items)) {
      return Mono.just(Collections.emptyList());
    }
    return items.map(e -> toModel(e)).collectList();
  }

  @Override
  public Flux<Item> toItemFlux(Mono<CartEntity> cart) {
    if (Objects.isNull(cart)) {
      return Flux.empty();
    }
    return cart.flatMapMany(c -> toModelFlux(c.getItems()));
  }

  @Override
  public ItemEntity toEntity(Item m) {
    ItemEntity e = new ItemEntity();
    e.setProductId(UUID.fromString(m.getId()))
        .setPrice(m.getUnitPrice())
        .setQuantity(m.getQuantity());
    return e;
  }

  @Override
  public List<ItemEntity> toEntityList(List<Item> items) {
    if (Objects.isNull(items)) {
      return Collections.emptyList();
    }
    return items.stream().map(m -> toEntity(m)).collect(toList());
  }

  @Override
  public Item toModel(ItemEntity e) {
    Item m = new Item();
    m.id(e.getProductId().toString())
        .unitPrice(e.getPrice()).quantity(e.getQuantity());
    return m;
  }

  @Override
  public List<Item> toModelList(List<ItemEntity> items) {
    if (Objects.isNull(items)) {
      return Collections.emptyList();
    }
    return items.stream().map(e -> toModel(e)).collect(toList());
  }

  @Override
  public Flux<Item> toModelFlux(List<ItemEntity> items) {
    if (Objects.isNull(items)) {
      return Flux.empty();
    }
    return Flux.fromIterable(items.stream().map(e -> toModel(e)).collect(toList()));
  }
}
