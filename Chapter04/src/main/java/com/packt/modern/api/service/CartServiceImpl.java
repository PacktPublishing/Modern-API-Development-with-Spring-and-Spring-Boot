package com.packt.modern.api.service;

import static java.util.stream.Collectors.toList;
import static org.springframework.objenesis.instantiator.util.UnsafeUtils.getUnsafe;

import com.packt.modern.api.entity.CartEntity;
import com.packt.modern.api.entity.ItemEntity;
import com.packt.modern.api.exception.CustomerNotFoundException;
import com.packt.modern.api.exception.ItemNotFoundException;
import com.packt.modern.api.model.Item;
import com.packt.modern.api.repository.CartRepository;
import com.packt.modern.api.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import javax.validation.Valid;
import org.springframework.stereotype.Service;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
@Service
public class CartServiceImpl implements CartService {

  private CartRepository repository;
  private UserRepository userRepo;
  private ItemService itemService;

  public CartServiceImpl(CartRepository repository, UserRepository userRepo,
      ItemService itemService) {
    this.repository = repository;
    this.userRepo = userRepo;
    this.itemService = itemService;
  }

  @Override
  public List<Item> addCartItemsByCustomerId(String customerId, @Valid Item item) {
    CartEntity entity = getCartByCustomerId(customerId);
    entity.getItems().add(itemService.toEntity(item));
    return itemService.toModelList(repository.save(entity).getItems());
  }

  @Override
  public List<Item> addOrReplaceItemsByCustomerId(String customerId, @Valid Item item) {
    CartEntity entity = getCartByCustomerId(customerId);
    List<ItemEntity> items = Collections.emptyList();
    items.addAll(entity.getItems());
    AtomicReference<Boolean> isItemSaved = new AtomicReference<>(Boolean.FALSE);
    items.forEach(i -> {
      if (i.getProduct().getId().equals(UUID.fromString(item.getId()))) {
        i.setQuantity(item.getQuantity()).setPrice(i.getPrice());
        isItemSaved.set(Boolean.TRUE);
      }
      isItemSaved.set(Boolean.TRUE);
    });
    if (isItemSaved.get()) {
      items.add(itemService.toEntity(item));
    }
    return itemService.toModelList(repository.save(entity).getItems());
  }

  @Override
  public void deleteCart(String customerId) {
    // will throw the error if doesn't exist
    CartEntity entity = getCartByCustomerId(customerId);
    repository.deleteById(entity.getId());
  }

  @Override
  public void deleteItemFromCart(String customerId, String itemId) {
    CartEntity entity = getCartByCustomerId(customerId);
    List<ItemEntity> updatedItems = entity.getItems().stream()
        .filter(i -> !i.getProduct().getId().equals(UUID.fromString(itemId))).collect(toList());
    entity.setItems(updatedItems);
    repository.save(entity);
  }

  @Override
  public CartEntity getCartByCustomerId(String customerId) {
    CartEntity entity = repository.findByCustomerId(customerId).orElse(new CartEntity());
    if (Objects.isNull(entity.getUser())) {
      entity.setUser(userRepo.findById(UUID.fromString(customerId))
          .orElseThrow(() -> new CustomerNotFoundException(
              String.format(" - %s", customerId))));
    }
    return entity;
  }

  @Override
  public List<Item> getCartItemsByCustomerId(String customerId) {
    CartEntity entity = getCartByCustomerId(customerId);
    return itemService.toModelList(entity.getItems());
  }

  @Override
  public Item getCartItemsByItemId(String customerId, String itemId) {
    CartEntity entity = getCartByCustomerId(customerId);
    AtomicReference<ItemEntity> itemEntity = null;
    entity.getItems().forEach(i -> {
      if (i.getProduct().getId().equals(UUID.fromString(itemId))) {
        itemEntity.set(i);
      }
    });
    if (Objects.isNull(itemEntity.get())) {
      getUnsafe().throwException(new ItemNotFoundException(String.format(" - %s", itemId)));
    }
    return itemService.toModel(itemEntity.get());
  }
}
