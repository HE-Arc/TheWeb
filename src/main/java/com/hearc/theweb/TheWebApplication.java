package com.hearc.theweb;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import com.hearc.theweb.models.repositories.CardsRepository;
import com.hearc.theweb.models.repositories.SocialMediaAccountRepository;

@SpringBootApplication
public class TheWebApplication {

	@Autowired
	CardsRepository cardsRepo;
	@Autowired
	SocialMediaAccountRepository smaRepo;

	@PostConstruct
	public void init() {
		System.out.println("init the application");

	}

	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[] { new ClassPathResource("card-data.json") });
		return factory;
	}

	public static void main(String[] args) {
		SpringApplication.run(TheWebApplication.class, args);
	}

}
