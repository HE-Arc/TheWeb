package com.hearc.theweb;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheWebApplication {

	@PostConstruct
	public void init() {

	}

	public static void main(String[] args) {
		SpringApplication.run(TheWebApplication.class, args);
	}

}
