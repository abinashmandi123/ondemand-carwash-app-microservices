package com.project.washer.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.washer.model.Washer;

@Repository
public interface WasherRepository extends MongoRepository<Washer,String>{

	Washer findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);


}
