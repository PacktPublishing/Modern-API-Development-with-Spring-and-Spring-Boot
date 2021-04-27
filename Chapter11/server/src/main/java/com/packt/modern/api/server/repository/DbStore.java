package com.packt.modern.api.server.repository;

import com.google.rpc.Code;
import com.packt.modern.api.grpc.v1.Address;
import com.packt.modern.api.grpc.v1.AttachOrDetachReq;
import com.packt.modern.api.grpc.v1.CaptureChargeReq;
import com.packt.modern.api.grpc.v1.Charge;
import com.packt.modern.api.grpc.v1.ChargeId;
import com.packt.modern.api.grpc.v1.CreateChargeReq;
import com.packt.modern.api.grpc.v1.CreateSourceReq;
import com.packt.modern.api.grpc.v1.CustomerId;
import com.packt.modern.api.grpc.v1.Flow;
import com.packt.modern.api.grpc.v1.Owner;
import com.packt.modern.api.grpc.v1.Receiver;
import com.packt.modern.api.grpc.v1.Receiver.RefundAttributeStatus;
import com.packt.modern.api.grpc.v1.Receiver.RefundAttributesMethod;
import com.packt.modern.api.grpc.v1.Source;
import com.packt.modern.api.grpc.v1.Source.Status;
import com.packt.modern.api.grpc.v1.SourceId;
import com.packt.modern.api.grpc.v1.UpdateChargeReq;
import com.packt.modern.api.grpc.v1.UpdateSourceReq;
import com.packt.modern.api.grpc.v1.Usage;
import io.grpc.protobuf.StatusProto;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter11-server - Modern API Development with Spring and Spring Boot
 **/
@Component
public class DbStore {

  private static final Map<String, Source> sourceEntities = new ConcurrentHashMap<>();
  private static final Map<String, Charge> chargeEntities = new ConcurrentHashMap<>();

  public DbStore() {
    // Seed Source for testing
    Source source = Source.newBuilder().setId(RandomHolder.randomKey()).setType("card")
        .setAmount(100).setOwner(createOwner()).setReceiver(createReceiver())
        .setCurrency("USD").setStatementDescriptor("Statement Descriptor")
        .setFlow(Flow.RECEIVER).setUsage(Usage.REUSABLE).setCreated(Instant.now().getEpochSecond())
        .build();
    sourceEntities.put(source.getId(), source);
    // Seed Charge for testing
    Charge charge = Charge.newBuilder().setId(RandomHolder.randomKey()).setAmount(1000)
        .setCurrency("USD").setCustomerId("ab1ab2ab3ab4ab5")
        .setDescription("Charge Description").setReceiptEmail("receipt@email.com")
        .setStatementDescriptor("Statement Descriptor").setSourceId(source.getId())
        .setCreated(Instant.now().getEpochSecond()).build();
    chargeEntities.put(charge.getId(), charge);
  }

  public CreateSourceReq.Response createSource(CreateSourceReq req) {
    // validate request object
    // Owner and receiver should be taken from req. in the form of ID
    Source source = Source.newBuilder().setId(RandomHolder.randomKey()).setType(req.getType())
        .setAmount(req.getAmount()).setOwner(createOwner()).setReceiver(createReceiver())
        .setCurrency(req.getCurrency()).setStatementDescriptor(req.getStatementDescriptor())
        .setFlow(req.getFlow()).setUsage(req.getUsage()).setCreated(Instant.now().getEpochSecond())
        .build();
    sourceEntities.put(source.getId(), source);
    return CreateSourceReq.Response.newBuilder().setSource(source).build();
  }

  public UpdateSourceReq.Response updateSource(UpdateSourceReq req) {
    Source source = sourceEntities.get(req.getSourceId());
    if (Objects.isNull(source)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Requested source is not available")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    source = source.toBuilder().setAmount(req.getAmount()).setOwner(req.getOwner()).build();
    sourceEntities.put(source.getId(), source);
    return UpdateSourceReq.Response.newBuilder().setSource(source).build();
  }

  public SourceId.Response retrieveSource(String sourceId) {
    if (Strings.isBlank(sourceId)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Invalid Source ID is passed.")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    Source source = sourceEntities.get(sourceId);
    if (Objects.isNull(source)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Requested source is not available")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    return SourceId.Response.newBuilder().setSource(source).build();
  }

  public AttachOrDetachReq.Response attach(AttachOrDetachReq req) {
    Source source = sourceEntities.get(req.getSourceId());
    if (Objects.isNull(source)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Requested source is not available")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    Status status = source.getStatus();
    if (status.equals(Status.CHARGEABLE) || status.equals(Status.PENDING)) {
      int amt = source.getAmount();
      source = source.toBuilder().setAmount(0).setStatus(Status.CHARGEABLE).build();
      sourceEntities.put(source.getId(), source);
    }
    return AttachOrDetachReq.Response.newBuilder().setSource(source).build();
  }

  public AttachOrDetachReq.Response detach(AttachOrDetachReq req) {
    Source source = sourceEntities.get(req.getSourceId());
    if (Objects.isNull(source)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Requested source is not available")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    int amt = source.getAmount();
    source = source.toBuilder().setAmount(0).setStatus(Status.CONSUMED).build();
    sourceEntities.put(source.getId(), source);
    return AttachOrDetachReq.Response.newBuilder().setSource(source).build();
  }

  private Owner createOwner() {
    Address address = Address.newBuilder().setNumber("F-31").setResidency("Residency")
        .setStreet("Street").setCity("City").setState("State").setCountry("Country")
        .setPostalCode("12345").build();
    return Owner.newBuilder().setName("Scott").setAddress(address)
        .setEmail("scott@packt.modern.api")
        .setPhone("9999999999").build();
  }

  private Receiver createReceiver() {
    return Receiver.newBuilder().setAddress("121042882-38381234567890123").setAmountReceived(0)
        .setAmountCharged(0).setAmountReturned(0).setRefundAttributesMethod(
            RefundAttributesMethod.EMAIL).setRefundAttributesStatus(RefundAttributeStatus.MISSING)
        .build();
  }

  public CreateChargeReq.Response createCharge(CreateChargeReq req) {
    Charge charge = Charge.newBuilder().setId(RandomHolder.randomKey()).setAmount(req.getAmount())
        .setCurrency(req.getCurrency()).setCustomerId(req.getCustomerId())
        .setDescription(req.getDescription()).setReceiptEmail(req.getReceiptEmail())
        .setStatementDescriptor(req.getStatementDescriptor()).setSourceId(req.getSourceId())
        .setCreated(Instant.now().getEpochSecond()).build();
    chargeEntities.put(charge.getId(), charge);
    return CreateChargeReq.Response.newBuilder().setCharge(charge).build();
  }

  public UpdateChargeReq.Response updateCharge(UpdateChargeReq req) {
    Charge charge = chargeEntities.get(req.getChargeId());
    if (Objects.isNull(charge)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Requested Charge is not available")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    Charge.Builder builder = charge.toBuilder();
    boolean chargeUpdated = false;
    String customerId = req.getCustomerId();
    if (Strings.isNotBlank(customerId)) {
      builder.setCustomerId(customerId); // assume its dollar, else add logic for Yen
      chargeUpdated = true;
    }
    String email = charge.getReceiptEmail();
    if (Strings.isNotBlank(email)) {
      builder.setReceiptEmail(email);
      chargeUpdated = true;
    }
    String desc = charge.getDescription();
    if (Strings.isNotBlank(desc)) {
      builder.setDescription(desc);
      chargeUpdated = true;
    }
    if (chargeUpdated) {
      charge = builder.build();
      chargeEntities.put(charge.getId(), charge);
    }
    return UpdateChargeReq.Response.newBuilder().setCharge(charge).build();
  }

  public CaptureChargeReq.Response captureCharge(CaptureChargeReq req) {
    Charge charge = chargeEntities.get(req.getChargeId());
    if (Objects.isNull(charge)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Requested Charge is not available")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    Charge.Builder builder = charge.toBuilder();
    boolean chargeUpdated = false;
    int amt = req.getAmount();
    if (amt >= 50) {
      builder.setAmount(amt / 100); // assume its dollar, else add logic for Yen
      chargeUpdated = true;
    }
    String email = charge.getReceiptEmail();
    if (Strings.isNotBlank(email)) {
      builder.setReceiptEmail(email);
      chargeUpdated = true;
    }
    String desc = charge.getStatementDescriptor();
    if (Strings.isNotBlank(desc)) {
      builder.setStatementDescriptor(desc);
      chargeUpdated = true;
    }
    if (chargeUpdated) {
      charge = builder.build();
      chargeEntities.put(charge.getId(), charge);
    }
    return CaptureChargeReq.Response.newBuilder().setCharge(charge).build();
  }

  public ChargeId.Response retrieveCharge(String chargeId) {
    if (Strings.isBlank(chargeId)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Invalid Charge ID is passed")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    Charge charge = chargeEntities.get(chargeId);
    if (Objects.isNull(charge)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Requested Charge is not available")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    return ChargeId.Response.newBuilder().setCharge(charge).build();
  }

  public CustomerId.Response retrieveAllCharges(String customerId) {
    if (Strings.isBlank(customerId)) {
      com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
          .setCode(Code.INVALID_ARGUMENT.getNumber())
          .setMessage("Requested customer ID is either null or empty")
          .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
    CustomerId.Response.Builder builder = CustomerId.Response.newBuilder();
    chargeEntities.entrySet().stream()
        .filter(es -> es.getValue().getCustomerId().equals(customerId))
        .forEach(e -> builder.addCharge(e.getValue()));
    return builder.build();
  }

  // https://stackoverflow.com/a/31214709/109354
  // or can use org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(n)
  private static class RandomHolder {

    static final Random random = new SecureRandom();

    public static String randomKey() {
      return randomKey(32);
    }

    public static String randomKey(int length) {
      return String.format("%" + length + "s", new BigInteger(length * 5/*base 32,2^5*/, random)
          .toString(32)).replace('\u0020', '0');
    }
  }
}
