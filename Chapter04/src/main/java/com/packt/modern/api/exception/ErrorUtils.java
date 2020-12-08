package com.packt.modern.api.exception;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot
 **/
public class ErrorUtils {

  private ErrorUtils() {
  }

  /**
   * Creates and return an error object
   *
   * @param errMsgKey
   * @param errorCode
   * @param httpStatusCode
   * @param url
   * @return error
   */
  public static Error createError(final String errMsgKey, final String errorCode,
      final Integer httpStatusCode) {
    Error error = new Error();
    error.setMessage(errMsgKey);
    error.setErrorCode(errorCode);
    error.setStatus(httpStatusCode);
    return error;
  }
}
