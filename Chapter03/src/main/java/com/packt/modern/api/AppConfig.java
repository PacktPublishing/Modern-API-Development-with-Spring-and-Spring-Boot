package com.packt.modern.api;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter03 - Modern API Development with Spring and Spring Boot
 * @created : 11/10/2020, Tuesday
 **/
public class AppConfig implements WebMvcConfigurer {

/*  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.defaultContentType(MediaType.APPLICATION_JSON).
        mediaType("xml", MediaType.APPLICATION_XML).
        mediaType("json", MediaType.APPLICATION_JSON)
  }*/
}
