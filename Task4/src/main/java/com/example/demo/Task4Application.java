package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Task4Application {

	public static void main(String[] args) {
		SpringApplication.run(Task4Application.class, args);
	}

    @Bean
    RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	 
	
	
	
	
	
	
}
