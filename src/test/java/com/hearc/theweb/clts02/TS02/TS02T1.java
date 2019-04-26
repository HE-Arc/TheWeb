package com.hearc.theweb.clts02.TS02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hearc.theweb.models.entites.Card;
import com.hearc.theweb.models.repositories.CardsRepository;
import com.hearc.theweb.services.StorageService;

/**
 * CLTS02-TS02T1 The card doesn't exist
 * 
 * @author kim.biloni
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TS02T1 {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	CardsRepository cardsRepository;
	
	@Resource(name = "FileSystemStorageService")
	public StorageService storageService;

	@Test
	public void givenCardAttributs_whenCardDoesntExist_thenAdd() {

		Card card = new Card();
		card.setName("CardName");
		card.setFirstname("CardFirstname");
		card.setBirthdate("10.02.1995");
		card.setLocalisation("Neuchâtel");

		entityManager.persist(card);
		entityManager.flush();

		Optional<Card> cardSearched = cardsRepository.findById(card.getId());

		assertTrue(cardSearched.isPresent());
		assertTrue(cardSearched.get().getId() == card.getId());

		assertThat(cardSearched.get()).isNotNull();
	}

}
