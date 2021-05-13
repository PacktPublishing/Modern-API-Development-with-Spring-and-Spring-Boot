package com.packt.modern.api.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsSubscription;
import com.netflix.graphql.dgs.InputArgument;
import com.packt.modern.api.generated.DgsConstants;
import com.packt.modern.api.generated.DgsConstants.MUTATION;
import com.packt.modern.api.generated.DgsConstants.QUERY;
import com.packt.modern.api.generated.DgsConstants.SUBSCRIPTION;
import com.packt.modern.api.generated.types.Product;
import com.packt.modern.api.generated.types.TagInput;
import com.packt.modern.api.services.ProductService;
import com.packt.modern.api.services.TagService;
import java.util.List;
import org.apache.logging.log4j.util.Strings;
import org.reactivestreams.Publisher;

@DgsComponent
public class ProductDatafetcher {

  private final ProductService productService;
  private final TagService tagService;

  public ProductDatafetcher(ProductService productService, TagService tagService) {
    this.productService = productService;
    this.tagService = tagService;
  }

  @DgsData(parentType = DgsConstants.QUERY_TYPE, field = QUERY.Product)
  public Product getProduct(@InputArgument("id") String id) {
    if (Strings.isBlank(id)) {
      new RuntimeException("Invalid Product ID.");
    }
    return productService.getProduct(id);
  }

  @DgsMutation(field = MUTATION.AddTag)
  public Product addTags(@InputArgument("productId") String productId,
      @InputArgument(value = "tags", collectionType = TagInput.class) List<TagInput> tags) {
    return tagService.addTags(productId, tags);
  }

  @DgsMutation(field = MUTATION.AddQuantity)
  public Product addQuantity(@InputArgument("productId") String productId,
      @InputArgument(value = "quantity") int qty) {
    return productService.addQuantity(productId, qty);
  }

  @DgsSubscription(field = SUBSCRIPTION.QuantityChanged)
  public Publisher<Product> quantityChanged(@InputArgument("productId") String productId) {
    return productService.gerProductPublisher();
  }
}
