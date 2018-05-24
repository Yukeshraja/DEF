package com.altimetrik.def.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.altimetrik.def.model.CDModel;

public interface CdRepository extends MongoRepository<CDModel, String> {

}
