package com.packt.modern.api.grpc.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * It allows to add various payment sources (read methods) for customers.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.1)",
    comments = "Source: PaymentGatewayService.proto")
public final class SourceServiceGrpc {

  private SourceServiceGrpc() {}

  public static final String SERVICE_NAME = "com.packtpub.v1.SourceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.CreateSourceReq,
      com.packt.modern.api.grpc.v1.CreateSourceReq.Response> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Create",
      requestType = com.packt.modern.api.grpc.v1.CreateSourceReq.class,
      responseType = com.packt.modern.api.grpc.v1.CreateSourceReq.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.CreateSourceReq,
      com.packt.modern.api.grpc.v1.CreateSourceReq.Response> getCreateMethod() {
    io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.CreateSourceReq, com.packt.modern.api.grpc.v1.CreateSourceReq.Response> getCreateMethod;
    if ((getCreateMethod = SourceServiceGrpc.getCreateMethod) == null) {
      synchronized (SourceServiceGrpc.class) {
        if ((getCreateMethod = SourceServiceGrpc.getCreateMethod) == null) {
          SourceServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<com.packt.modern.api.grpc.v1.CreateSourceReq, com.packt.modern.api.grpc.v1.CreateSourceReq.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.CreateSourceReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.CreateSourceReq.Response.getDefaultInstance()))
              .setSchemaDescriptor(new SourceServiceMethodDescriptorSupplier("Create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.SourceId,
      com.packt.modern.api.grpc.v1.SourceId.Response> getRetrieveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Retrieve",
      requestType = com.packt.modern.api.grpc.v1.SourceId.class,
      responseType = com.packt.modern.api.grpc.v1.SourceId.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.SourceId,
      com.packt.modern.api.grpc.v1.SourceId.Response> getRetrieveMethod() {
    io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.SourceId, com.packt.modern.api.grpc.v1.SourceId.Response> getRetrieveMethod;
    if ((getRetrieveMethod = SourceServiceGrpc.getRetrieveMethod) == null) {
      synchronized (SourceServiceGrpc.class) {
        if ((getRetrieveMethod = SourceServiceGrpc.getRetrieveMethod) == null) {
          SourceServiceGrpc.getRetrieveMethod = getRetrieveMethod =
              io.grpc.MethodDescriptor.<com.packt.modern.api.grpc.v1.SourceId, com.packt.modern.api.grpc.v1.SourceId.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Retrieve"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.SourceId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.SourceId.Response.getDefaultInstance()))
              .setSchemaDescriptor(new SourceServiceMethodDescriptorSupplier("Retrieve"))
              .build();
        }
      }
    }
    return getRetrieveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.UpdateSourceReq,
      com.packt.modern.api.grpc.v1.UpdateSourceReq.Response> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Update",
      requestType = com.packt.modern.api.grpc.v1.UpdateSourceReq.class,
      responseType = com.packt.modern.api.grpc.v1.UpdateSourceReq.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.UpdateSourceReq,
      com.packt.modern.api.grpc.v1.UpdateSourceReq.Response> getUpdateMethod() {
    io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.UpdateSourceReq, com.packt.modern.api.grpc.v1.UpdateSourceReq.Response> getUpdateMethod;
    if ((getUpdateMethod = SourceServiceGrpc.getUpdateMethod) == null) {
      synchronized (SourceServiceGrpc.class) {
        if ((getUpdateMethod = SourceServiceGrpc.getUpdateMethod) == null) {
          SourceServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<com.packt.modern.api.grpc.v1.UpdateSourceReq, com.packt.modern.api.grpc.v1.UpdateSourceReq.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.UpdateSourceReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.UpdateSourceReq.Response.getDefaultInstance()))
              .setSchemaDescriptor(new SourceServiceMethodDescriptorSupplier("Update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.AttachOrDetachReq,
      com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> getAttachMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Attach",
      requestType = com.packt.modern.api.grpc.v1.AttachOrDetachReq.class,
      responseType = com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.AttachOrDetachReq,
      com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> getAttachMethod() {
    io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.AttachOrDetachReq, com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> getAttachMethod;
    if ((getAttachMethod = SourceServiceGrpc.getAttachMethod) == null) {
      synchronized (SourceServiceGrpc.class) {
        if ((getAttachMethod = SourceServiceGrpc.getAttachMethod) == null) {
          SourceServiceGrpc.getAttachMethod = getAttachMethod =
              io.grpc.MethodDescriptor.<com.packt.modern.api.grpc.v1.AttachOrDetachReq, com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Attach"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.AttachOrDetachReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response.getDefaultInstance()))
              .setSchemaDescriptor(new SourceServiceMethodDescriptorSupplier("Attach"))
              .build();
        }
      }
    }
    return getAttachMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.AttachOrDetachReq,
      com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> getDetachMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Detach",
      requestType = com.packt.modern.api.grpc.v1.AttachOrDetachReq.class,
      responseType = com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.AttachOrDetachReq,
      com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> getDetachMethod() {
    io.grpc.MethodDescriptor<com.packt.modern.api.grpc.v1.AttachOrDetachReq, com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> getDetachMethod;
    if ((getDetachMethod = SourceServiceGrpc.getDetachMethod) == null) {
      synchronized (SourceServiceGrpc.class) {
        if ((getDetachMethod = SourceServiceGrpc.getDetachMethod) == null) {
          SourceServiceGrpc.getDetachMethod = getDetachMethod =
              io.grpc.MethodDescriptor.<com.packt.modern.api.grpc.v1.AttachOrDetachReq, com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Detach"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.AttachOrDetachReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response.getDefaultInstance()))
              .setSchemaDescriptor(new SourceServiceMethodDescriptorSupplier("Detach"))
              .build();
        }
      }
    }
    return getDetachMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SourceServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SourceServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SourceServiceStub>() {
        @java.lang.Override
        public SourceServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SourceServiceStub(channel, callOptions);
        }
      };
    return SourceServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SourceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SourceServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SourceServiceBlockingStub>() {
        @java.lang.Override
        public SourceServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SourceServiceBlockingStub(channel, callOptions);
        }
      };
    return SourceServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SourceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SourceServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SourceServiceFutureStub>() {
        @java.lang.Override
        public SourceServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SourceServiceFutureStub(channel, callOptions);
        }
      };
    return SourceServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * It allows to add various payment sources (read methods) for customers.
   * </pre>
   */
  public static abstract class SourceServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Creates a new source object.
     * </pre>
     */
    public void create(com.packt.modern.api.grpc.v1.CreateSourceReq request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.CreateSourceReq.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves an existing source object.
     * </pre>
     */
    public void retrieve(com.packt.modern.api.grpc.v1.SourceId request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.SourceId.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRetrieveMethod(), responseObserver);
    }

    /**
     * <pre>
     * Updates the specified source by setting the values of the parameters passed. Any parameters not provided will be left unchanged.
     * </pre>
     */
    public void update(com.packt.modern.api.grpc.v1.UpdateSourceReq request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.UpdateSourceReq.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Attaches a Source object to a Customer. The source must be in a chargeable or pending state.
     * </pre>
     */
    public void attach(com.packt.modern.api.grpc.v1.AttachOrDetachReq request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAttachMethod(), responseObserver);
    }

    /**
     * <pre>
     * Detaches a Source object from a Customer. The status of a source is changed to consumed when it is detached and it can no longer be used to create a charge.
     * </pre>
     */
    public void detach(com.packt.modern.api.grpc.v1.AttachOrDetachReq request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDetachMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.packt.modern.api.grpc.v1.CreateSourceReq,
                com.packt.modern.api.grpc.v1.CreateSourceReq.Response>(
                  this, METHODID_CREATE)))
          .addMethod(
            getRetrieveMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.packt.modern.api.grpc.v1.SourceId,
                com.packt.modern.api.grpc.v1.SourceId.Response>(
                  this, METHODID_RETRIEVE)))
          .addMethod(
            getUpdateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.packt.modern.api.grpc.v1.UpdateSourceReq,
                com.packt.modern.api.grpc.v1.UpdateSourceReq.Response>(
                  this, METHODID_UPDATE)))
          .addMethod(
            getAttachMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.packt.modern.api.grpc.v1.AttachOrDetachReq,
                com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response>(
                  this, METHODID_ATTACH)))
          .addMethod(
            getDetachMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.packt.modern.api.grpc.v1.AttachOrDetachReq,
                com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response>(
                  this, METHODID_DETACH)))
          .build();
    }
  }

  /**
   * <pre>
   * It allows to add various payment sources (read methods) for customers.
   * </pre>
   */
  public static final class SourceServiceStub extends io.grpc.stub.AbstractAsyncStub<SourceServiceStub> {
    private SourceServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SourceServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SourceServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Creates a new source object.
     * </pre>
     */
    public void create(com.packt.modern.api.grpc.v1.CreateSourceReq request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.CreateSourceReq.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves an existing source object.
     * </pre>
     */
    public void retrieve(com.packt.modern.api.grpc.v1.SourceId request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.SourceId.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRetrieveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Updates the specified source by setting the values of the parameters passed. Any parameters not provided will be left unchanged.
     * </pre>
     */
    public void update(com.packt.modern.api.grpc.v1.UpdateSourceReq request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.UpdateSourceReq.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Attaches a Source object to a Customer. The source must be in a chargeable or pending state.
     * </pre>
     */
    public void attach(com.packt.modern.api.grpc.v1.AttachOrDetachReq request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAttachMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Detaches a Source object from a Customer. The status of a source is changed to consumed when it is detached and it can no longer be used to create a charge.
     * </pre>
     */
    public void detach(com.packt.modern.api.grpc.v1.AttachOrDetachReq request,
        io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDetachMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * It allows to add various payment sources (read methods) for customers.
   * </pre>
   */
  public static final class SourceServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SourceServiceBlockingStub> {
    private SourceServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SourceServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SourceServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Creates a new source object.
     * </pre>
     */
    public com.packt.modern.api.grpc.v1.CreateSourceReq.Response create(com.packt.modern.api.grpc.v1.CreateSourceReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves an existing source object.
     * </pre>
     */
    public com.packt.modern.api.grpc.v1.SourceId.Response retrieve(com.packt.modern.api.grpc.v1.SourceId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRetrieveMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Updates the specified source by setting the values of the parameters passed. Any parameters not provided will be left unchanged.
     * </pre>
     */
    public com.packt.modern.api.grpc.v1.UpdateSourceReq.Response update(com.packt.modern.api.grpc.v1.UpdateSourceReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Attaches a Source object to a Customer. The source must be in a chargeable or pending state.
     * </pre>
     */
    public com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response attach(com.packt.modern.api.grpc.v1.AttachOrDetachReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAttachMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Detaches a Source object from a Customer. The status of a source is changed to consumed when it is detached and it can no longer be used to create a charge.
     * </pre>
     */
    public com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response detach(com.packt.modern.api.grpc.v1.AttachOrDetachReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDetachMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * It allows to add various payment sources (read methods) for customers.
   * </pre>
   */
  public static final class SourceServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SourceServiceFutureStub> {
    private SourceServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SourceServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SourceServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Creates a new source object.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.packt.modern.api.grpc.v1.CreateSourceReq.Response> create(
        com.packt.modern.api.grpc.v1.CreateSourceReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves an existing source object.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.packt.modern.api.grpc.v1.SourceId.Response> retrieve(
        com.packt.modern.api.grpc.v1.SourceId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRetrieveMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Updates the specified source by setting the values of the parameters passed. Any parameters not provided will be left unchanged.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.packt.modern.api.grpc.v1.UpdateSourceReq.Response> update(
        com.packt.modern.api.grpc.v1.UpdateSourceReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Attaches a Source object to a Customer. The source must be in a chargeable or pending state.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> attach(
        com.packt.modern.api.grpc.v1.AttachOrDetachReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAttachMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Detaches a Source object from a Customer. The status of a source is changed to consumed when it is detached and it can no longer be used to create a charge.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response> detach(
        com.packt.modern.api.grpc.v1.AttachOrDetachReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDetachMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_RETRIEVE = 1;
  private static final int METHODID_UPDATE = 2;
  private static final int METHODID_ATTACH = 3;
  private static final int METHODID_DETACH = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SourceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SourceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((com.packt.modern.api.grpc.v1.CreateSourceReq) request,
              (io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.CreateSourceReq.Response>) responseObserver);
          break;
        case METHODID_RETRIEVE:
          serviceImpl.retrieve((com.packt.modern.api.grpc.v1.SourceId) request,
              (io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.SourceId.Response>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((com.packt.modern.api.grpc.v1.UpdateSourceReq) request,
              (io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.UpdateSourceReq.Response>) responseObserver);
          break;
        case METHODID_ATTACH:
          serviceImpl.attach((com.packt.modern.api.grpc.v1.AttachOrDetachReq) request,
              (io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response>) responseObserver);
          break;
        case METHODID_DETACH:
          serviceImpl.detach((com.packt.modern.api.grpc.v1.AttachOrDetachReq) request,
              (io.grpc.stub.StreamObserver<com.packt.modern.api.grpc.v1.AttachOrDetachReq.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SourceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SourceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.packt.modern.api.grpc.v1.PaymentGatewayService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SourceService");
    }
  }

  private static final class SourceServiceFileDescriptorSupplier
      extends SourceServiceBaseDescriptorSupplier {
    SourceServiceFileDescriptorSupplier() {}
  }

  private static final class SourceServiceMethodDescriptorSupplier
      extends SourceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SourceServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SourceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SourceServiceFileDescriptorSupplier())
              .addMethod(getCreateMethod())
              .addMethod(getRetrieveMethod())
              .addMethod(getUpdateMethod())
              .addMethod(getAttachMethod())
              .addMethod(getDetachMethod())
              .build();
        }
      }
    }
    return result;
  }
}
