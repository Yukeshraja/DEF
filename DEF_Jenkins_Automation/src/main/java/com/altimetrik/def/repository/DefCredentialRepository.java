package com.altimetrik.def.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.def.model.Credential;




@Repository
public interface DefCredentialRepository extends MongoRepository<Credential, String> {

}
