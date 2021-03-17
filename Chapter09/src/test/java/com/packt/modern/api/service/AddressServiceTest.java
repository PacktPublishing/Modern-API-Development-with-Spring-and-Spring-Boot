package com.packt.modern.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.exception.ResourceNotFoundException;
import com.packt.modern.api.model.AddAddressReq;
import com.packt.modern.api.repository.AddressRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter09 - Modern API Development with Spring and Spring Boot
 **/
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

  private final static String id = "a1b9b31d-e73c-4112-af7c-b68530f38222";
  private final static String nonExistId = "a1b9b31d-e73c-4112-af7c-b68530f38220";
  private static AddressEntity entity;
  private static AddAddressReq addAddressReq;
  @Mock
  private AddressRepository repository;
  @InjectMocks
  private AddressServiceImpl service;

  @BeforeAll
  public static void setup() {
    entity = new AddressEntity()
        .setId(UUID.fromString(id))
        .setNumber("111").setResidency("Residency").setStreet("Street")
        .setCity("City").setState("State").setCountry("Country").setPincode("12345");
    addAddressReq = new AddAddressReq().id(entity.getId().toString()).number(entity.getNumber())
        .residency(entity.getResidency()).street(entity.getStreet()).city(entity.getCity())
        .state(entity.getState()).country(entity.getCountry()).pincode(entity.getPincode());
  }

  @Test
  @DisplayName("returns an AddressEntity when private method toEntity() is called with Address model")
  public void convertModelToEntity() {
    // given
    AddressServiceImpl srvc = new AddressServiceImpl(repository);
    // when
    AddressEntity e = ReflectionTestUtils.invokeMethod(srvc, "toEntity", addAddressReq);
    // then
    then(e).as("Check address entity is returned and not null").isNotNull();
    then(e.getNumber()).as("Check house/flat number is set").isEqualTo(entity.getNumber());
    then(e.getResidency()).as("Check residency is set").isEqualTo(entity.getResidency());
    then(e.getStreet()).as("Check street is set").isEqualTo(entity.getStreet());
    then(e.getCity()).as("Check city is set").isEqualTo(entity.getCity());
    then(e.getState()).as("Check state is set").isEqualTo(entity.getState());
    then(e.getCountry()).as("Check country is set").isEqualTo(entity.getCountry());
    then(e.getPincode()).as("Check pincode is set").isEqualTo(entity.getPincode());
  }

  @Test
  @DisplayName("save a new address")
  public void createAddress() {
    given(repository.save(any())).willReturn(entity);
    // when
    Optional<AddressEntity> result = service.createAddress(addAddressReq);
    // then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result.get()).isEqualTo(entity);
  }

  @Test
  @DisplayName("return address by the given existing id")
  public void getAddressesByIdWhenExists() {
    given(repository.findById(UUID.fromString(id))).willReturn(Optional.of(entity));
    // when
    Optional<AddressEntity> result = service.getAddressesById(id);
    // then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result.get()).isEqualTo(entity);
  }

  @Test
  @DisplayName("return empty address by the given non-existing id")
  public void getAddressesByIdWhenNotExists() {
    given(repository.findById(UUID.fromString(nonExistId)))
        .willReturn(Optional.empty());
    // when
    Optional<AddressEntity> result = service.getAddressesById(nonExistId);
    // then
    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("delete address by given existing id")
  public void deleteAddressesByIdWhenExists() {
    given(repository.findById(UUID.fromString(nonExistId)))
        .willReturn(Optional.of(entity));
    willDoNothing().given(repository).deleteById(UUID.fromString(nonExistId));
    // when
    service.deleteAddressesById(nonExistId);
    // then
    verify(repository, times(1)).findById(UUID.fromString(nonExistId));
    verify(repository, times(1)).deleteById(UUID.fromString(nonExistId));
  }

  @Test
  @DisplayName("delete address by given non-existing id, should throw ResourceNotFoundException")
  public void deleteAddressesByNonExistId() throws Exception {
    given(repository.findById(UUID.fromString(nonExistId))).willReturn(Optional.empty())
        .willThrow(new ResourceNotFoundException(
            String.format("No Address found with id %s.", nonExistId)));
    // when
    try {
      service.deleteAddressesById(nonExistId);
    } catch (Exception ex) {
      // then
      assertThat(ex).isInstanceOf(com.packt.modern.api.exception.ResourceNotFoundException.class);
      assertThat(ex.getMessage()).contains("No Address found with id " + nonExistId);
    }
    // then
    verify(repository, times(1)).findById(UUID.fromString(nonExistId));
    verify(repository, times(0)).deleteById(UUID.fromString(nonExistId));
  }

  @Test
  @DisplayName("return all addresses")
  public void getAllAddress() {
    given(repository.findAll()).willReturn(List.of(entity));
    // when
    Iterable<AddressEntity> result = service.getAllAddresses();
    // then
    assertThat(result).isNotNull();
    assertThat(result).contains(entity);
  }
}
