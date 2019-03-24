package com.hearc.theweb;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hearc.theweb.models.entites.Role;
import com.hearc.theweb.models.entites.User;
import com.hearc.theweb.models.repositories.CardsRepository;
import com.hearc.theweb.models.repositories.RoleRepositoy;
import com.hearc.theweb.models.repositories.SocialMediaAccountRepository;
import com.hearc.theweb.models.repositories.UserRepository;

@SpringBootApplication
public class TheWebApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepositoy roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	CardsRepository cardsRepo;
	@Autowired
	SocialMediaAccountRepository smaRepo;

	public static void main(String[] args) {
		SpringApplication.run(TheWebApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("init the application");

		// Creation des roles
		Role role_admin = new Role();
		role_admin.setName("ROLE_ADMIN");
		roleRepository.save(role_admin);
		
		Role role_moderator = new Role();
		role_moderator.setName("ROLE_MODERATOR");
		roleRepository.save(role_moderator);

		Role role_user = new Role();
		role_user.setName("ROLE_USER");
		roleRepository.save(role_user);

		// Creation d'un admin
		User u_admin = new User();
		u_admin.setUsername("admin");
		u_admin.setPassword(bCryptPasswordEncoder.encode("password"));
		
		Set<Role> roles_admin = new HashSet<>();
		roles_admin.add(role_admin);
		u_admin.setRoles(roles_admin);
		
		userRepository.save(u_admin);
		
		// Creation d'un moderator
		User u_moderator = new User();
		u_moderator.setUsername("moderator");
		u_moderator.setPassword(bCryptPasswordEncoder.encode("password"));
		
		Set<Role> roles_moderator = new HashSet<>();
		roles_moderator.add(role_moderator);
		u_moderator.setRoles(roles_moderator);

		userRepository.save(u_moderator);
		
		// Création d'un user
		User u_user = new User();
		u_user.setUsername("user");
		u_user.setPassword(bCryptPasswordEncoder.encode("password"));

		// Attribution du rôle
		Set<Role> roles_user = new HashSet<>();
		roles_user.add(role_user);
		u_user.setRoles(roles_user);

		userRepository.save(u_user);
	}

	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[] { new ClassPathResource("card-data.json") });
		return factory;
	}
}
