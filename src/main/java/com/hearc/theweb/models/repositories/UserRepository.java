package com.hearc.theweb.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hearc.theweb.models.entites.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
}
