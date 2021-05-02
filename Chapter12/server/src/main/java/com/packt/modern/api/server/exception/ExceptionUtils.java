package com.packt.modern.api.server.exception;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import java.net.SocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter12 - Modern API Development with Spring and Spring Boot
 **/
@Component
public class ExceptionUtils {

  private static final Logger LOG = LoggerFactory.getLogger(ExceptionUtils.class);

  public static StatusRuntimeException traceException(Throwable e) {
    return traceException(e, null);
  }

  public static <T extends com.google.protobuf.GeneratedMessageV3> StatusRuntimeException traceException(
      Throwable e, T defaultInstance) {
    com.google.rpc.Status status;
    StatusRuntimeException statusRuntimeException;
    if (e instanceof StatusRuntimeException) {
      statusRuntimeException = (StatusRuntimeException) e;
    } else {
      Throwable cause = e;
      if (cause != null && cause.getCause() != null && cause.getCause() != cause) {
        cause = cause.getCause();
      }
      // Socket exception is added for example. Similarly, you can add relevant errors.
      if (cause instanceof SocketException) {
        String errorMessage = "Sample exception message";
        LOG.info(errorMessage + "{}", e.getMessage());
        status = com.google.rpc.Status.newBuilder()
            .setCode(com.google.rpc.Code.UNAVAILABLE_VALUE)
            .setMessage(errorMessage + cause.getMessage())
            .addDetails(Any.pack(defaultInstance))
            .build();
      } else {
        status = com.google.rpc.Status.newBuilder()
            .setCode(com.google.rpc.Code.INTERNAL_VALUE)
            .setMessage("Internal server error")
            .addDetails(Any.pack(defaultInstance))
            .build();
      }
      LOG.error("Error message {}", e.getMessage());
      statusRuntimeException = StatusProto.toStatusRuntimeException(status);
    }
    return statusRuntimeException;
  }

  public static <T extends GeneratedMessageV3> void observeError(
      StreamObserver<T> responseObserver, Throwable e) {
    responseObserver.onError(traceException(e));
  }

  public static <T extends GeneratedMessageV3> void observeError(
      StreamObserver<T> responseObserver, Exception e, T defaultInstance) {
    responseObserver.onError(traceException(e, defaultInstance));
  }
}
