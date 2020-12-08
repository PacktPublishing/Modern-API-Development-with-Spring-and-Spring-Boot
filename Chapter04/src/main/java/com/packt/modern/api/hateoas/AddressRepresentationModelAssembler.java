package com.packt.modern.api.hateoas;

import static java.util.stream.Collectors.toList;

import com.packt.modern.api.controller.AddressController;
import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.model.Address;
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
public class AddressRepresentationModelAssembler extends
    RepresentationModelAssemblerSupport<AddressEntity, Address> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and
   * resource type.
   */
  public AddressRepresentationModelAssembler() {
    super(AddressController.class, Address.class);
  }

  /**
   * Coverts the Address entity to resource
   * @param entity
   * @return
   */
  @Override
  public Address toModel(AddressEntity entity) {
    Address resource = createModelWithId(entity.getId(), entity);
    resource.setId(entity.getId().toString());
    resource.setNumber(entity.getNumber());
    resource.setResidency(entity.getResidency());
    resource.setStreet(entity.getStreet());
    resource.setCity(entity.getCity());
    resource.setState(entity.getState());
    resource.setCountry(entity.getCountry());
    resource.setPincode(entity.getPincode());
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   * @param entities
   * @return
   */
  public List<Address> toListModel(Iterable<AddressEntity> entities) {
    if (Objects.isNull(entities)) return Collections.emptyList();
    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e)).collect(toList());
  }

}
