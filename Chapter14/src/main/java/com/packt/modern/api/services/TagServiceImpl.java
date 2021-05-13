package com.packt.modern.api.services;

import com.packt.modern.api.generated.types.Product;
import com.packt.modern.api.generated.types.Tag;
import com.packt.modern.api.generated.types.TagInput;
import com.packt.modern.api.repository.Repository;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter14 - Modern API Development with Spring and Spring Boot
 **/
@Service
public class TagServiceImpl implements TagService {

  private final Repository repository;

  public TagServiceImpl(Repository repository) {
    this.repository = repository;
  }

  @Override
  public Map<String, List<Tag>> getTags(List<String> productIds) {
    return repository.getProductTagMappings(productIds);
  }

  @Override
  public Product addTags(String productId, List<TagInput> tags) {
    return repository.addTags(productId, tags);
  }
}
