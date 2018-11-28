package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.example.model.ApplicationProperties;

@SpringBootApplication
@ComponentScan(basePackages= {"com.example"})
@EnableMongoRepositories(basePackages= {"com.example.repository"})
@EnableCaching
public class DemoApplication extends SpringBootServletInitializer implements CommandLineRunner {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoApplication.class);
	}

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
