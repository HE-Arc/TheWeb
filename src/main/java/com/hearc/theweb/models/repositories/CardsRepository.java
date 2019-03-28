package com.hearc.theweb.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hearc.theweb.models.entites.Card;

public interface CardsRepository extends CrudRepository<Card, Long> {
	// https://www.baeldung.com/spring-data-jpa-query
	// Faire les query avec ça

	@Query(value = "SELECT c from Card c WHERE c.name = ?1 OR c.firstname = ?1")
	List<Card> findCardsByWord(String word);
}
