package com.project.washer;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WasherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WasherApplication.class, args);
	}
	
	 @Bean
	    public Jackson2JsonMessageConverter converter() {
	        return new Jackson2JsonMessageConverter();
	    }

}
