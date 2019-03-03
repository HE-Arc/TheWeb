package com.hearc.theweb.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hearc.theweb.models.entites.Card;

public interface CardsRepository extends CrudRepository<Card, Long> {

}
