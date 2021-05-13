package com.packt.modern.api.scalar;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.scalar.GraphqlBigDecimalCoercing;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter14 - Modern API Development with Spring and Spring Boot
 **/
@DgsScalar(name = "BigDecimal")
public class BigDecimalScalar extends GraphqlBigDecimalCoercing {

}
