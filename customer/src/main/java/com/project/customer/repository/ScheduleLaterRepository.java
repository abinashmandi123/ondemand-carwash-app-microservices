package com.project.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.customer.model.ScheduleLater;

@Repository
public interface ScheduleLaterRepository extends MongoRepository<ScheduleLater,String>{

}
