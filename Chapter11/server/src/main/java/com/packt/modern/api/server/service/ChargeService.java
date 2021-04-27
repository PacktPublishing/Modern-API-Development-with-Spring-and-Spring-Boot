package com.packt.modern.api.server.service;

import com.packt.modern.api.grpc.v1.CaptureChargeReq;
import com.packt.modern.api.grpc.v1.Charge;
import com.packt.modern.api.grpc.v1.ChargeId;
import com.packt.modern.api.grpc.v1.ChargeServiceGrpc.ChargeServiceImplBase;
import com.packt.modern.api.grpc.v1.CreateChargeReq;
import com.packt.modern.api.grpc.v1.CustomerId;
import com.packt.modern.api.grpc.v1.UpdateChargeReq;
import com.packt.modern.api.server.repository.ChargeRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter11-server - Modern API Development with Spring and Spring Boot
 **/
@Service
public class ChargeService extends ChargeServiceImplBase {

  private final ChargeRepository repository;

  public ChargeService(ChargeRepository repository) {
    this.repository = repository;
  }

  @Override
  public void create(CreateChargeReq req, StreamObserver<CreateChargeReq.Response> resObserver) {
    CreateChargeReq.Response resp = repository.create(req);
    resObserver.onNext(resp);
    resObserver.onCompleted();
  }

  @Override
  public void retrieve(ChargeId chargeId, StreamObserver<ChargeId.Response> resObserver) {
    ChargeId.Response resp = repository.retrieve(chargeId.getId());
    resObserver.onNext(resp);
    resObserver.onCompleted();
  }

  @Override
  public void update(UpdateChargeReq req, StreamObserver<UpdateChargeReq.Response> resObserver) {
    UpdateChargeReq.Response resp = repository.update(req);
    resObserver.onNext(resp);
    resObserver.onCompleted();
  }

  @Override
  public void capture(CaptureChargeReq req, StreamObserver<CaptureChargeReq.Response> resObserver) {
    CaptureChargeReq.Response resp = repository.capture(req);
    resObserver.onNext(resp);
    resObserver.onCompleted();
  }

  @Override
  public void retrieveAll(CustomerId customerId, StreamObserver<CustomerId.Response> resObserver) {
    CustomerId.Response resp = repository.retrieveAll(customerId.getId());
    resObserver.onNext(resp);
    resObserver.onCompleted();
  }
}
