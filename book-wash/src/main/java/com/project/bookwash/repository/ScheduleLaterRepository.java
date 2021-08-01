package com.project.bookwash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.bookwash.model.ScheduleLater;



@Repository
public interface ScheduleLaterRepository extends MongoRepository<ScheduleLater,String>{

	List<ScheduleLater> findScheduleByOwner(String owner);

}
