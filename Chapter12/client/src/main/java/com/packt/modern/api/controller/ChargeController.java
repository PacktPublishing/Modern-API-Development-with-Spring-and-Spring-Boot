package com.packt.modern.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.packt.modern.api.client.GrpcClient;
import com.packt.modern.api.grpc.v1.CustomerId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter12 - Modern API Development with Spring and Spring Boot
 **/
@RestController
public class ChargeController {

  private Logger LOG = LoggerFactory.getLogger(getClass());
  private GrpcClient client;

  public ChargeController(GrpcClient client) {
    this.client = client;
  }

  @GetMapping("/charges")
  public String getSources(@RequestParam(defaultValue = "ab1ab2ab3ab4ab5") String customerId)
      throws InvalidProtocolBufferException {
    LOG.info("CustomerId : {}", customerId);
    var req = CustomerId.newBuilder().setId(customerId).build();
    CustomerId.Response resp = client.getChargeServiceStub().retrieveAll(req);
    var printer = JsonFormat.printer().includingDefaultValueFields();
    LOG.info("Server response received in Json Format: {}", resp);
    return printer.print(resp);
  }
}
