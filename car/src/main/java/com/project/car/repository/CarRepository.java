package com.project.car.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.car.model.Car;

@Repository
public interface CarRepository extends MongoRepository<Car,String>{

	Car findCarByCarNumber(String carNumber);


	List<Car> findByOwner(String owner);


	Car findCarById(String id);



}
