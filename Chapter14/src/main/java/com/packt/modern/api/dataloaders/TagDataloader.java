package com.packt.modern.api.dataloaders;

import com.netflix.graphql.dgs.DgsDataLoader;
import com.packt.modern.api.generated.types.Tag;
import com.packt.modern.api.services.TagService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.dataloader.MappedBatchLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter14 - Modern API Development with Spring and Spring Boot
 **/
@DgsDataLoader(name = "tags")
public class TagDataloader implements MappedBatchLoader<String, List<Tag>> {

  private final Logger LOG = LoggerFactory.getLogger(getClass());
  private final TagService tagService;

  public TagDataloader(TagService tagService) {
    this.tagService = tagService;
  }
  
  @Override
  public CompletionStage<Map<String, List<Tag>>> load(Set<String> keys) {
    LOG.info("Tags will be fetched for following product IDs: {}", keys);
    return CompletableFuture.supplyAsync(() -> tagService.getTags(new ArrayList<>(keys)));
  }
}
