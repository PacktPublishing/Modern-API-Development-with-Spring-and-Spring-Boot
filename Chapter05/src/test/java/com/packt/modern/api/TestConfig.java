package com.packt.modern.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Configuration
public class TestConfig {
  @Bean
  public ServerCodecConfigurer serverCodecConfigurer() {
    return ServerCodecConfigurer.create();
  }
}
