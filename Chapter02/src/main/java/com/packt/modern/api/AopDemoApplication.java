package com.packt.modern.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author  : github.com/sharmasourabh
 * @project : Chapter02 - Modern API Development with Spring and Spring Boot
 * @created : 10/20/2020, Tuesday
 * Usage    : Demonstrate the usage of AOP for around advice
 **/
@SpringBootApplication
public class AopDemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(AopDemoApplication.class, args);
    SortUtil aopTest = context.getBean(SortUtil.class);

    final List<Integer> list = new ArrayList<>(Arrays.asList(99, 7, 78, 10, 17, 8, 19, 91, 15, 1));

    aopTest.bubbleSort(list.stream().mapToInt(Integer::intValue).toArray());
    aopTest.insertionSort(list.stream().mapToInt(Integer::intValue).toArray());
    aopTest.selectionSort(list.stream().mapToInt(Integer::intValue).toArray());
    aopTest.mergeSort(list.stream().mapToInt(Integer::intValue).toArray());
    aopTest.quickSort(list.stream().mapToInt(Integer::intValue).toArray());
    System.exit(0);
  }
}