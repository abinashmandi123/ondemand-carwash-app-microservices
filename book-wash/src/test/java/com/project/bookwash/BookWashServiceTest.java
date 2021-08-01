package com.project.bookwash;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

import com.project.bookwash.model.BookWash;
import com.project.bookwash.model.ScheduleLater;
import com.project.bookwash.repository.BookWashRepository;
import com.project.bookwash.repository.ScheduleLaterRepository;
import com.project.bookwash.service.BookWashService;


public class BookWashServiceTest {

	@InjectMocks
	private BookWashService bookWashService;
	
	@Mock
	private BookWashRepository bookWashRepository;
	
	@Mock
	private ScheduleLaterRepository scheduleRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void saveBookingsTest() {
		BookWash booking=new BookWash("102","MH45689","Hyundai Creta","Abinash Mandi","Regular","Mumbai,Maharashtra","July 21","Accepted");
		
		when(bookWashRepository.save(booking)).thenAnswer(invocation->invocation.getArgument(0));
		
		BookWash savedBooking=bookWashService.saveBookingDetails(booking);
		
		Assertions.assertNotNull(savedBooking, "savedBooking is not null");
		
		verify(bookWashRepository).save(any(BookWash.class));
	}
	
	@Test
	public void getAllBookingsTest() {
		List<BookWash> bookings=new ArrayList<BookWash>();
		BookWash booking1=new BookWash("102","MH45689","Hyundai Creta","Abinash Mandi","Regular","Mumbai,Maharashtra","July 21","Accepted");
		BookWash booking2=new BookWash("102","MH45689","Hyundai Creta","Abinash Mandi","Regular","Mumbai,Maharashtra","July 21","Accepted");
		BookWash booking3=new BookWash("102","MH45689","Hyundai Creta","Abinash Mandi","Regular","Mumbai,Maharashtra","July 21","Accepted");
		
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		
		when(bookWashRepository.findAll()).thenReturn(bookings);
		
		List<BookWash> expected=bookWashService.getAllBookings();
		
		Assertions.assertEquals(expected,bookings);
	}
	
	


	@Test
	public void getBookingsByIdTest() {
		BookWash booking=new BookWash("102","MH45689","Hyundai Creta","Abinash Mandi","Regular","Mumbai,Maharashtra","July 21","Accepted");
		Mockito.when(bookWashRepository.findById("1")).thenReturn(Optional.ofNullable(booking));
		
		Optional<BookWash> result=bookWashService.getBooking("1");
		
		Assertions.assertEquals(booking.getCarNumber(),result.get().getCarNumber());
		
	}
	
	@Test
	public void deleteBookingByIdTest() {
		String bookingId="101";
		
		bookWashService.deleteBookingById(bookingId);
		bookWashService.deleteBookingById(bookingId);
	
		verify(bookWashRepository,times(2)).deleteById(bookingId);
	}
	
	@Test
	public void addScheduleTest() {
		LocalDate date=LocalDate.parse("2021-07-17");
		LocalTime time=LocalTime.parse("10:45:00");
		
		ScheduleLater schedule=new ScheduleLater("1111","WB87654","Hyundai Creta","abinash","Regular","Durgapur,West Bengal",date,time,"Accepted");
		
		when(scheduleRepository.save(schedule)).thenAnswer(a -> a.getArgument(0));
		
		ScheduleLater savedSchedule=bookWashService.addSchedule(schedule);
		
		Assertions.assertNotNull(savedSchedule);
		
		verify(scheduleRepository).save(any(ScheduleLater.class));
	}
	
	@Test
	public void getAllSchedulesTest() {
		List<ScheduleLater> schedules=new ArrayList<ScheduleLater>();
		
		LocalDate date=LocalDate.parse("2021-07-17");
		LocalTime time=LocalTime.parse("10:45:00");
		
		schedules.add(new ScheduleLater("1111","WB87654","Hyundai Creta","abinash","Regular","Durgapur,West Bengal",date,time,"Accepted"));
		schedules.add(new ScheduleLater("1111","WB87654","Hyundai Creta","abinash","Regular","Durgapur,West Bengal",date,time,"Accepted"));
		
		when(scheduleRepository.findAll()).thenReturn(schedules);
		
		List<ScheduleLater> expected=bookWashService.getAllSchedules();
		
		Assertions.assertEquals(expected, schedules);
		
	}

	@Test
	public void getScheduleById() {
		LocalDate date=LocalDate.parse("2021-07-17");
		LocalTime time=LocalTime.parse("10:45:00");
		
		ScheduleLater schedule=new ScheduleLater("1111","WB87654","Hyundai Creta","abinash","Regular","Durgapur,West Bengal",date,time,"Accepted");
		
		when(scheduleRepository.findById("1212")).thenReturn(Optional.ofNullable(schedule));
		
		Optional<ScheduleLater> result=bookWashService.getScheduleById("1212");
		
		Assertions.assertEquals(schedule.getWashPackage(), result.get().getWashPackage());
	}
	
	@Test
	public void deleteScheduleByIdTest() {
		String id="1111";
		
		bookWashService.deleteScheduleById(id);
		bookWashService.deleteScheduleById(id);
	
		verify(scheduleRepository,times(2)).deleteById(id);
	}
}
