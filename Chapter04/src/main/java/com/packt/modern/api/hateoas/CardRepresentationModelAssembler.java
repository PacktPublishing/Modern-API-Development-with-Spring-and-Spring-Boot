package com.packt.modern.api.hateoas;

import static java.util.stream.Collectors.toList;

import com.packt.modern.api.controller.CardController;
import com.packt.modern.api.entity.CardEntity;
import com.packt.modern.api.model.Card;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
@Component
public class CardRepresentationModelAssembler extends
    RepresentationModelAssemblerSupport<CardEntity, Card> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and
   * resource type.
   */
  public CardRepresentationModelAssembler() {
    super(CardController.class, Card.class);
  }

  /**
   * Coverts the Card entity to resource
   * @param entity
   * @return
   */
  @Override
  public Card toModel(CardEntity entity) {
    String uid = Objects.nonNull(entity.getUser()) ? entity.getUser().getId().toString() : null;
    Card resource = createModelWithId(entity.getId(), entity);
    resource.id(entity.getId().toString()).cardNumber(entity.getNumber())
     .ccv(entity.getCvv()).expires(entity.getExpires()).userId(uid);
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   * @param entities
   * @return
   */
  public List<Card> toListModel(Iterable<CardEntity> entities) {
    if (Objects.isNull(entities)) return Collections.emptyList();
    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e)).collect(toList());
  }

}
