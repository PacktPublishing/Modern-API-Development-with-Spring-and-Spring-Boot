package com.packt.modern.api.exception;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter05 - Modern API Development with Spring and Spring Boot
 **/
public class CardAlreadyExistsException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String errMsgKey;
  private final String errorCode;

  public CardAlreadyExistsException(ErrorCode code) {
    super(code.getErrMsgKey());
    this.errMsgKey = code.getErrMsgKey();
    this.errorCode = code.getErrCode();
  }

  public CardAlreadyExistsException(final String message) {
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
