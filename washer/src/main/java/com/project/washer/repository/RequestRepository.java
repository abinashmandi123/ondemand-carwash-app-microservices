package com.project.washer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.washer.model.Request;

@Repository
public interface RequestRepository extends MongoRepository<Request,String>{

}
