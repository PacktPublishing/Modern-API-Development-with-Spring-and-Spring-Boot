package com.packt.modern.api.repository;

import static java.util.stream.Collectors.toList;

import com.packt.modern.api.entity.CartEntity;
import com.packt.modern.api.entity.ItemEntity;
import com.packt.modern.api.entity.OrderEntity;
import com.packt.modern.api.entity.OrderItemEntity;
import com.packt.modern.api.exception.ResourceNotFoundException;
import com.packt.modern.api.model.Cart;
import com.packt.modern.api.model.NewOrder;
import com.packt.modern.api.model.Order.StatusEnum;
import com.packt.modern.api.service.ItemService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter08 - Modern API Development with Spring and Spring Boot
 **/
@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepositoryExt {

  @PersistenceContext
  private EntityManager em;

  private ItemRepository itemRepo;
  private AddressRepository aRepo;
  private CartRepository cRepo;
  private OrderItemRepository oiRepo;
  private ItemService itemService;

  public OrderRepositoryImpl(EntityManager em, ItemRepository itemRepo, AddressRepository aRepo,
      CartRepository cRepo, OrderItemRepository oiRepo, ItemService itemService) {
    this.em = em;
    this.itemRepo = itemRepo;
    this.aRepo = aRepo;
    this.cRepo = cRepo;
    this.oiRepo = oiRepo;
    this.itemService = itemService;
  }

  @Override
  public Optional<OrderEntity> insert(NewOrder m) {
    // Items are already in cart and saved in db when user places the order
    // Here, you can also populate the other Order details like address, card etc.
    Iterable<ItemEntity> dbItems = itemRepo.findByCustomerId(m.getCustomerId());
    List<ItemEntity> items = StreamSupport.stream(dbItems.spliterator(), false).collect(toList());
    if (items.size() < 1) {
      throw new ResourceNotFoundException(String
          .format("There is no item found in customer's (ID: %s) cart.", m.getCustomerId()));
    }
    BigDecimal total = BigDecimal.ZERO;
    for (ItemEntity i : items) {
      total = (BigDecimal.valueOf(i.getQuantity()).multiply(i.getPrice())).add(total);
    }
    Timestamp orderDate = Timestamp.from(Instant.now());
    em.createNativeQuery("""
        INSERT INTO ecomm.orders (address_id, card_id, customer_id, order_date, total, status) 
        VALUES(?, ?, ?, ?, ?, ?)
        """)
        .setParameter(1, m.getAddress().getId())
        .setParameter(2, m.getCard().getId())
        .setParameter(3, m.getCustomerId())
        .setParameter(4, orderDate)
        .setParameter(5, total)
        .setParameter(6, StatusEnum.CREATED.getValue())
        .executeUpdate();
    Optional<CartEntity> oCart = cRepo.findByCustomerId(UUID.fromString(m.getCustomerId()));
    CartEntity cart = oCart.orElseThrow(() -> new ResourceNotFoundException(String.format("Cart not found for given customer (ID: %s)", m.getCustomerId())));
    itemRepo.deleteCartItemJoinById(cart.getItems().stream().map(i -> i.getId()).collect(toList()), cart.getId());
    OrderEntity entity = (OrderEntity) em.createNativeQuery("""
        SELECT o.* FROM ecomm.orders o WHERE o.customer_id = ? AND o.order_date >= ?
        """, OrderEntity.class)
        .setParameter(1, m.getCustomerId())
        .setParameter(2, OffsetDateTime.ofInstant(orderDate.toInstant(), ZoneId.of("Z")).truncatedTo(
            ChronoUnit.MICROS))
        .getSingleResult();
    oiRepo.saveAll(cart.getItems().stream().map(i -> new OrderItemEntity()
        .setOrderId(entity.getId()).setItemId(i.getId())).collect(toList()));
    return Optional.of(entity);
  }
}
