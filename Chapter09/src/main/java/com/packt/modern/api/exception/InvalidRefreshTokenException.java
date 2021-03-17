package com.packt.modern.api.exception;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter09 - Modern API Development with Spring and Spring Boot
 **/
public class InvalidRefreshTokenException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String errMsgKey;
  private final String errorCode;

  public InvalidRefreshTokenException() {
    super(ErrorCode.UNAUTHORIZED.getErrMsgKey());
    this.errMsgKey = ErrorCode.UNAUTHORIZED.getErrMsgKey();
    this.errorCode = ErrorCode.UNAUTHORIZED.getErrCode();
  }

  public InvalidRefreshTokenException(ErrorCode code) {
    super(code.getErrMsgKey());
    this.errMsgKey = code.getErrMsgKey();
    this.errorCode = code.getErrCode();
  }

  public InvalidRefreshTokenException(final String message) {
    super(message);
    this.errMsgKey = ErrorCode.CUSTOMER_NOT_FOUND.getErrMsgKey();
    this.errorCode = ErrorCode.CUSTOMER_NOT_FOUND.getErrCode();
  }

  public String getErrMsgKey() {
    return errMsgKey;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
