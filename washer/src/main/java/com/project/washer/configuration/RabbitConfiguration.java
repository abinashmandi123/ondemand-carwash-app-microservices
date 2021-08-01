package com.project.washer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.washer.model.User;

@Configuration
public class RabbitConfiguration {
	
	private static final String LISTENER_METHOD = "receiveMessage";

	public final static String QUEUE1="booking_queue";
	
	public final static String QUEUE2="schedule_queue";
	
	public final static String EXCHANGE="booking_exchange";
	
	 
	public final static String ROUTING_KEY1="booking_key";
	
	public final static String ROUTING_KEY2="schedule_key";
	
	@Bean
	 Queue queue1() {
	  return new Queue(QUEUE1, true);
	 }
	
	@Bean
	 Queue queue2() {
	  return new Queue(QUEUE2, true);
	 }


	 @Bean
	 TopicExchange exchange() {
	  return new TopicExchange(EXCHANGE);
	 }

	 @Bean
	 Binding binding1(Queue queue1, TopicExchange exchange) {
	  return BindingBuilder.bind(queue1).to(exchange).with(ROUTING_KEY1);
	 }
	 
	 @Bean
	 Binding binding2(Queue queue2, TopicExchange exchange) {
	  return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY2);
	 }
	 
//	 @Bean
//		public MessageConverter converter() {
//			return new Jackson2JsonMessageConverter();
//		}

//	 @Bean
//	 SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//	  MessageListenerAdapter listenerAdapter) {
//	  SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	  container.setConnectionFactory(connectionFactory);
//	  container.setQueueNames(QUEUE1);
//	  container.setMessageListener(listenerAdapter);
//	  return container;
//	 }
//
//	 @Bean
//	 MessageListenerAdapter listenerAdapter(User user) {
//	  return new MessageListenerAdapter(user, LISTENER_METHOD);
//	 }
}
