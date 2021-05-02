package com.packt.modern.api.client;

import brave.grpc.GrpcTracing;
import brave.rpc.RpcTracing;
import io.grpc.ServerInterceptor;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.sleuth.brave.instrument.grpc.GrpcManagedChannelBuilderCustomizer;
import org.springframework.cloud.sleuth.brave.instrument.grpc.SpringAwareManagedChannelBuilder;
import org.springframework.cloud.sleuth.brave.instrument.grpc.TracingManagedChannelBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter12 - Modern API Development with Spring and Spring Boot
 **/
@Configuration
public class Config {

  @Bean
  public GrpcTracing grpcTracing(RpcTracing rpcTracing) {
    return GrpcTracing.create(rpcTracing);
  }

  // This is wrapper around gRPC's managed channel builder that is spring-aware
  @Bean
  public SpringAwareManagedChannelBuilder managedChannelBuilder(
      Optional<List<GrpcManagedChannelBuilderCustomizer>> customizers) {
    return new SpringAwareManagedChannelBuilder(customizers);
  }

  @Bean
  GrpcManagedChannelBuilderCustomizer tracingManagedChannelBuilderCustomizer(
      GrpcTracing grpcTracing) {
    return new TracingManagedChannelBuilderCustomizer(grpcTracing);
  }
}
