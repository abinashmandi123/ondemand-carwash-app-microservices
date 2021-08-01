package com.project.customer.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.customer.model.Car;
import com.project.customer.model.Customer;


@Repository
public interface CustomerRepository extends MongoRepository<Customer,String>{

	Customer findByUsername(String username);

	void save(Car car1);

	Customer findCarByUsername(String username);

	void deleteById(String id);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Optional<Customer> findById(String id);

}
