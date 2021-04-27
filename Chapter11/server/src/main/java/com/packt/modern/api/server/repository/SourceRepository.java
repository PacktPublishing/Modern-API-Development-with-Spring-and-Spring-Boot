package com.packt.modern.api.server.repository;

import com.packt.modern.api.grpc.v1.AttachOrDetachReq;
import com.packt.modern.api.grpc.v1.CreateSourceReq;
import com.packt.modern.api.grpc.v1.SourceId;
import com.packt.modern.api.grpc.v1.UpdateSourceReq;

/**
 * @author : github.com/sharmasourabh
 * @project : chapter11-server - Modern API Development with Spring and Spring Boot
 **/
public interface SourceRepository {

  UpdateSourceReq.Response update(UpdateSourceReq req);

  CreateSourceReq.Response create(CreateSourceReq req);

  SourceId.Response retrieve(String sourceId);

  AttachOrDetachReq.Response attach(AttachOrDetachReq req);

  AttachOrDetachReq.Response detach(AttachOrDetachReq req);
}
