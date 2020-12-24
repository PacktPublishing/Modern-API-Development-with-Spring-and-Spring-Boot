package com.packt.modern.api.service;

import static java.util.stream.Collectors.toList;

import com.packt.modern.api.entity.CartEntity;
import com.packt.modern.api.entity.ItemEntity;
import com.packt.modern.api.exception.GenericAlreadyExistsException;
import com.packt.modern.api.exception.ResourceNotFoundException;
import com.packt.modern.api.model.Item;
import com.packt.modern.api.repository.CartRepository;
import com.packt.modern.api.repository.ItemRepository;
import com.packt.modern.api.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Service
public class CartServiceImpl implements CartService {

  private CartRepository repository;
  private UserRepository userRepo;
  private ItemRepository itemRepo;
  private ItemService itemService;
  private BiFunction<CartEntity, ItemEntity, CartEntity> cartEntityBiFun = (c, i) -> {
    c.getItems().add(i);
    return c;
  };
  private BiFunction<CartEntity, List<ItemEntity>, CartEntity> cartItemBiFun = (c, i) -> c
      .setItems(i);

  public CartServiceImpl(CartRepository repository, UserRepository userRepo,
      ItemService itemService, ItemRepository itemRepo) {
    this.repository = repository;
    this.userRepo = userRepo;
    this.itemService = itemService;
    this.itemRepo = itemRepo;
  }

  @Override
  @Transactional
  public Flux<Item> addCartItemsByCustomerId(CartEntity cartEntity, @Valid Mono<Item> newItem) {
    final List<ItemEntity> cartItems = cartEntity.getItems();
    return newItem.flatMap(ni -> {
      long countExisting = cartItems.stream()
          .filter(i -> i.getProductId().equals(UUID.fromString(ni.getId()))).count();
      if (countExisting == 1) {
        return Mono
            .error(new GenericAlreadyExistsException(String.format(
                "Requested Item (%s) is already there in cart. Please make a PUT call for update.",
                ni.getId())));
      }
      return itemRepo.save(itemService.toEntity(ni)).flatMap(i ->
          itemRepo.saveMapping(cartEntity.getId().toString(), i.getId().toString()).then(
              getUpdatedList(cartItems, i)
          )
      );
    }).flatMapMany(Flux::fromIterable);
  }

  private Mono<List<Item>> getUpdatedList(List<ItemEntity> cartItems, ItemEntity savedItem) {
    cartItems.add(savedItem);
    return Mono.just(itemService.toModelList(cartItems));
  }

  @Override
  public Flux<Item> addOrReplaceItemsByCustomerId(CartEntity cartEntity,
      @Valid Mono<Item> newItem) {
    final List<ItemEntity> cartItems = cartEntity.getItems();
    return newItem.flatMap(ni -> {
      List<ItemEntity> existing = cartItems.stream()
          .filter(i -> i.getProductId().equals(UUID.fromString(ni.getId()))).collect(toList());
      if (existing.size() == 1) {
        existing.get(0).setPrice(ni.getUnitPrice()).setQuantity(ni.getQuantity());
        return itemRepo.save(existing.get(0)).flatMap(i -> getUpdatedList(
            cartItems.stream().filter(j -> !j.getProductId().equals(UUID.fromString(ni.getId())))
                .collect(toList()), i));
      }
      return itemRepo.save(itemService.toEntity(ni)).flatMap(i ->
          itemRepo.saveMapping(cartEntity.getId().toString(), i.getId().toString()).then(
              getUpdatedList(cartItems, i)
          )
      );
    }).flatMapMany(Flux::fromIterable);
  }

  @Override
  @Transactional
  public Mono<Void> deleteCart(String customerId, String cartId) {
    Mono<List<String>> monoIds = itemRepo.findByCustomerId(customerId)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException(
            ". No items found in Cart of customer with Id - " + customerId)))
        .map(i -> i.getId().toString())
        .collectList().cache();
    return monoIds.zipWhen(l -> {
      List<String> ids = l.subList(0, l.size());
      return itemRepo.deleteCartItemJoinById(ids, cartId)
          .then(itemRepo.deleteByIds(ids).subscribeOn(Schedulers.boundedElastic()));
    }).then();
  }

  @Override
  public Mono<Void> deleteItemFromCart(CartEntity cartEntity, String itemId) {
    List<ItemEntity> items = cartEntity.getItems();
    items = items.stream()
        .filter(i -> i.getProductId().equals(UUID.fromString(itemId))).collect(toList());
    if (items.size() != 1) {
      return Mono
          .error(new ResourceNotFoundException(". No items found in Cart with Id - " + itemId));
    }
    List<String> ids = items.stream().map(i -> i.getId().toString()).collect(toList());
    return itemRepo.deleteCartItemJoinById(ids, cartEntity.getId().toString())
        .then(itemRepo.deleteByIds(ids).subscribeOn(Schedulers.boundedElastic()));
  }

  @Override
  public Mono<CartEntity> getCartByCustomerId(String customerId) {
    Mono<CartEntity> cart = repository.findByCustomerId(customerId)
        .subscribeOn(Schedulers.boundedElastic());
    Flux<ItemEntity> items = itemRepo.findByCustomerId(customerId)
        .subscribeOn(Schedulers.boundedElastic());
    return Mono.zip(cart, items.collectList(), cartItemBiFun);
  }
}
