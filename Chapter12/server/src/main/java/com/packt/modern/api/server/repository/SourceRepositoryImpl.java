package com.packt.modern.api.server.repository;

import com.packt.modern.api.grpc.v1.AttachOrDetachReq;
import com.packt.modern.api.grpc.v1.CreateSourceReq;
import com.packt.modern.api.grpc.v1.Source;
import com.packt.modern.api.grpc.v1.SourceId;
import com.packt.modern.api.grpc.v1.UpdateSourceReq;
import org.springframework.stereotype.Repository;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter12-server - Modern API Development with Spring and Spring Boot
 **/
@Repository
public class SourceRepositoryImpl implements SourceRepository {

  private DbStore dbStore;

  public SourceRepositoryImpl(DbStore dbStore) {
    this.dbStore = dbStore;
  }

  @Override
  public UpdateSourceReq.Response update(UpdateSourceReq req) {
    return dbStore.updateSource(req);
  }

  @Override
  public CreateSourceReq.Response create(CreateSourceReq req) {
    return dbStore.createSource(req);
  }

  @Override
  public SourceId.Response retrieve(String sourceId) {
    return dbStore.retrieveSource(sourceId);
  }

  @Override
  public AttachOrDetachReq.Response attach(AttachOrDetachReq req) {
    return dbStore.attach(req);
  }

  @Override
  public AttachOrDetachReq.Response detach(AttachOrDetachReq req) {
    return dbStore.detach(req);
  }
}
