//package com.project.bookwash.service;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import com.project.bookwash.config.MessagingConfig;
//import com.project.bookwash.model.BookingStatus;
//
//
//
//@Component
//public class User {
//
//	@RabbitListener(queues=MessagingConfig.QUEUE1)
//	public void consumeMessageFromQueue(BookingStatus orderStatus) {
//		System.out.println("Message received from queue "+orderStatus);
//	}
//}
