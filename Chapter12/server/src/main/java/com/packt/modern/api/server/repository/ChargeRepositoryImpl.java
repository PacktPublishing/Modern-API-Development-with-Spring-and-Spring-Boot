package com.packt.modern.api.server.repository;

import com.packt.modern.api.grpc.v1.CaptureChargeReq;
import com.packt.modern.api.grpc.v1.ChargeId;
import com.packt.modern.api.grpc.v1.CreateChargeReq;
import com.packt.modern.api.grpc.v1.CustomerId;
import com.packt.modern.api.grpc.v1.UpdateChargeReq;
import org.springframework.stereotype.Repository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter12-server - Modern API Development with Spring and Spring Boot
 **/
@Repository
public class ChargeRepositoryImpl implements ChargeRepository {

  private DbStore dbStore;

  public ChargeRepositoryImpl(DbStore dbStore) {
    this.dbStore = dbStore;
  }

  @Override
  public CreateChargeReq.Response create(CreateChargeReq req) {
    return dbStore.createCharge(req);
  }

  @Override
  public UpdateChargeReq.Response update(UpdateChargeReq req) {
    return dbStore.updateCharge(req);
  }

  @Override
  public ChargeId.Response retrieve(String chargeId) {
    return dbStore.retrieveCharge(chargeId);
  }

  @Override
  public CaptureChargeReq.Response capture(CaptureChargeReq req) {
    return dbStore.captureCharge(req);
  }

  @Override
  public CustomerId.Response retrieveAll(String customerId) {
    return dbStore.retrieveAllCharges(customerId);
  }
}
