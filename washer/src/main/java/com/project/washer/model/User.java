package com.project.washer.model;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import com.project.washer.configuration.RabbitConfiguration;



@Component
public class User {

	@RabbitListener(queues=RabbitConfiguration.QUEUE1)
	public void bookingQueue(BookingStatus orderStatus) {
		System.out.println("Message received from queue "+orderStatus);
	}
	
	@RabbitListener(queues=RabbitConfiguration.QUEUE2)
	public void scheduleQueue(ScheduleLater schedule) {
		System.out.println("Message received from queue "+schedule);
	}
}
