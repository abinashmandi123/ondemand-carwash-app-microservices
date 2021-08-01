package com.project.car;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.car.model.Car;
import com.project.car.repository.CarRepository;
import com.project.car.service.CarService;

public class CarServiceTest {

	@InjectMocks
	private CarService carService;
	
	@Mock
	private CarRepository carRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveCarTest() {
//		MockMultipartFile file=new MockMultipartFile("file","hello.txt",MediaType.TEXT_PLAIN_VALUE,"Hello,World!".getBytes());
		Binary file=new Binary(BsonBinarySubType.BINARY,"Hello World".getBytes());
		Car car=new Car("101","WB7834","Abinash Mandi","SUV","Mahindra XUV",file);
	
		when(carRepository.save(car)).thenAnswer(invocation->invocation.getArgument(0));
		
		Car savedCar=carService.saveCar(car);
		
		Assertions.assertNotNull(savedCar);
		
		verify(carRepository).save(any(Car.class));
		
		
	}
	
	@Test
	public void getAllCarsTest() {
		List<Car> cars=new ArrayList<Car>();
//		MockMultipartFile file=new MockMultipartFile("file","hello.txt",MediaType.TEXT_PLAIN_VALUE,"Hello,World!".getBytes());
		Binary file=new Binary(BsonBinarySubType.BINARY,"Hello World".getBytes());
		Car car1=new Car("101","WB90853","Mohit","SUV","Mahindra XUV",file);
		Car car2=new Car("102","WB90853","Mohit","SUV","Mahindra XUV",file);
		
		cars.add(car1);
		cars.add(car2);
		
		when(carRepository.findAll()).thenReturn(cars);
		
		List<Car> expected=carService.getAllCar();
		
		Assertions.assertEquals(expected, cars);
		
		
	}
	
	@Test
	public void findCarByCarNumberTest() {
		String carNumber="WB67845";
//		MockMultipartFile file=new MockMultipartFile("file","hello.txt",MediaType.TEXT_PLAIN_VALUE,"Hello,World!".getBytes());
		Binary file=new Binary(BsonBinarySubType.BINARY,"Hello World".getBytes());
		 Car car=new Car("101",carNumber,"Suresh","Sedan","Hyundai Verna",file);
		
		when(carRepository.findCarByCarNumber("WB67845")).thenReturn(car);
		
		Car expected=carService.findCarByCarNumber(carNumber);
		
		Assertions.assertNotNull(expected);
		
		
	}
	
	@Test
	public void deleteCarByIdTest() {
		String id="111";
		
		carService.deleteCarById(id);
		carService.deleteCarById(id);
		
		verify(carRepository,times(2)).deleteById(id);
	}
}
