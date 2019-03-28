package com.hearc.theweb.models.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.hearc.theweb.models.entites.Card;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CardsRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CardsRepository cardsRepository;

	@Test
	public void givenCard_whenPersistCard_thenCardIsPersisted() {
		
		// Given, etat
		Card card = new Card();
		card.setName("Card name");
		card.setFirstname("Card firstname");
		card.setBirthdate("01.01.1990");
		card.setLocalisation("Card localisation");

		entityManager.persist(card);
		entityManager.flush();

		// When, action
		Optional<Card> cardSearched = cardsRepository.findById(card.getId());
		
		// Then, result
		assertTrue(cardSearched.isPresent());
		assertTrue(cardSearched.get().getId() == card.getId());
		
		assertThat(cardSearched.get()).isNotNull();	// Avec assertJ
		
	}

}
