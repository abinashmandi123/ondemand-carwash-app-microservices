package com.project.car.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.car.model.Car;
import com.project.car.service.CarService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarService carService;
	
	
	@PostMapping(value="/add",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	private ResponseEntity<String> addCar(@RequestPart("carNumber") String carNumber,@RequestPart("owner") String owner,@RequestPart("carType") String carType,@RequestPart("carModel") String carModel,@RequestPart("carImage") MultipartFile carImage) throws IOException{	
		Car car1=new Car();
		car1.setCarNumber(carNumber);
		car1.setOwner(owner);
		car1.setCarType(carType);
		car1.setCarModel(carModel);
		car1.setCarImage(new Binary(BsonBinarySubType.BINARY,carImage.getBytes()));
		
		try {
			carService.saveCar(car1);
		}catch(Exception e) {
			return ResponseEntity.ok("Error during saving car "+carNumber);
		}
		
		
		
		return ResponseEntity.ok("car "+carNumber+" is succesfully added");
		
	}
	
	@GetMapping("/get")
	private List<Car> getAllCars(){
		return carService.getAllCar();
	}
	
	@GetMapping("/getByCarNumber/{carNumber}")
	private Car findCar(@PathVariable String carNumber) {
		return carService.findCarByCarNumber(carNumber);
		
	}
	
	@GetMapping("/{id}")
	private Optional<Car> findCarById(@PathVariable String id) {
		return carService.findCarById(id);
		
	}
	
	@GetMapping("/getcars/{username}")
	private List<Car> findCarByOwner(@PathVariable("username") String owner){
		return carService.findAllCar(owner);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<String> updateCarById(@PathVariable String id,@RequestPart("carNumber") String carNumber,@RequestPart("owner") String owner,@RequestPart("carType") String carType,@RequestPart("carModel") String carModel,@RequestPart("carImage") MultipartFile carImage) throws IOException {
		Car car=carService.updateCarById(id);
		car.setCarNumber(carNumber);
		car.setOwner(owner);
		car.setCarType(carType);
		car.setCarModel(carModel);
		car.setCarImage(new Binary(BsonBinarySubType.BINARY,carImage.getBytes()));
		try {
			carService.saveCar(car);
		}catch(Exception e) {
			return ResponseEntity.ok("Error during updating car "+car.getCarNumber());
		}
		
		
		return ResponseEntity.ok("Succesful updation for car "+car.getCarNumber());
	}
	
	@PutMapping("/update/{carNumber}")
	private ResponseEntity<String> updateCarByCarNumber(@PathVariable String carNumber,@RequestPart("owner") String owner,@RequestPart("carType") String carType,@RequestPart("carModel") String carModel,@RequestPart("carImage") MultipartFile carImage) throws IOException {
		Car car=carService.updateCarByCarNumber(carNumber);
		car.setCarNumber(carNumber);
		car.setOwner(owner);
		car.setCarType(carType);
		car.setCarModel(carModel);
		car.setCarImage(new Binary(BsonBinarySubType.BINARY,carImage.getBytes()));
		try {
			carService.saveCar(car);
		}catch(Exception e) {
			return ResponseEntity.ok("Error during updating car "+car.getCarNumber());
		}
		
		
		return ResponseEntity.ok("Succesful updation for car "+car.getCarNumber());
	}
	
	@DeleteMapping("/deleteall")
	private void deleteAllCars() {
		carService.deleteAllCars();
	}
	
	@DeleteMapping("/delete/{id}")
	private void deleteCarById(@PathVariable("id") String id) {
		carService.deleteCarById(id);
	}
}