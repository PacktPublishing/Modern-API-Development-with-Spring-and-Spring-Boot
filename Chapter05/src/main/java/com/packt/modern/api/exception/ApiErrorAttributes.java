package com.packt.modern.api.exception;

import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
@Component
public class ApiErrorAttributes extends DefaultErrorAttributes {

  private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
  private String message = ErrorCode.GENERIC_ERROR.getErrMsgKey();

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request,
      ErrorAttributeOptions options) {
    var attributes = super.getErrorAttributes(request, options);
    attributes.put("status", status);
    attributes.put("message", message);
    attributes.put("code", ErrorCode.GENERIC_ERROR.getErrCode());
    return attributes;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public ApiErrorAttributes setStatus(HttpStatus status) {
    this.status = status;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public ApiErrorAttributes setMessage(String message) {
    this.message = message;
    return this;
  }
}
