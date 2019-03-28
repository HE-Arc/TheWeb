package com.hearc.theweb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hearc.theweb.models.entites.Card;
import com.hearc.theweb.models.repositories.CardsRepository;

@Controller
public class homeController {

	@Autowired
	CardsRepository cardsRepository;

	@GetMapping(value = "/")
	public String getHome(@RequestParam(value = "search", required = false) String q, Map<String, Object> model) {
		if (q != null && !q.isEmpty()) {
			Iterable<Card> cards = cardsRepository.findCardsByWord(q);
			model.put("searched_value", q);
			model.put("cards", cards);
			return "search_results";
		} else {
			return "home";
		}
	}
}
