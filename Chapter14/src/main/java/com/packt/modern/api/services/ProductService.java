package com.packt.modern.api.services;

import com.packt.modern.api.generated.types.Product;
import com.packt.modern.api.generated.types.ProductCriteria;
import java.util.List;
import org.reactivestreams.Publisher;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter14 - Modern API Development with Spring and Spring Boot
 **/
public interface ProductService {

  Product getProduct(String id);

  List<Product> getProducts(ProductCriteria criteria);

  Product addQuantity(String productId, int qty);

  Publisher<Product> gerProductPublisher();
}
