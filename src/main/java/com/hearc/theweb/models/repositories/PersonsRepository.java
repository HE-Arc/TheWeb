package com.hearc.theweb.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hearc.theweb.models.entites.Person;

public interface PersonsRepository extends CrudRepository<Person, Long> {

}
