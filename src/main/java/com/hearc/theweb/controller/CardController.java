package com.hearc.theweb.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hearc.theweb.models.entites.Card;
import com.hearc.theweb.models.entites.SocialMediaAccount;
import com.hearc.theweb.models.repositories.CardsRepository;
import com.hearc.theweb.models.repositories.SocialMediaAccountRepository;

@Controller
@RequestMapping(value = "/card")
public class CardController {

	@Autowired
	CardsRepository cardsRepository;
	
	@Autowired
	SocialMediaAccountRepository smaRepository;

	@GetMapping(value = "/")
	public String getCards(Map<String, Object> model) {
		model.put("cards", cardsRepository.findAll());
		return "card/see-cards";
	}

	@GetMapping(value = "/{Id}")
	public String getCard(@PathVariable(value = "Id") Long id, Map<String, Object> model) {
		Optional<Card> card = cardsRepository.findById(id);
		if (card.isPresent()) {
			model.put("card", card.get());
			
			// Add the related social media accounts
			List<SocialMediaAccount> smaList = smaRepository.findByCard(card.get());
			model.put("social_media_accounts", smaList);
			return "card/see-detail.html";
		} else {
			return "redirect:/card/";
		}
	}

	@GetMapping(value = "/add")
	public String addPersonneMap(Map<String, Object> model) {
		model.put("card", new Card());
		return "card/add";
	}

	@PostMapping(value = "/save")
	public String save(Card card) {
		cardsRepository.save(card);
		return "redirect:/card/";
	}

}
