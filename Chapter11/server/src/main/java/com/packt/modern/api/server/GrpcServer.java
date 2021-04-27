package com.packt.modern.api.server;

import com.packt.modern.api.server.interceptor.ExceptionInterceptor;
import com.packt.modern.api.server.service.ChargeService;
import com.packt.modern.api.server.service.SourceService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter11-server - Modern API Development with Spring and Spring Boot
 **/
@Component
public class GrpcServer {

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Value("${grpc.port:8080}")
  private int port;

  private Server server;

  private ChargeService chargeService;
  private SourceService sourceService;
  private ExceptionInterceptor exceptionInterceptor;

  public GrpcServer(SourceService sourceService, ChargeService chargeService, ExceptionInterceptor exceptionInterceptor) {
    this.sourceService = sourceService;
    this.chargeService = chargeService;
    this.exceptionInterceptor = exceptionInterceptor;
  }

  public void start() throws IOException, InterruptedException {
    LOG.info("gRPC server is starting on port: {}.", port);
    server = ServerBuilder.forPort(port)
        .addService(sourceService).addService(chargeService)
        .intercept(exceptionInterceptor)
        .build().start();
    LOG.info("gRPC server started and listening on port: {}.", port);
    LOG.info("Following service are available: ");
    server.getServices().stream()
        .forEach(s -> LOG.info("Service Name: {}", s.getServiceDescriptor().getName()));
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      LOG.info("Shutting down gRPC server.");
      GrpcServer.this.stop();
      LOG.info("gRPC server shut down successfully.");
    }));
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  public void block() throws InterruptedException {
    if (server != null) {
      // received the request until application is terminated
      server.awaitTermination();
    }
  }
}
