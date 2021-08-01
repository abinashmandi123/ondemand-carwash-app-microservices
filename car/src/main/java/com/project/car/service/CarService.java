package com.project.car.service;

import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.car.model.Car;
import com.project.car.repository.CarRepository;

@Service
public class CarService {

	final Logger logger=LoggerFactory.getLogger(Car.class);
	
	@Autowired
	private CarRepository carRepository;

	public Car saveCar(Car car1) {
		logger.info("Inside saveCar method of CarService");
		return carRepository.save(car1);
		
	}

	public Car findCarByCarNumber(String carNumber) {
		logger.info("Inside findCarByCarNumber method of CarService");
		return carRepository.findCarByCarNumber(carNumber);
	}
	
	public List<Car> getAllCar(){
		logger.info("Inside getAllCar method of CarService");
		return carRepository.findAll();
	}
	
	public List<Car> findAllCar(String owner) {
		logger.info("Inside findAllCar method of CarService");
		return carRepository.findByOwner(owner);
	}

	public void deleteAllCars() {
		logger.info("Inside deleteAllCars method of CarService");
		carRepository.deleteAll();
	}

	public void deleteCarById(String id) {
		logger.info("Inside deleteById method of CarService");
		carRepository.deleteById(id);
	}

	public Car updateCarById(String id) {
		logger.info("Inside updateCarById method of CarService");
		Car car= carRepository.findCarById(id);
		
		return car;
	}

	public Optional<Car> findCarById(String id) {
		return carRepository.findById(id);
	}

	public Car updateCarByCarNumber(String carNumber) {
		logger.info("Inside updateCarByCarNumber method of CarService");
		return carRepository.findCarByCarNumber(carNumber);
	}
	
}
