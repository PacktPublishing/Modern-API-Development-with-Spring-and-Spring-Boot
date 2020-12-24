package com.packt.modern.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApp {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ECommerceApp.class);
    app.setWebApplicationType(WebApplicationType.REACTIVE);
    app.run(args);
  }
}