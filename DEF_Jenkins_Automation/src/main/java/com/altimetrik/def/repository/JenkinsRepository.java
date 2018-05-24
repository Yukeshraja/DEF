package com.altimetrik.def.repository;

import org.json.JSONObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.def.model.Def;

@Repository
public interface JenkinsRepository extends MongoRepository<Def, String> {
}
