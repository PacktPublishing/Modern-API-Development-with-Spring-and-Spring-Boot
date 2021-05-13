package com.packt.modern.api.repository;

import com.packt.modern.api.generated.types.Product;
import com.packt.modern.api.generated.types.Tag;
import com.packt.modern.api.generated.types.TagInput;
import java.util.List;
import java.util.Map;
import org.reactivestreams.Publisher;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter14 - Modern API Development with Spring and Spring Boot
 **/
public interface Repository {

  Product getProduct(String id);

  List<Product> getProducts();

  Map<String, List<Tag>> getProductTagMappings(List<String> productIds);

  Product addTags(String productId, List<TagInput> tags);

  Product addQuantity(String productId, int qty);

  Publisher<Product> getProductPublisher();
}
