// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PaymentGatewayService.proto

package com.packt.modern.api.grpc.v1;

public interface UpdateSourceReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.packtpub.v1.UpdateSourceReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Id of the Source for which update request is being called.
   * </pre>
   *
   * <code>string sourceId = 1;</code>
   * @return The sourceId.
   */
  java.lang.String getSourceId();
  /**
   * <pre>
   * Id of the Source for which update request is being called.
   * </pre>
   *
   * <code>string sourceId = 1;</code>
   * @return The bytes for sourceId.
   */
  com.google.protobuf.ByteString
      getSourceIdBytes();

  /**
   * <pre>
   * Optional. Amount associated with the source.
   * </pre>
   *
   * <code>uint32 amount = 2;</code>
   * @return The amount.
   */
  int getAmount();

  /**
   * <pre>
   * Optional. Information about the owner of the payment instrument that may be used or required by particular source types.
   * </pre>
   *
   * <code>.com.packtpub.v1.Owner owner = 3;</code>
   * @return Whether the owner field is set.
   */
  boolean hasOwner();
  /**
   * <pre>
   * Optional. Information about the owner of the payment instrument that may be used or required by particular source types.
   * </pre>
   *
   * <code>.com.packtpub.v1.Owner owner = 3;</code>
   * @return The owner.
   */
  com.packt.modern.api.grpc.v1.Owner getOwner();
  /**
   * <pre>
   * Optional. Information about the owner of the payment instrument that may be used or required by particular source types.
   * </pre>
   *
   * <code>.com.packtpub.v1.Owner owner = 3;</code>
   */
  com.packt.modern.api.grpc.v1.OwnerOrBuilder getOwnerOrBuilder();
}