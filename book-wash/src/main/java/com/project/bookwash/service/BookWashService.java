package com.project.bookwash.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.project.bookwash.VO.Car;
import com.project.bookwash.VO.ReponseTemplateVO;
import com.project.bookwash.model.BookWash;
import com.project.bookwash.model.ScheduleLater;
import com.project.bookwash.repository.BookWashRepository;
import com.project.bookwash.repository.ScheduleLaterRepository;



@Service
public class BookWashService {

	final Logger logger=LoggerFactory.getLogger(BookWashService.class);
	
	@Autowired
	private BookWashRepository bookWashRepository;
	
	@Autowired
	private ScheduleLaterRepository scheduleRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public BookWash saveBookingDetails(BookWash bookWash) {
		return bookWashRepository.save(bookWash);
	}

	public ReponseTemplateVO getBookDetailsWithCarDetails(String carNumber) {
		
		ReponseTemplateVO vo=new ReponseTemplateVO();
		BookWash bookWash=bookWashRepository.findBookingsByCarNumber(carNumber);
		Car car=restTemplate.getForObject("http://localhost:8081/cars/"+bookWash.getCarNumber(), Car.class);
		
		vo.setBookWash(bookWash);
		vo.setCar(car);

		return vo;
	}

	public List<BookWash> getAllBookings() {
		
		return bookWashRepository.findAll();
	}

	public Optional<BookWash> getBooking(String id) {
		
		return bookWashRepository.findById(id);
	}

	public void deleteBookingById(String id) {
		
		bookWashRepository.deleteById(id);
	}
	
	
	public ScheduleLater addSchedule(ScheduleLater schedule) {
		logger.info("Inside addSchedule method of BookWashService");
		return scheduleRepository.save(schedule);
		
	}

	public List<ScheduleLater> getAllSchedules() {
		logger.info("Inside getAllSchedules method of BookWashService");
		return scheduleRepository.findAll();
	}

	public Optional<ScheduleLater> getScheduleById(String id) {
		logger.info("Inside getScheduleById method of BookWashService");
		return scheduleRepository.findById(id);
	}

	public void deleteAll() {
		logger.info("Inside deleteAll method of BookWashService");
		scheduleRepository.deleteAll();
	}

	public void deleteScheduleById(String id) {
		logger.info("Inside deleteById method of BookWashService");
		scheduleRepository.deleteById(id);
	}

	public List<BookWash> getBookingByOwner(String owner) {
		
		return bookWashRepository.findBookingByOwner(owner);
	}

	public List<ScheduleLater> getScheduleByOwner(String owner) {
		
		return scheduleRepository.findScheduleByOwner(owner);
	}

	
	
	
}
