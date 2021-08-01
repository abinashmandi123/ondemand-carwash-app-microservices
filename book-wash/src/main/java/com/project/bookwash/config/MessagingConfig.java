package com.project.bookwash.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;






@Configuration
public class MessagingConfig {

	
	public final static String QUEUE1="booking_queue";
	
	public final static String QUEUE2="schedule_queue";
	
	public final static String EXCHANGE="booking_exchange";
	
	 
	public final static String ROUTING_KEY1="booking_key";
	
	public final static String ROUTING_KEY2="schedule_key";
	
	@Bean
	public Queue queue1() {
		return new Queue(QUEUE1);
	}
	
	@Bean
	public Queue queue2() {
		return new Queue(QUEUE2);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
		
	}
	
	@Bean
	public Binding binding1(Queue queue1,TopicExchange exchange) {
		return BindingBuilder.bind(queue1).to(exchange).with(ROUTING_KEY1);
		
	}
	
	@Bean
	public Binding binding2(Queue queue2,TopicExchange exchange) {
		return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY2);
		
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}
