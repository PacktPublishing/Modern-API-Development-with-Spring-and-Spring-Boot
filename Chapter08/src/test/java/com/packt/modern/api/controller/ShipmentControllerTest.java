package com.packt.modern.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packt.modern.api.AppConfig;
import com.packt.modern.api.entity.ShipmentEntity;
import com.packt.modern.api.exception.RestApiErrorHandler;
import com.packt.modern.api.hateoas.ShipmentRepresentationModelAssembler;
import com.packt.modern.api.model.Shipment;
import com.packt.modern.api.service.ShipmentService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter08 - Modern API Development with Spring and Spring Boot
 **/
@ExtendWith(MockitoExtension.class)
public class ShipmentControllerTest {

  private static final String id = "a1b9b31d-e73c-4112-af7c-b68530f38222";
  private MockMvc mockMvc;
  @Mock
  private ShipmentService service;
  @Mock
  private ShipmentRepresentationModelAssembler assembler;
  @Mock
  private MessageSource msgSource;
  @InjectMocks
  private ShipmentController controller;
  private ShipmentEntity entity;
  private Shipment model = new Shipment();

  private JacksonTester<List<Shipment>> shipmentTester;

  @BeforeEach
  public void setup() {
    ObjectMapper mapper = new AppConfig().objectMapper();
    JacksonTester.initFields(this, mapper);
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new
        MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setObjectMapper(mapper);
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(new RestApiErrorHandler(msgSource))
        .setMessageConverters(mappingJackson2HttpMessageConverter)
        //.alwaysDo(print())
        .build();

    final Instant now = Instant.now();
    entity = new ShipmentEntity()
        .setId(UUID.fromString(id))
        .setCarrier("Carrier").setEstDeliveryDate(new Timestamp(now.getEpochSecond() * 1000));
    BeanUtils.copyProperties(entity, model);
    model.setId(entity.getId().toString());
    model.setEstDeliveryDate(entity.getEstDeliveryDate().toLocalDateTime().toLocalDate());
  }

  @Test
  @DisplayName("returns shipments by given order ID")
  public void testGetShipmentByOrderId() throws Exception {
    // given
    given(service.getShipmentByOrderId(id))
        .willReturn(List.of(entity));
    given(assembler.toListModel(List.of(entity)))
        .willReturn(List.of(model));

    // when
    MockHttpServletResponse response = mockMvc.perform(
        get("/api/v1/shipping/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString())
        .isEqualTo(shipmentTester.write(List.of(model)).getJson());
  }
}
