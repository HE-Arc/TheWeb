package com.hearc.theweb.controller;

import java.util.Map;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hearc.theweb.models.entites.Card;
import com.hearc.theweb.models.entites.SocialMedia;
import com.hearc.theweb.models.entites.SocialMediaAccount;
import com.hearc.theweb.models.repositories.CardsRepository;
import com.hearc.theweb.models.repositories.SocialMediaAccountRepository;

@Controller
@RequestMapping(value = "card/{Id}/sma")
public class SocialMediaAccountController {

	@Autowired
	SocialMediaAccountRepository smaRepository;

	@Autowired
	CardsRepository cardsRepository;

	@GetMapping(value = "/add")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER" })
	public String addSocialMediaAccountMap(@PathVariable(value = "Id") Long id, Map<String, Object> model) {
		Optional<Card> card = cardsRepository.findById(id);
		if (card.isPresent()) {
			SocialMediaAccount socialMediaAccount = new SocialMediaAccount();
			socialMediaAccount.setCard(card.get());
			model.put("card", card.get());
			model.put("social_media_account", socialMediaAccount);
			return "social_media_account/add";
		} else {
			return "redirect:/card/";
		}
	}

	@PostMapping(value = "/save")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER" })
	public String save(@PathVariable("Id") long id, SocialMediaAccount sma) {
		Optional<Card> card = cardsRepository.findById(id);
		if(card.isPresent()) {
			sma.setCard(card.get());
			smaRepository.save(sma);
		}
		return "redirect:/card/" + id; // redirect on card related
	}
}
