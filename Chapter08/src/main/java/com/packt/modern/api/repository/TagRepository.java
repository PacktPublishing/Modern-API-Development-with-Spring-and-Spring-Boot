package com.packt.modern.api.repository;

import com.packt.modern.api.entity.TagEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;


/**
 * @author : github.com/sharmasourabh
 * @project : Chapter08 - Modern API Development with Spring and Spring Boot
 **/
public interface TagRepository extends CrudRepository<TagEntity, UUID> {
}
