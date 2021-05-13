package com.packt.modern.api.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.packt.modern.api.dataloaders.TagsDataloaderWithContext;
import com.packt.modern.api.generated.DgsConstants;
import com.packt.modern.api.generated.DgsConstants.PRODUCT;
import com.packt.modern.api.generated.DgsConstants.QUERY;
import com.packt.modern.api.generated.types.Product;
import com.packt.modern.api.generated.types.ProductCriteria;
import com.packt.modern.api.services.ProductService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.dataloader.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.metrics.StartupStep.Tags;

@DgsComponent
public class ProductsDatafetcher {

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  private ProductService service;

  public ProductsDatafetcher(ProductService service) {
    this.service = service;
  }

  @DgsData(
      parentType = PRODUCT.TYPE_NAME,
      field = PRODUCT.Tags
  )
  public CompletableFuture<List<Tags>> tags(DgsDataFetchingEnvironment env) {
    DataLoader<String, List<Tags>> tagsDataLoader = env
        .getDataLoader(TagsDataloaderWithContext.class);
    Product product = env.getSource();
    LOG.info("Tag is being fetched for Product with ID - {}", product.getId());
    return tagsDataLoader.load(product.getId());
  }

  @DgsData(
      parentType = DgsConstants.QUERY_TYPE,
      field = QUERY.Products
  )
  public List<Product> getProducts(@InputArgument("filter") ProductCriteria criteria) {
    return service.getProducts(criteria);
  }
}
