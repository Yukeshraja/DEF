package com.altimetrik.def.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.altimetrik.def.model.CIModel;

public interface CiRepository extends MongoRepository<CIModel, String> {

}
