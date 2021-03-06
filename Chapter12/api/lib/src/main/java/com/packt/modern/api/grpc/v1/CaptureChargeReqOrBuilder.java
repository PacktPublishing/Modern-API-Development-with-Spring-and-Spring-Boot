// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PaymentGatewayService.proto

package com.packt.modern.api.grpc.v1;

public interface CaptureChargeReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.packtpub.v1.CaptureChargeReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Id of the Charge for which capture request is being called.
   * </pre>
   *
   * <code>string chargeId = 1;</code>
   * @return The chargeId.
   */
  java.lang.String getChargeId();
  /**
   * <pre>
   * Id of the Charge for which capture request is being called.
   * </pre>
   *
   * <code>string chargeId = 1;</code>
   * @return The bytes for chargeId.
   */
  com.google.protobuf.ByteString
      getChargeIdBytes();

  /**
   * <pre>
   * Optional. Amount intended to be collected by this payment. A positive integer representing how
   * much to charge in the smallest currency unit (e.g., 100 cents to charge $1.00 or 100 to charge
   * ¥100, a zero-decimal currency). The minimum amount is $0.50 US or equivalent in charge currency.
   *The amount value supports up to eight digits (e.g., a value of 99999999 for a USD charge of $999,999.99).
   * </pre>
   *
   * <code>uint32 amount = 2;</code>
   * @return The amount.
   */
  int getAmount();

  /**
   * <pre>
   * Optional. The email address to which this charge’s receipt will be sent. The receipt will not
   * be sent until the charge is paid, and no receipts will be sent for test mode charges. If this
   * charge is for a Customer, the email address specified here will override the customer’s email
   * address. If receiptEmail is specified for a charge in live mode, a receipt will be sent
   * regardless of your email settings.
   * </pre>
   *
   * <code>string receiptEmail = 3;</code>
   * @return The receiptEmail.
   */
  java.lang.String getReceiptEmail();
  /**
   * <pre>
   * Optional. The email address to which this charge’s receipt will be sent. The receipt will not
   * be sent until the charge is paid, and no receipts will be sent for test mode charges. If this
   * charge is for a Customer, the email address specified here will override the customer’s email
   * address. If receiptEmail is specified for a charge in live mode, a receipt will be sent
   * regardless of your email settings.
   * </pre>
   *
   * <code>string receiptEmail = 3;</code>
   * @return The bytes for receiptEmail.
   */
  com.google.protobuf.ByteString
      getReceiptEmailBytes();

  /**
   * <pre>
   * Optional
   * </pre>
   *
   * <code>string statementDescriptor = 4;</code>
   * @return The statementDescriptor.
   */
  java.lang.String getStatementDescriptor();
  /**
   * <pre>
   * Optional
   * </pre>
   *
   * <code>string statementDescriptor = 4;</code>
   * @return The bytes for statementDescriptor.
   */
  com.google.protobuf.ByteString
      getStatementDescriptorBytes();
}
