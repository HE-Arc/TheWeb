package com.hearc.theweb.unittests;

import org.junit.Test;
import org.springframework.util.Assert;

import com.hearc.theweb.models.entites.Card;

public class CardCreation {

	@Test
	public void test() {
		Card card = new Card();
		card.setName("Name");
		card.setFirstname("Firstname");
		Assert.isTrue(card != null, "The card instance is null");
	}

}
