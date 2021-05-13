package com.packt.modern.api.datafetchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import com.packt.modern.api.generated.DgsConstants.MUTATION;
import com.packt.modern.api.generated.DgsConstants.SUBSCRIPTION;
import com.packt.modern.api.generated.client.AddQuantityGraphQLQuery;
import com.packt.modern.api.generated.client.AddQuantityProjectionRoot;
import com.packt.modern.api.generated.client.AddTagGraphQLQuery;
import com.packt.modern.api.generated.client.AddTagProjectionRoot;
import com.packt.modern.api.generated.client.ProductGraphQLQuery;
import com.packt.modern.api.generated.client.ProductProjectionRoot;
import com.packt.modern.api.generated.types.Product;
import com.packt.modern.api.generated.types.Tag;
import com.packt.modern.api.generated.types.TagInput;
import com.packt.modern.api.repository.InMemRepository;
import com.packt.modern.api.scalar.BigDecimalScalar;
import com.packt.modern.api.services.ProductService;
import com.packt.modern.api.services.TagService;
import graphql.ExecutionResult;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter14 - Modern API Development with Spring and Spring Boot
 **/
@SpringBootTest(classes = {DgsAutoConfiguration.class, ProductDatafetcher.class,
    BigDecimalScalar.class})
public class ProductDatafetcherTest {

  private final InMemRepository repo = new InMemRepository();
  private final int TEN = 10;
  @Autowired
  private DgsQueryExecutor dgsQueryExecutor;
  @MockBean
  private ProductService productService;
  @MockBean
  private TagService tagService;

  @BeforeEach
  public void beforeEach() {
    List<Tag> tags = new ArrayList<>();
    tags.add(Tag.newBuilder().id("tag1").name("Tag 1").build());
    Product product = Product.newBuilder().id("any").name("mock title")
        .description("mock description").price(BigDecimal.valueOf(20.20)).count(100)
        .tags(tags).build();
    given(productService.getProduct("any")).willReturn(product);
    tags.add(Tag.newBuilder().id("tag2").name("addTags").build());
    product.setTags(tags);
    given(tagService.addTags("any", List.of(TagInput.newBuilder().name("addTags").build())))
        .willAnswer(invocation -> product);
  }

  @Test
  @DisplayName("Verify the JSON attribute returned from the query 'product'")
  public void product() {
    String name = dgsQueryExecutor
        .executeAndExtractJsonPath("{ product(id: \"any\") { name }}", "data.product.name");
    assertThat(name).contains("mock title");
  }

  @Test
  @DisplayName("Verify the exception if incorrect ID is passed to query 'product'")
  public void productWithException() {
    given(productService.getProduct("any")).willThrow(new RuntimeException("Invalid Product ID."));
    ExecutionResult result = dgsQueryExecutor.execute(" { product (id: \"any\") { name }}");
    verify(productService, times(1)).getProduct("any");
    assertThat(result.getErrors()).isNotEmpty();
    assertThat(result.getErrors().get(0).getMessage())
        .isEqualTo("java.lang.RuntimeException: Invalid Product ID.");
  }

  @Test
  @DisplayName("Verify the JSON attributes returned from the query 'product' using client GraphQLQueryRequest")
  void productsWithQueryApi() {
    GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(
        ProductGraphQLQuery.newRequest().id("any").build(),
        new ProductProjectionRoot().id().name());
    String name = dgsQueryExecutor
        .executeAndExtractJsonPath(graphQLQueryRequest.serialize(), "data.product.name");
    assertThat(name).contains("mock title");
  }

  @Test
  @DisplayName("Verify the Tags returned from the query 'product'")
  void productsWithTags() {
    GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(
        ProductGraphQLQuery.newRequest().id("any").build(),
        new ProductProjectionRoot().id().name().tags().id().name());
    Product product = dgsQueryExecutor
        .executeAndExtractJsonPathAsObject(graphQLQueryRequest.serialize(), "data.product",
            new TypeRef<>() {
            });
    System.out.println(product);
    assertThat(product.getId()).isEqualTo("any");
    assertThat(product.getName()).isEqualTo("mock title");
    assertThat(product.getTags().size()).isEqualTo(2);
    assertThat(product.getTags().get(0).getName()).isEqualTo("Tag 1");
  }

  @Test
  @DisplayName("Verify the mutation 'addTags'")
  void addTagsMutation() {
    GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(
        AddTagGraphQLQuery.newRequest().productId("any")
            .tags(List.of(TagInput.newBuilder().name("addTags").build()))
            .build(),
        new AddTagProjectionRoot().name().count());
    ExecutionResult executionResult = dgsQueryExecutor.execute(graphQLQueryRequest.serialize());
    assertThat(executionResult.getErrors()).isEmpty();
    verify(tagService).addTags("any", List.of(TagInput.newBuilder().name("addTags").build()));
  }

  @Test
  @DisplayName("Verify the mutation 'addQuantity'")
  void addQuantityMutation() {
    given(productService.addQuantity("a1s2d3f4-1", TEN))
        .willReturn(repo.addQuantity("a1s2d3f4-1", TEN));
    GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(
        AddQuantityGraphQLQuery.newRequest().productId("a1s2d3f4-1")
            .quantity(TEN)
            .build(),
        new AddQuantityProjectionRoot().name().count());
    ExecutionResult executionResult = dgsQueryExecutor.execute(graphQLQueryRequest.serialize());
    assertThat(executionResult.getErrors()).isEmpty();
    Object obj = executionResult.getData();
    assertThat(obj).isNotNull();
    Map<String, Object> data = (Map) ((Map) executionResult.getData()).get(MUTATION.AddQuantity);
    org.hamcrest.MatcherAssert.assertThat((Integer) data.get("count"), greaterThan(TEN));
  }

  @Test
  @DisplayName("Verify the subscription 'quantityChanged'")
  void reviewSubscription() {
    given(productService.gerProductPublisher()).willReturn(repo.getProductPublisher());
    ExecutionResult executionResult = dgsQueryExecutor
        .execute(
            "subscription { quantityChanged(productId: \"a1s2d3f4-0\") { id name price count } }");
    Publisher<ExecutionResult> publisher = executionResult.getData();
    List<Product> product = new CopyOnWriteArrayList<>();
    publisher.subscribe(new Subscriber<>() {
      @Override
      public void onSubscribe(Subscription s) {
        s.request(2);
      }

      @Override
      public void onNext(ExecutionResult result) {
        if (result.getErrors().size() > 0) {
          System.out.println(result.getErrors());
        }
        Map<String, Object> data = result.getData();
        product.add(
            new ObjectMapper().convertValue(data.get(SUBSCRIPTION.QuantityChanged), Product.class));
      }

      @Override
      public void onError(Throwable t) {
      }

      @Override
      public void onComplete() {
      }
    });
    addQuantityMutation();
    Integer count = product.get(0).getCount();
    addQuantityMutation();
    assertThat(product.get(0).getId()).isEqualTo(product.get(1).getId());
    assertThat(product.get(1).getCount()).isEqualTo(count.intValue() + TEN);
  }
}
