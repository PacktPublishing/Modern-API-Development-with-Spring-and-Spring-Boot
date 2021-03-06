// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PaymentGatewayService.proto

package com.packt.modern.api.grpc.v1;

public interface SourceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.packtpub.v1.Source)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <pre>
   * A positive integer in the smallest currency unit (that is, 100 cents for $1.00, or 1 for ¥1,
   * Japanese Yen being a zero-decimal currency) representing the total amount associated with the
   * source. This is the amount for which the source will be chargeable once ready. Required for single_use sources.
   * </pre>
   *
   * <code>uint32 amount = 2;</code>
   * @return The amount.
   */
  int getAmount();

  /**
   * <pre>
   * The client secret of the source. Used for client-side retrieval using a publishable key.
   * </pre>
   *
   * <code>string clientSecret = 3;</code>
   * @return The clientSecret.
   */
  java.lang.String getClientSecret();
  /**
   * <pre>
   * The client secret of the source. Used for client-side retrieval using a publishable key.
   * </pre>
   *
   * <code>string clientSecret = 3;</code>
   * @return The bytes for clientSecret.
   */
  com.google.protobuf.ByteString
      getClientSecretBytes();

  /**
   * <pre>
   * Time at which the object was created. Measured in seconds since the Unix epoch.
   * </pre>
   *
   * <code>uint64 created = 4;</code>
   * @return The created.
   */
  long getCreated();

  /**
   * <pre>
   * Three-letter ISO code for the currency associated with the source. This is the currency for
   * which the source will be chargeable once ready. Required for single_use sources.
   * </pre>
   *
   * <code>string currency = 5;</code>
   * @return The currency.
   */
  java.lang.String getCurrency();
  /**
   * <pre>
   * Three-letter ISO code for the currency associated with the source. This is the currency for
   * which the source will be chargeable once ready. Required for single_use sources.
   * </pre>
   *
   * <code>string currency = 5;</code>
   * @return The bytes for currency.
   */
  com.google.protobuf.ByteString
      getCurrencyBytes();

  /**
   * <pre>
   * The authentication flow of the source. flow is one of redirect, receiver, codeVerification, none.
   * </pre>
   *
   * <code>.com.packtpub.v1.Flow flow = 6;</code>
   * @return The enum numeric value on the wire for flow.
   */
  int getFlowValue();
  /**
   * <pre>
   * The authentication flow of the source. flow is one of redirect, receiver, codeVerification, none.
   * </pre>
   *
   * <code>.com.packtpub.v1.Flow flow = 6;</code>
   * @return The flow.
   */
  com.packt.modern.api.grpc.v1.Flow getFlow();

  /**
   * <pre>
   * Information about the owner of the payment instrument that may be used or required by particular source types.
   * </pre>
   *
   * <code>.com.packtpub.v1.Owner owner = 7;</code>
   * @return Whether the owner field is set.
   */
  boolean hasOwner();
  /**
   * <pre>
   * Information about the owner of the payment instrument that may be used or required by particular source types.
   * </pre>
   *
   * <code>.com.packtpub.v1.Owner owner = 7;</code>
   * @return The owner.
   */
  com.packt.modern.api.grpc.v1.Owner getOwner();
  /**
   * <pre>
   * Information about the owner of the payment instrument that may be used or required by particular source types.
   * </pre>
   *
   * <code>.com.packtpub.v1.Owner owner = 7;</code>
   */
  com.packt.modern.api.grpc.v1.OwnerOrBuilder getOwnerOrBuilder();

  /**
   * <pre>
   * Information related to the receiver flow. Present if the source is a receiver (flow is receiver).
   * </pre>
   *
   * <code>.com.packtpub.v1.Receiver receiver = 8;</code>
   * @return Whether the receiver field is set.
   */
  boolean hasReceiver();
  /**
   * <pre>
   * Information related to the receiver flow. Present if the source is a receiver (flow is receiver).
   * </pre>
   *
   * <code>.com.packtpub.v1.Receiver receiver = 8;</code>
   * @return The receiver.
   */
  com.packt.modern.api.grpc.v1.Receiver getReceiver();
  /**
   * <pre>
   * Information related to the receiver flow. Present if the source is a receiver (flow is receiver).
   * </pre>
   *
   * <code>.com.packtpub.v1.Receiver receiver = 8;</code>
   */
  com.packt.modern.api.grpc.v1.ReceiverOrBuilder getReceiverOrBuilder();

  /**
   * <pre>
   * Extra information about a source. This will appear on your customer’s statement every time you charge the source.
   * </pre>
   *
   * <code>string statementDescriptor = 9;</code>
   * @return The statementDescriptor.
   */
  java.lang.String getStatementDescriptor();
  /**
   * <pre>
   * Extra information about a source. This will appear on your customer’s statement every time you charge the source.
   * </pre>
   *
   * <code>string statementDescriptor = 9;</code>
   * @return The bytes for statementDescriptor.
   */
  com.google.protobuf.ByteString
      getStatementDescriptorBytes();

  /**
   * <pre>
   * The status of the source, one of canceled, chargeable, consumed, failed, or pending. Only
   * chargeable sources can be used to create a charge.
   * </pre>
   *
   * <code>.com.packtpub.v1.Source.Status status = 10;</code>
   * @return The enum numeric value on the wire for status.
   */
  int getStatusValue();
  /**
   * <pre>
   * The status of the source, one of canceled, chargeable, consumed, failed, or pending. Only
   * chargeable sources can be used to create a charge.
   * </pre>
   *
   * <code>.com.packtpub.v1.Source.Status status = 10;</code>
   * @return The status.
   */
  com.packt.modern.api.grpc.v1.Source.Status getStatus();

  /**
   * <pre>
   * The type of the source. The type is a payment method, one of achCreditTransfer, card etc.
   * An additional hash is included on the source with a name matching this value.
   * It contains additional information specific to the payment method used.
   * </pre>
   *
   * <code>string type = 11;</code>
   * @return The type.
   */
  java.lang.String getType();
  /**
   * <pre>
   * The type of the source. The type is a payment method, one of achCreditTransfer, card etc.
   * An additional hash is included on the source with a name matching this value.
   * It contains additional information specific to the payment method used.
   * </pre>
   *
   * <code>string type = 11;</code>
   * @return The bytes for type.
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <pre>
   * Either reusable or single_use. Whether this source should be reusable or not.
   * Some source types may or may not be reusable by construction, while others may leave the option
   * at creation. If an incompatible value is passed, an error will be returned.
   * </pre>
   *
   * <code>.com.packtpub.v1.Usage usage = 12;</code>
   * @return The enum numeric value on the wire for usage.
   */
  int getUsageValue();
  /**
   * <pre>
   * Either reusable or single_use. Whether this source should be reusable or not.
   * Some source types may or may not be reusable by construction, while others may leave the option
   * at creation. If an incompatible value is passed, an error will be returned.
   * </pre>
   *
   * <code>.com.packtpub.v1.Usage usage = 12;</code>
   * @return The usage.
   */
  com.packt.modern.api.grpc.v1.Usage getUsage();
}
