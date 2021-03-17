package com.packt.modern.api.exception;

import static com.packt.modern.api.security.Constants.TOKEN_URL;

import com.fasterxml.jackson.core.JsonParseException;
import java.time.Instant;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter09 - Modern API Development with Spring and Spring Boot
 **/
@ControllerAdvice
public class RestApiErrorHandler {

  private static final Logger log = LoggerFactory.getLogger(RestApiErrorHandler.class);
  private final MessageSource messageSource;

  @Autowired
  public RestApiErrorHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handleException(HttpServletRequest request, Exception ex,
      Locale locale) {
    ex.printStackTrace(); // TODO: Should be kept only for development
    Error error = ErrorUtils
        .createError(ErrorCode.GENERIC_ERROR.getErrMsgKey(), ErrorCode.GENERIC_ERROR.getErrCode(),
            HttpStatus.INTERNAL_SERVER_ERROR.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(HttpServletRequest request,
      HttpMediaTypeNotSupportedException ex,
      Locale locale) {
    ex.printStackTrace(); // TODO: Should be kept only for development
    Error error = ErrorUtils
        .createError(ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrMsgKey(),
            ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrCode(),
            HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    log.info("HttpMediaTypeNotSupportedException :: request.getMethod(): " + request.getMethod());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpMessageNotWritableException.class)
  public ResponseEntity<Error> handleHttpMessageNotWritableException(HttpServletRequest request,
      HttpMessageNotWritableException ex,
      Locale locale) {
    ex.printStackTrace(); // TODO: Should be kept only for development
    Error error = ErrorUtils
        .createError(ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getErrMsgKey(),
            ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getErrCode(),
            HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    log.info("HttpMessageNotWritableException :: request.getMethod(): " + request.getMethod());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  public ResponseEntity<Error> handleHttpMediaTypeNotAcceptableException(HttpServletRequest request,
      HttpMediaTypeNotAcceptableException ex,
      Locale locale) {
    ex.printStackTrace(); // TODO: Should be kept only for development
    Error error = ErrorUtils
        .createError(ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrMsgKey(),
            ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrCode(),
            HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    log.info("HttpMediaTypeNotAcceptableException :: request.getMethod(): " + request.getMethod());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Error> handleHttpMessageNotReadableException(HttpServletRequest request,
      HttpMessageNotReadableException ex,
      Locale locale) {
    Error error = ErrorUtils
        .createError(ErrorCode.HTTP_MESSAGE_NOT_READABLE.getErrMsgKey(),
            ErrorCode.HTTP_MESSAGE_NOT_READABLE.getErrCode(),
            HttpStatus.NOT_ACCEPTABLE.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(JsonParseException.class)
  public ResponseEntity<Error> handleJsonParseException(HttpServletRequest request,
      JsonParseException ex,
      Locale locale) {
    Error error = ErrorUtils
        .createError(ErrorCode.JSON_PARSE_ERROR.getErrMsgKey(),
            ErrorCode.JSON_PARSE_ERROR.getErrCode(),
            HttpStatus.NOT_ACCEPTABLE.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Error> handleHttpRequestMethodNotSupportedException(
      HttpServletRequest request,
      HttpRequestMethodNotSupportedException ex,
      Locale locale) {
    Error error = ErrorUtils
        .createError(ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED.getErrMsgKey(),
            ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED.getErrCode(),
            HttpStatus.NOT_IMPLEMENTED.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
  }

  @ExceptionHandler(MethodNotAllowedException.class)
  public ResponseEntity<Error> handleMethodNotAllowedException(
      HttpServletRequest request,
      MethodNotAllowedException ex,
      Locale locale) {
    Error error = ErrorUtils
        .createError(String
                .format("%s. Supported methods: %s", ex.getMessage(), ex.getSupportedMethods()),
            ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED.getErrCode(),
            HttpStatus.METHOD_NOT_ALLOWED.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Error> handleAuthenticationException(
      HttpServletRequest request,
      AuthenticationException ex,
      Locale locale) {
    log.info("exception = " + ex);
    log.info("exception.getCause() = " + ex.getCause());
    String errorMsg = "";
    if (ex instanceof InsufficientAuthenticationException) {
      errorMsg = ex.getMessage();
    } else {
      errorMsg = ErrorCode.UNAUTHORIZED.getErrMsgKey();
    }
    Error error = ErrorUtils
        .createError(errorMsg, ErrorCode.UNAUTHORIZED.getErrCode(),
            HttpStatus.UNAUTHORIZED.value()).setUrl(TOKEN_URL)
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Error> handleAccessDeniedException(
      HttpServletRequest request,
      AccessDeniedException ex,
      Locale locale) {
    log.info("exception = " + ex);
    log.info("exception.getCause() = " + ex.getCause());
    //InvalidRefreshTokenException
    String errorMsg = String.format("%s %s",ErrorCode.ACCESS_DENIED.getErrMsgKey(), ex.getMessage());
    Error error = ErrorUtils
        .createError(errorMsg, ErrorCode.ACCESS_DENIED.getErrCode(),
            HttpStatus.FORBIDDEN.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(InvalidRefreshTokenException.class)
  public ResponseEntity<Error> handleInvalidRefreshTokenException(
      HttpServletRequest request,
      InvalidRefreshTokenException ex,
      Locale locale) {
    String errorMsg = String.format("%s %s",ErrorCode.RESOURCE_NOT_FOUND.getErrMsgKey(), ex.getMessage());
    Error error = ErrorUtils
        .createError(errorMsg, ErrorCode.RESOURCE_NOT_FOUND.getErrCode(),
            HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  /*@ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Error> handleConstraintViolationException(HttpServletRequest request,
      ConstraintViolationException ex, Locale locale) {
    ex.printStackTrace();
    Error error = ErrorUtils
        .createError(
            String.format("%s %s", ErrorCode.CONSTRAINT_VIOLATION.getErrMsgKey(), ex.getMessage()),
            ErrorCode.CONSTRAINT_VIOLATION.getErrCode(),
            HttpStatus.BAD_REQUEST.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }*/

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Error> handleHIllegalArgumentException(
      HttpServletRequest request,
      IllegalArgumentException ex,
      Locale locale) {
    Error error = ErrorUtils
        .createError(String
                .format("%s %s", ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getErrMsgKey(), ex.getMessage()),
            ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getErrCode(),
            HttpStatus.BAD_REQUEST.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Error> handleResourceNotFoundException(HttpServletRequest request,
      ResourceNotFoundException ex, Locale locale) {
    Error error = ErrorUtils
        .createError(
            String.format("%s %s", ErrorCode.RESOURCE_NOT_FOUND.getErrMsgKey(), ex.getMessage()),
            ex.getErrorCode(),
            HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<Error> handleCustomerNotFoundException(HttpServletRequest request,
      CustomerNotFoundException ex, Locale locale) {
    Error error = ErrorUtils
        .createError(
            String.format("%s %s", ErrorCode.CUSTOMER_NOT_FOUND.getErrMsgKey(), ex.getMessage()),
            ex.getErrorCode(),
            HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ItemNotFoundException.class)
  public ResponseEntity<Error> handleItemNotFoundException(HttpServletRequest request,
      ItemNotFoundException ex, Locale locale) {
    Error error = ErrorUtils
        .createError(
            String.format("%s %s", ErrorCode.ITEM_NOT_FOUND.getErrMsgKey(), ex.getMessage()),
            ex.getErrorCode(),
            HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(GenericAlreadyExistsException.class)
  public ResponseEntity<Error> handleGenericAlreadyExistsException(HttpServletRequest request,
      GenericAlreadyExistsException ex, Locale locale) {
    Error error = ErrorUtils
        .createError(
            String
                .format("%s %s", ErrorCode.GENERIC_ALREADY_EXISTS.getErrMsgKey(), ex.getMessage()),
            ex.getErrorCode(),
            HttpStatus.NOT_ACCEPTABLE.value()).setUrl(request.getRequestURL().toString())
        .setReqMethod(request.getMethod())
        .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
  }
}
