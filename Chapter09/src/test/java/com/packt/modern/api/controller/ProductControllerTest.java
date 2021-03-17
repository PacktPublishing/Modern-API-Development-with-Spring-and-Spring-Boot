package com.packt.modern.api.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packt.modern.api.entity.ProductEntity;
import com.packt.modern.api.exception.RestApiErrorHandler;
import com.packt.modern.api.hateoas.ProductRepresentationModelAssembler;
import com.packt.modern.api.model.Product;
import com.packt.modern.api.service.ProductService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter09 - Modern API Development with Spring and Spring Boot
 **/
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

  private MockMvc mockMvc;
  @Mock
  private WebApplicationContext wac;
  @Mock
  private ProductService service;
  @Mock
  private MessageSource msgSource;

  private ProductController controller;

  private ProductEntity entity;
  private static final String URI = "/api/v1/products";

  private JacksonTester<Product> shipmentTester;

  @BeforeEach
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
    controller = new ProductController(service, new ProductRepresentationModelAssembler());
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(new RestApiErrorHandler(msgSource))
        .build();

    entity = new ProductEntity()
        .setId(UUID.fromString("a1b9b31d-e73c-4112-af7c-b68530f38222"))
        .setName("Name").setDescription("Description").setImageUrl("/images/image.jpeg").setPrice(
            new BigDecimal(20.99).setScale(2, RoundingMode.HALF_DOWN)).setCount(100);
  }

  @Test
  @DisplayName("returns product by given ID")
  public void getProduct() throws Exception {
    given(service.getProduct("a1b9b31d-e73c-4112-af7c-b68530f38222"))
        .willReturn(Optional.of(entity));

    // when
    mockMvc.getDispatcherServlet().getHandlerMappings();
    ResultActions result = mockMvc.perform(
        get("/api/v1/products/{id}", "a1b9b31d-e73c-4112-af7c-b68530f38222")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    // then
    result.andExpect(status().isOk());
    verifyJson(result);
  }

  private void verifyJson(final ResultActions result) throws Exception {
    final String BASE_PATH = "http://localhost";
    result
        .andExpect(jsonPath("id", is(entity.getId().toString())))
        .andExpect(jsonPath("name", is(entity.getName())))
        .andExpect(jsonPath("description", is(entity.getDescription())))
        .andExpect(jsonPath("imageUrl", is(entity.getImageUrl())))
        .andExpect(jsonPath("price", is(entity.getPrice().doubleValue())))
        .andExpect(jsonPath("count", is(entity.getCount())))
        .andExpect(jsonPath("links[0].rel", is("self")))
        .andExpect(jsonPath("links[0].href", is(BASE_PATH + "/" + entity.getId())))
        .andExpect(jsonPath("links[1].rel", is("self")))
        .andExpect(
            jsonPath("links[1].href", is(BASE_PATH + URI + "/" + entity.getId())))
        .andExpect(jsonPath("links[2].rel", is("products")))
        .andExpect(
            jsonPath("links[2].href",
                is(BASE_PATH + URI + "?page=1&size=10{&tag,name}")));
  }
}
