package com.myappl.kafka.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.myappl"})
public class ApplicationMain {

	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}
	
}
