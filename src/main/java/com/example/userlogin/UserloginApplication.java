package com.example.userlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.controller.AppController;

@SpringBootApplication
@EntityScan("com.example.models") 
@EnableJpaRepositories(basePackages = "com.example.repositories")
@ComponentScan("com.example")
public class UserloginApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserloginApplication.class, args);
	}

}
