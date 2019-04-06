package com.hearc.theweb.models.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hearc.theweb.models.entites.Card;

public interface CardsRepository extends PagingAndSortingRepository<Card, Long> {
	// https://www.baeldung.com/spring-data-jpa-query
	// Faire les query avec ça

	@Query(value = "SELECT c from Card c WHERE c.name LIKE %?1% OR c.firstname LIKE %?1%")
	List<Card> findCardsByWord(String word);
	
	@Query(value = "SELECT c from Card c WHERE c.name LIKE %?1% OR c.firstname LIKE %?1%")
	Page<Card> findCardsByWord(String word, Pageable pageable);
}
