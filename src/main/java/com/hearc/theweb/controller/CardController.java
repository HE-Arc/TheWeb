package com.hearc.theweb.controller;

import java.util.Map;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hearc.theweb.models.entites.Card;
import com.hearc.theweb.models.repositories.CardsRepository;

@Controller
@RequestMapping(value = "/card")
public class CardController {

	@Autowired
	CardsRepository repo;

	@GetMapping(value = "/")
	public String getCards(Map<String, Object> model) {
		model.put("cards", repo.findAll());
		return "card/see-cards";
	}

	@GetMapping(value = "/{Id}")
	public String getCard(@PathVariable(value="Id") Long id, Map<String, Object> model) {
		Optional<Card> card = repo.findById(id);
		try
		{
			model.put("card", card.get());
			return "card/see-detail.html";
		}
		catch (Exception e)
		{
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
		repo.save(card);
		return "redirect:/card/";
	}

}