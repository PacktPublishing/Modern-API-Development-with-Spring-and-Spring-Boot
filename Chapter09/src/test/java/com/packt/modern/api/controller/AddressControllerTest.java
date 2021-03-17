package com.packt.modern.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.packt.modern.api.AppConfig;
import com.packt.modern.api.entity.AddressEntity;
import com.packt.modern.api.exception.ResourceNotFoundException;
import com.packt.modern.api.exception.RestApiErrorHandler;
import com.packt.modern.api.hateoas.AddressRepresentationModelAssembler;
import com.packt.modern.api.model.AddAddressReq;
import com.packt.modern.api.model.Address;
import com.packt.modern.api.service.AddressService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter09 - Modern API Development with Spring and Spring Boot
 **/
@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {

  private static final String URI = "/api/v1/addresses";
  private static final String id = "a1b9b31d-e73c-4112-af7c-b68530f38222";
  private static MockMvc mockMvc;
  private static AddressService service = mock(AddressService.class);
  private static MessageSource msgSource = mock(MessageSource.class);
  private static AddressController controller;
  private static AddressEntity entity;
  private static Address address;
  private static AddAddressReq addAddressReq;
  private JacksonTester<Address> addressJackson;
  private JacksonTester<AddAddressReq> addAddressReqJackson;

  @BeforeAll
  public static void setup() {
    controller = new AddressController(service, new AddressRepresentationModelAssembler());
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(new RestApiErrorHandler(msgSource))
        .alwaysDo(print())
        .build();

    entity = new AddressEntity()
        .setId(UUID.fromString(id))
        .setNumber("111").setResidency("Residency").setStreet("Street")
        .setCity("City").setState("State").setCountry("Country").setPincode("12345");
    addAddressReq = new AddAddressReq().id(entity.getId().toString()).number(entity.getNumber())
        .residency(entity.getResidency()).street(entity.getStreet()).city(entity.getCity())
        .state(entity.getState()).country(entity.getCountry()).pincode(entity.getPincode());
    address = new Address().id(entity.getId().toString()).number(entity.getNumber())
        .residency(entity.getResidency()).street(entity.getStreet()).city(entity.getCity())
        .state(entity.getState()).country(entity.getCountry()).pincode(entity.getPincode());
  }

  @BeforeEach
  public void configureJsonTester() {
    JacksonTester.initFields(this, new AppConfig().objectMapper());
  }

  @Test
  @DisplayName("returns address by given existing ID")
  public void getAddressByOrderIdWhenExists() throws Exception {
    given(service.getAddressesById(id)).willReturn(Optional.of(entity));

    // when
    ResultActions result = mockMvc.perform(
        get("/api/v1/addresses/a1b9b31d-e73c-4112-af7c-b68530f38222")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    // then
    result.andExpect(status().isOk());
    verifyJson(result);
  }

  private void verifyJson(final ResultActions result) throws Exception {
    final String BASE_PATH = "http://localhost";
    result
        .andExpect(jsonPath("id", is(entity.getId().toString())))
        .andExpect(jsonPath("number", is(entity.getNumber())))
        .andExpect(jsonPath("residency", is(entity.getResidency())))
        .andExpect(jsonPath("street", is(entity.getStreet())))
        .andExpect(jsonPath("city", is(entity.getCity())))
        .andExpect(jsonPath("state", is(entity.getState())))
        .andExpect(jsonPath("country", is(entity.getCountry())))
        .andExpect(jsonPath("pincode", is(entity.getPincode())))
        .andExpect(jsonPath("links[0].rel", is("self")))
        .andExpect(jsonPath("links[0].href", is(BASE_PATH + "/" + entity.getId())))
        .andExpect(jsonPath("links[1].rel", is("self")))
        .andExpect(
            jsonPath("links[1].href", is(BASE_PATH + URI + "/" + entity.getId())));
  }

  @Test
  @DisplayName("returns NOT FOUND response when non-existing ID is used for fetching address")
  public void getAddressByOrderIdWhenNotExists() throws Exception {
    given(service.getAddressesById("a1b9b31d-e73c-4112-af7c-b68530f38199"))
        .willReturn(Optional.empty());

    // when
    MockHttpServletResponse response = mockMvc.perform(
        get("/api/v1/addresses/a1b9b31d-e73c-4112-af7c-b68530f38199")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    assertThat(response.getContentAsString()).isEmpty();
  }

  @Test
  @DisplayName("returns newly created address with CREATED status")
  public void createAddress() throws Exception {
    given(service.createAddress(addAddressReq)).willReturn(Optional.of(entity));

    // when
    ResultActions result = mockMvc.perform(
        post("/api/v1/addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(addAddressReqJackson.write(addAddressReq).getJson())
            .accept(MediaType.APPLICATION_JSON));

    // then
    result.andExpect(status().isCreated());
    verifyJson(result);
  }

  @Test
  @DisplayName("returns all address")
  public void getAllAddresses() throws Exception {
    given(service.getAllAddresses()).willReturn(List.of(entity));

    // when
    ResultActions result = mockMvc.perform(
        get("/api/v1/addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    // then
    result.andExpect(status().isOk());
    result.andExpect(jsonPath("$", hasSize(1)));
    result.andExpect(jsonPath("$[0].id", is(id)));
  }

  @Test
  @DisplayName("delete an address by given existing id")
  public void deleteAddressByIdWhenExists() throws Exception {
    // given
    willDoNothing().given(service).deleteAddressesById(id);

    // when
    ResultActions result = mockMvc.perform(
        delete("/api/v1/addresses/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    // then
    verify(service, times(1)).deleteAddressesById(id);
    result.andExpect(status().isAccepted());
  }

  @Test
  @DisplayName("delete an address by given non-existing id will throw the exception")
  public void deleteAddressByIdWhenNotExists() throws Exception {
    // given
    final String nonExistId = "abc";
    willThrow(
        new ResourceNotFoundException(String.format("No Address found with id %s.", nonExistId)))
        .willDoNothing().given(service).deleteAddressesById(nonExistId);

    // when
    ResultActions result = mockMvc.perform(
        delete("/api/v1/addresses/{id}", nonExistId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    // then
    verify(service, times(1)).deleteAddressesById(nonExistId);
    result.andExpect(status().isNotFound());
    result.andExpect(jsonPath("errorCode", is("PACKT-0010")));
    result.andExpect(jsonPath("message", is("Requested resource not found. No Address found with id abc.")));
    result.andExpect(jsonPath("url", is("http://localhost/api/v1/addresses/abc")));
    result.andExpect(jsonPath("reqMethod", is("DELETE")));
    result.andExpect(jsonPath("timestamp", Matchers.isA(BigDecimal.class)));
  }
}
