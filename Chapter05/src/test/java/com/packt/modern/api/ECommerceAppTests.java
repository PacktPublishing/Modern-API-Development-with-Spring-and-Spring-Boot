package com.packt.modern.api;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
/*@ComponentScan(basePackages = {"com.packt.modern.api"}, excludeFilters={
		@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value=com.packt.modern.api.H2ConsoleComponent.class)})*/
class ECommerceAppTests {

  private static final Logger log = LoggerFactory.getLogger(ECommerceAppTests.class);

  @Test
  void contextLoads() {
  }

}
