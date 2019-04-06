package com.hearc.theweb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hearc.theweb.models.entites.Card;
import com.hearc.theweb.models.repositories.CardsRepository;

@Controller
public class homeController {

	@Autowired
	CardsRepository cardsRepository;
	
	private static final int CARDS_BY_PAGE = 3;

	@GetMapping(value = "/")
	public String getHome(@RequestParam(value = "search", required = false) String word, @RequestParam(value = "page", required = false, defaultValue = "1") int numPage, Map<String, Object> model) {
		if (word != null && !word.isEmpty()) {
			//Iterable<Card> cards = cardsRepository.findCardsByWord(word);
			
			Pageable pageRequest = PageRequest.of(numPage - 1, CARDS_BY_PAGE); // beacause it starts at 0
			Page<Card> cardsPage = cardsRepository.findCardsByWord(word, pageRequest);
			model.put("searched_value", word);
			model.put("cards", cardsPage.getContent());
			model.put("current_page", numPage);
			model.put("has_next", cardsPage.hasNext());
			model.put("has_previous", cardsPage.hasPrevious());
			return "search_results";
		} else {
			return "home";
		}
	}
}
