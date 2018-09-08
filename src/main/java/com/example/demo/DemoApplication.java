package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.example.model.ApplicationProperties;

@SpringBootApplication
@ComponentScan(basePackages= {"com.example"})
@EnableMongoRepositories(basePackages= {"com.example.repository"})
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Override
	public void run(String...args) throws Exception{
		System.out.println("Application Running on Port :"+applicationProperties.getPort());

	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
}
