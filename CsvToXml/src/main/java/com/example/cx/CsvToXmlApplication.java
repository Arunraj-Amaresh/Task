package com.example.cx;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvToXmlApplication {

	@Autowired
	private DataSource dat;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CsvToXmlApplication.class, args);
		
		
		
		
	}

}
