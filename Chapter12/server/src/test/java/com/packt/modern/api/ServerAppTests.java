package com.packt.modern.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.packt.modern.api.grpc.v1.CreateSourceReq;
import com.packt.modern.api.grpc.v1.SourceId;
import com.packt.modern.api.grpc.v1.SourceServiceGrpc;
import com.packt.modern.api.server.GrpcServer;
import com.packt.modern.api.server.GrpcServerRunner;
import com.packt.modern.api.server.interceptor.ExceptionInterceptor;
import com.packt.modern.api.server.service.ChargeService;
import com.packt.modern.api.server.service.SourceService;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import java.io.IOException;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ServerAppTests {

  /**
   * This rule manages automatic graceful shutdown for the registered servers and channels at the
   * end of test.
   */
  @Rule
  private final static GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();
  private static SourceServiceGrpc.SourceServiceBlockingStub blockingStub;
  private static String newlyCreatedSourceId = null;
  @Autowired
  private ApplicationContext context;

  /**
   * To test the server, make calls with a real stub using the in-process channel, and verify
   * behaviors or state changes from the client side.
   */
  @BeforeAll
  public static void setup(@Autowired SourceService sourceService,
      @Autowired ChargeService chargeService, @Autowired ExceptionInterceptor exceptionInterceptor)
      throws IOException {
    // Generate a unique in-process server name.
    String serverName = InProcessServerBuilder.generateName();

    // Create a server, add service, start, and register for automatic graceful shutdown.
    grpcCleanup.register(InProcessServerBuilder
        .forName(serverName).directExecutor().addService(sourceService)
        .intercept(exceptionInterceptor)
        .build().start());

    blockingStub = SourceServiceGrpc.newBlockingStub(
        // Create a client channel and register for automatic graceful shutdown.
        grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));
  }

  @Test
  @Order(1)
  void beanGrpcServerRunnerTest() {
    assertNotNull(context.getBean(GrpcServer.class));
    assertThrows(NoSuchBeanDefinitionException.class,
        () -> context.getBean(GrpcServerRunner.class),
        "GrpcServerRunner should not be loaded during test");
  }

  @Test
  @Order(2)
  @DisplayName("Creates the source object using create RPC call")
  public void SourceService_Create() {
    CreateSourceReq.Response response =
        blockingStub.create(CreateSourceReq.newBuilder().setAmount(100).setCurrency("USD").build());
    assertNotNull(response);
    assertNotNull(response.getSource());
    newlyCreatedSourceId = response.getSource().getId();
    assertEquals(100, response.getSource().getAmount());
    assertEquals("USD", response.getSource().getCurrency());
  }

  @Test
  @Order(3)
  @DisplayName("Throws the exception when invalid source id is passed to retrieve RPC call")
  public void SourceService_RetrieveForInvalidId() {
    Throwable throwable = assertThrows(
        StatusRuntimeException.class,
        () -> blockingStub.retrieve(SourceId.newBuilder().setId("").build()));
    assertEquals("INVALID_ARGUMENT: Invalid Source ID is passed.", throwable.getMessage());
  }

  @Test
  @Order(4)
  @DisplayName("Retrieves the source object created using create RPC call")
  public void SourceService_Retrieve() {
    SourceId.Response response =
        blockingStub.retrieve(SourceId.newBuilder().setId(newlyCreatedSourceId).build());
    assertNotNull(response);
    assertNotNull(response.getSource());
    assertEquals(100, response.getSource().getAmount());
    assertEquals("USD", response.getSource().getCurrency());
  }
}
