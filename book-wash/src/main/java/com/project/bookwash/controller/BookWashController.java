package com.project.bookwash.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.bookwash.VO.ReponseTemplateVO;
import com.project.bookwash.config.MessagingConfig;
import com.project.bookwash.model.BookWash;
import com.project.bookwash.model.BookingStatus;
import com.project.bookwash.model.ScheduleLater;
import com.project.bookwash.service.BookWashService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/bookings")
public class BookWashController {

	@Autowired
	private BookWashService bookWashService;
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/add")
	private BookWash saveBookWash(@RequestBody BookWash bookWash) {
		bookWash.setBookingId(UUID.randomUUID().toString());
		BookingStatus bookingStatus=new BookingStatus(bookWash,"PROCESS","Booking Done");
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY1, bookingStatus);
		return bookWashService.saveBookingDetails(bookWash);
	}
	
	@GetMapping("/")
	private List<BookWash> retrieveAllBookings(){
		return bookWashService.getAllBookings();
	}
	
	@GetMapping("/{id}")
	private Optional<BookWash> getBookingById(@PathVariable String id) {
		return bookWashService.getBooking(id);
	}
	
	@GetMapping("/get/{owner}")
	private List<BookWash> getBookingByOwner(@PathVariable String owner){
		return bookWashService.getBookingByOwner(owner);
	}
	
	@GetMapping("/cars/{carNumber}")
	private ReponseTemplateVO retrieveBooKDetailsWithCarDetails(@PathVariable String carNumber) {
		return bookWashService.getBookDetailsWithCarDetails(carNumber);
		
	}
	
	@PutMapping("/update/{id}")
	private BookWash updateBooking(@PathVariable String id,@RequestBody BookWash bookWash) {
		BookWash booking=bookWashService.getBooking(id).orElseThrow(()->new RuntimeException("Cannot fetch booking"));
		booking.setCarNumber(bookWash.getCarNumber());
		booking.setCarModel(bookWash.getCarModel());
		booking.setOwner(bookWash.getOwner());
		booking.setWashPackage(bookWash.getWashPackage());
		booking.setLocation(bookWash.getLocation());
		booking.setDate(bookWash.getDate());
		booking.setStatus(bookWash.getStatus());
		
		 return bookWashService.saveBookingDetails(booking);
		
		
	}
	
	@DeleteMapping("/delete/{id}")
	private void deleteBookingByBookingId(@PathVariable String id) {
		 bookWashService.deleteBookingById(id);
		
	}
	
	
	@PostMapping("/schedule/add")
	private ScheduleLater addSchedule(@RequestBody ScheduleLater schedule) {
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY2,schedule);
		return bookWashService.addSchedule(schedule);
	}
	
	@GetMapping("/schedule/getall")
	private List<ScheduleLater> getAllSchedules(){
		return bookWashService.getAllSchedules();
	}
	
	@GetMapping("/schedule/get/{id}")
	private Optional<ScheduleLater> getScheduleById(@PathVariable String id) {
		return bookWashService.getScheduleById(id);
	}
	
	@GetMapping("/schedule/getByOwner/{owner}")
	private List<ScheduleLater> getScheduleByOwner(@PathVariable String owner) {
		return bookWashService.getScheduleByOwner(owner);
	}
	
	@PutMapping("/schedule/update/{id}")
	private ScheduleLater updateSchedule(@PathVariable String id,@RequestBody ScheduleLater scheduleLater) {
		ScheduleLater schedule=bookWashService.getScheduleById(id).orElseThrow(()->new RuntimeException("Cannot fetch schedule"));
		schedule.setCarNumber(scheduleLater.getCarNumber());
		schedule.setCarModel(scheduleLater.getCarModel());
		schedule.setOwner(scheduleLater.getOwner());
		schedule.setWashPackage(scheduleLater.getWashPackage());
		schedule.setLocation(scheduleLater.getLocation());
		schedule.setDate(scheduleLater.getDate());
		schedule.setTime(scheduleLater.getTime());
		schedule.setStatus(scheduleLater.getStatus());
		return bookWashService.addSchedule(schedule);
	}
	
	
	@DeleteMapping("/schedule/deleteall")
	private void deleteSchedule() {
		bookWashService.deleteAll();
	}
	
	@DeleteMapping("/schedule/delete/{id}")
	private void deleteScheduleById(@PathVariable String id) {
		bookWashService.deleteScheduleById(id);
	}
}
