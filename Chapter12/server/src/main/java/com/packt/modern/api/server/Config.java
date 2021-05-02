package com.packt.modern.api.server;

import brave.grpc.GrpcTracing;
import brave.rpc.RpcTracing;
import io.grpc.ServerInterceptor;
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
}
