package com.packt.modern.api.controller;

import static com.packt.modern.api.TestUtils.isTokenExpired;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.packt.modern.api.AuthClient;
import com.packt.modern.api.TestUtils;
import com.packt.modern.api.model.AddAddressReq;
import com.packt.modern.api.model.Address;
import com.packt.modern.api.model.SignedInUser;
import com.packt.modern.api.repository.AddressRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter08 - Modern API Development with Spring and Spring Boot
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class AddressControllerIT {

  private static ObjectMapper objectMapper;
  private static AuthClient authClient;
  private static SignedInUser signedInUser;
  private static Address address;
  private static String idOfAddressToBeRemoved;
  @Autowired
  private AddressRepository repository;
  @Autowired
  private TestRestTemplate restTemplate;

  @BeforeAll
  public static void init() {
    objectMapper = TestUtils.objectMapper();
    address = new Address().id("a731fda1-aaad-42ea-bdbc-a27eeebe2cc0").number("9I-999")
        .residency("Fraser Suites Le Claridge").street("Champs-Elysees")
        .city("Paris").state("Île-de-France").country("France").pincode("75008");
  }

  @BeforeEach
  public void setup() throws JsonProcessingException {
    if (Objects.isNull(signedInUser) || Strings.isNullOrEmpty(signedInUser.getAccessToken())
        || isTokenExpired(signedInUser.getAccessToken())) {
      authClient = new AuthClient(restTemplate, objectMapper);
      signedInUser = authClient.login("scott", "tiger");
    }
  }

  @Test
  @DisplayName("returns UNAUTHORIZED when address is fetched by ID without login")
  @Order(1)
  public void getAddressByIdWithoutLogin() {
    // given
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

    // when
    ResponseEntity<String> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses/a731fda1-aaad-42ea-bdbc-a27eeebe2cc0",
            HttpMethod.GET, new HttpEntity<>(headers), String.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  @DisplayName("returns address by given existing ID")
  @Order(2)
  public void getAddressByIdWhenExists() throws IOException {
    // given
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    headers.add("Authorization", "Bearer " + signedInUser.getAccessToken());

    // when
    ResponseEntity<JsonNode> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses/a731fda1-aaad-42ea-bdbc-a27eeebe2cc0",
            HttpMethod.GET, new HttpEntity<>(headers), JsonNode.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    JsonNode node = addressResponseEntity.getBody();
    // a hack to change the _links to links
    ObjectNode object = (ObjectNode) node;
    object.set("links", node.get("_links"));
    object.remove("_links");
    Address addressFromResponse = objectMapper.convertValue(node, new TypeReference<>() {
    });
    assertThat(addressFromResponse).hasFieldOrProperty("links");
    assertThat(addressFromResponse).isEqualTo(address);
  }

  @Test
  @DisplayName("returns NOT FOUND response when non-existing ID is used for fetching address")
  @Order(3)
  public void getAddressByIdWhenNotExists() throws IOException {
    // given
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add("Authorization", "Bearer " + signedInUser.getAccessToken());

    // when
    ResponseEntity<String> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses/a731fda1-aaad-42ea-bdbc-a27eeebe2cc1",
            HttpMethod.GET, new HttpEntity<>(headers), String.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(addressResponseEntity.getBody()).isNull();
  }

  @Test
  @DisplayName("returns BAD REQUEST response when invalid UUID is used for fetching address")
  @Order(4)
  public void getAddressByInvalidUUID() throws IOException {
    // given
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add("Authorization", "Bearer " + signedInUser.getAccessToken());
    String id = "a731fda1";

    // when
    ResponseEntity<String> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses/" + id, HttpMethod.GET,
            new HttpEntity<>(headers), String.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(addressResponseEntity.getBody()).contains(
        "{\"errorCode\":\"PACKT-0009\",\"message\":\"Invalid data passed. Invalid UUID string: "
            + id);
  }

  @Test
  @DisplayName("returns UNAUTHORIZED when all addresses are fetched without login")
  @Order(5)
  public void getAllAddressWithoutLogin() {
    // given
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

    // when
    ResponseEntity<String> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses", HttpMethod.GET, new HttpEntity<>(headers), String.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  @DisplayName("returns all addresses")
  @Order(6)
  public void getAllAddress() throws IOException {
    // given
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    headers.add("Authorization", "Bearer " + signedInUser.getAccessToken());

    // when
    ResponseEntity<JsonNode> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses", HttpMethod.GET,
            new HttpEntity<>(headers), JsonNode.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    JsonNode node = addressResponseEntity.getBody();
    List<Address> addressFromResponse = objectMapper
        .convertValue(node, new TypeReference<ArrayList<Address>>() {
        });
    assertThat(addressFromResponse).hasSizeGreaterThan(0);
    assertThat(addressFromResponse.get(0)).hasFieldOrProperty("links");
    assertThat(addressFromResponse.get(0)).isInstanceOf(Address.class);
  }

  @Test
  @DisplayName("returns FORBIDDEN response when non-admin user is used for deleting an address")
  @Order(7)
  public void deleteAddressByIdWithNonAdmin() throws IOException {
    // given
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add("Authorization", "Bearer " + signedInUser.getAccessToken());
    String id = "a731fda1";

    // when
    ResponseEntity<String> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses/" + id, HttpMethod.DELETE,
            new HttpEntity<>(headers), String.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    assertThat(addressResponseEntity.getBody()).contains(
        "{\"errorCode\":\"PACKT-0014\",\"message\":\"Access Denied. Access is denied");
  }

  @Test
  @DisplayName("returns BAD REQUEST response when invalid UUID is used for deleting an address")
  @Order(8)
  public void deleteAddressByInvalidUUID() throws IOException {
    // given
    SignedInUser adminUser = authClient.login("scott2", "tiger");
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add("Authorization", "Bearer " + adminUser.getAccessToken());
    String id = "a731fda1";

    // when
    ResponseEntity<String> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses/" + id, HttpMethod.DELETE,
            new HttpEntity<>(headers), String.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(addressResponseEntity.getBody()).contains(
        "{\"errorCode\":\"PACKT-0009\",\"message\":\"Invalid data passed. Invalid UUID string: "
            + id);
  }

  @Test
  @DisplayName("add a new address without login")
  @Order(9)
  public void addAddressWithoutLogin() throws IOException {
    // given
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    AddAddressReq newAdd = new AddAddressReq().number("111").residency("Residency").street("Street")
        .city("City").state("State").country("Country").pincode("12345");
    HttpEntity<AddAddressReq> request = new HttpEntity<>(newAdd, headers);

    // when
    ResponseEntity<String> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses", HttpMethod.POST, request, String.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  @DisplayName("Returns FORBIDDEN when non-admin role user add an address")
  @Order(10)
  public void addAddressWithNonAdminRole() throws IOException {
    // given
    Address expectedAddress = new Address().number("111").residency("Residency").street("Street")
        .city("City")
        .state("State").country("Country").pincode("Pincode");
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add("Authorization", "Bearer " + signedInUser.getAccessToken());
    AddAddressReq newAdd = new AddAddressReq();
    BeanUtils.copyProperties(expectedAddress, newAdd);
    HttpEntity<AddAddressReq> request = new HttpEntity<>(newAdd, headers);

    // when
    ResponseEntity<String> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses", HttpMethod.POST, request, String.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
  }

  @Test
  @DisplayName("add a new address")
  @Order(11)
  public void addAddress() throws IOException {
    // given
    SignedInUser adminUser = authClient.login("scott2", "tiger");
    Address expectedAddress = new Address().number("111").residency("Residency").street("Street")
        .city("City")
        .state("State").country("Country").pincode("Pincode");
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add("Authorization", "Bearer " + adminUser.getAccessToken());
    AddAddressReq newAdd = new AddAddressReq();
    BeanUtils.copyProperties(expectedAddress, newAdd);
    HttpEntity<AddAddressReq> request = new HttpEntity<>(newAdd, headers);

    // when
    ResponseEntity<JsonNode> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses", HttpMethod.POST, request, JsonNode.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    JsonNode node = addressResponseEntity.getBody();
    // a hack to change the _links to links
    ObjectNode object = (ObjectNode) node;
    object.set("links", node.get("_links"));
    object.remove("_links");
    Address addressFromResponse = objectMapper.convertValue(node, new TypeReference<>() {
    });
    assertThat(addressFromResponse).hasFieldOrProperty("links");
    idOfAddressToBeRemoved = addressFromResponse.getId();
    expectedAddress.id(idOfAddressToBeRemoved);
    assertThat(addressFromResponse).isEqualTo(expectedAddress);
  }

  @Test
  @DisplayName("remove address created in previous test")
  @Order(12)
  public void deleteAddressByID() throws IOException {
    // given
    SignedInUser adminUser = authClient.login("scott2", "tiger");
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add("Authorization", "Bearer " + adminUser.getAccessToken());
    // when
    ResponseEntity<String> addressResponseEntity = restTemplate
        .exchange("/api/v1/addresses/" + idOfAddressToBeRemoved, HttpMethod.DELETE,
            new HttpEntity<>(headers), String.class);

    // then
    assertThat(addressResponseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
  }
}
