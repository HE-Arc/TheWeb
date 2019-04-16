package com.hearc.theweb.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hearc.theweb.models.entites.Card;
import com.hearc.theweb.models.entites.SocialMediaAccount;
import com.hearc.theweb.models.repositories.CardsRepository;
import com.hearc.theweb.models.repositories.SocialMediaAccountRepository;
import com.hearc.theweb.services.StorageService;

@Controller
@RequestMapping(value = "/card")
public class CardController {

	@Autowired
	CardsRepository cardsRepository;

	@Autowired
	SocialMediaAccountRepository smaRepository;

	@Autowired
	StorageService storageService;

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
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER" })
	public String addPersonneMap(Map<String, Object> model) {
		model.put("card", new Card());
		return "card/form";
	}

	@PostMapping(value = "/save")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER" })
	public String save(@ModelAttribute("card") Card card,
			@RequestParam(value = "picturefile", required = false) MultipartFile file) {
		
		storageService.store(file);
		
		cardsRepository.save(card);
		return "redirect:/card/" + card.getId();
	}

	@GetMapping(value = "/update/{Id}")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER" })
	public String update(@PathVariable(value = "Id") Long id, Map<String, Object> model) {
		Optional<Card> card = cardsRepository.findById(id);
		if (card.isPresent()) {
			model.put("card", card.get());
			return "card/form";
		}
		return "redirect:/card/";
	}

	@GetMapping(value = "/delete/{Id}")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR" })
	public String delete(@PathVariable(value = "Id") Long id) {
		Optional<Card> card = cardsRepository.findById(id);
		if (card.isPresent()) {
			cardsRepository.delete(card.get());
		}
		return "redirect:/card/";
	}
}
