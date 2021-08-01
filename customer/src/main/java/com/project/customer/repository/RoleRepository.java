package com.project.customer.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.customer.model.ERole;
import com.project.customer.model.Role;



@Repository
public interface RoleRepository extends MongoRepository<Role,String>{

	Optional<Role> findByName(ERole name);
}
