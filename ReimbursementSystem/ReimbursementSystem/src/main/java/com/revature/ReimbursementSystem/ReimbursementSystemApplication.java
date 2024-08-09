package com.revature.ReimbursementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.Models") //This tells Spring Boot to look in the model package for DB entities
@ComponentScan("com.revature") //This tells Spring Boot to look for beans in the entire com.rev package
@EnableJpaRepositories("com.revature.DAOS") //This allows us to use JpaRepository in our DAOs

public class ReimbursementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReimbursementSystemApplication.class, args);
	}

}