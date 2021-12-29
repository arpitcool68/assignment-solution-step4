package com.stackroute.newz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.stackroute.newz.repository")
public class NewsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsServiceApplication.class, args);
	}

}
