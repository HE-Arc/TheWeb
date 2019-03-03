package com.hearc.theweb;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hearc.theweb.models.repositories.CardsRepository;
import com.hearc.theweb.models.repositories.PersonsRepository;
import com.hearc.theweb.models.repositories.SocialMediaAccountRepository;

@SpringBootApplication
public class TheWebApplication {

	@Autowired
	CardsRepository cardsRepo;
	@Autowired
	SocialMediaAccountRepository smaRepo;

	@PostConstruct
	public void init() {

	}

	public static void main(String[] args) {
		SpringApplication.run(TheWebApplication.class, args);
	}

}
