package com.example.SpringBatch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:batch-config.xml")
public class TaskApplication {
	
	
	 private String message;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TaskApplication.class, args);
      
        context.getBean("schedulerFactory");
 }
    
    
    
    public void setMessage(String message) {
        this.message = message;
    }
   
    
    
    
    
    
    
    
}
