package com.packt.modern.api.repository;

import static java.util.stream.Collectors.toList;

import com.packt.modern.api.entity.OrderEntity;
import com.packt.modern.api.model.Item;
import com.packt.modern.api.model.NewOrder;
import com.packt.modern.api.model.Order.StatusEnum;
import com.packt.modern.api.service.ItemService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepositoryExt {

  @PersistenceContext
  private EntityManager em;

  private ItemRepository itemRepo;
  private ItemService itemService;

  public OrderRepositoryImpl(EntityManager em, ItemRepository itemRepo, ItemService itemService) {
    this.em = em;
    this.itemRepo = itemRepo;
    this.itemService = itemService;
  }

  @Override
  public Optional<OrderEntity> insert(NewOrder m) {
    // Validate that items are there in order before saving, else throw error
    List<Item> items = m.getItems();
    itemRepo.saveAll(StreamSupport.stream(itemService.toEntityList(items).spliterator(), false)
        .collect(toList()));
    BigDecimal total = BigDecimal.ZERO;
    for (Item i : items) {
      total = (BigDecimal.valueOf(i.getQuantity()).multiply(i.getUnitPrice())).add(total);
    }
    Timestamp orderDate = Timestamp.from(Instant.now());
    em.createNativeQuery("""
        INSERT INTO ecomm.orders (address_id, card_id, customer_id, orderDate, total, status) 
        VALUES(?, ?, ?, ?, ?, ?)
        """)
        .setParameter(1, m.getAddress().getId())
        .setParameter(2, m.getCard().getId())
        .setParameter(3, m.getCustomer().getId())
        .setParameter(4, orderDate)
        .setParameter(5, total)
        .setParameter(6, StatusEnum.CREATED)
        .executeUpdate();
    OrderEntity entity = (OrderEntity) em.createNativeQuery("""
        SELECT o.* FROM ecomm.orders o WHERE o.user_id = ? AND o.orderDate = ?
        """, OrderEntity.class)
        .setParameter(1, m.getCustomer().getId())
        .setParameter(2, orderDate)
        .getSingleResult();
    return Optional.of(entity);
  }

}
