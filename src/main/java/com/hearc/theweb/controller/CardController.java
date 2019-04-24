package com.hearc.theweb.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		Optional<Card> optionnalCard = cardsRepository.findById(id);
		if (optionnalCard.isPresent()) {
			Card card = optionnalCard.get();
			model.put("card", card);

			// Add the related social media accounts
			List<SocialMediaAccount> smaList = smaRepository.findByCard(card);
			model.put("social_media_accounts", smaList);

			// Get the picture path
			if (card.isHasPicture()) {
				try {
					// Get the filename of the picture
					String accessname = storageService.loadCardPicture(card.getId()).getFileName().toString();
					model.put("picturepath", "/card/img/" + accessname);
				} catch (Exception e) {
					model.put("picturepath", "/static/images/cards/card-default.png");
				}
			} else {
				model.put("picturepath", "/static/images/cards/card-default.png");
			}
			return "card/see-detail.html";
		} else {
			return "redirect:/card/";
		}
	}

	@GetMapping(value = "/add")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER" })
	public String addCard(Map<String, Object> model) {
		model.put("card", new Card());
		return "card/form";
	}

	@PostMapping(value = "/save")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER" })
	public String saveCard(@ModelAttribute("card") Card card,
			@RequestParam(value = "picturefile", required = false) MultipartFile file) {
		if (!file.isEmpty()) {
			storageService.storeCardPicture(file, card.getId());
			card.setHasPicture(true);
		}
		cardsRepository.save(card);
		return "redirect:/card/" + card.getId();
	}

	@GetMapping(value = "/update/{Id}")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER" })
	public String updateCard(@PathVariable(value = "Id") Long id, Map<String, Object> model) {
		Optional<Card> card = cardsRepository.findById(id);
		if (card.isPresent()) {
			model.put("card", card.get());
			return "card/form";
		}
		return "redirect:/card/";
	}

	@GetMapping(value = "/delete/{Id}")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_MODERATOR" })
	public String deleteCard(@PathVariable(value = "Id") Long id) {
		Optional<Card> card = cardsRepository.findById(id);
		if (card.isPresent()) {
			Card cardToDelete = card.get();
			
			// Delete all the related SMAs first
			List<SocialMediaAccount> smas = smaRepository.findByCard(cardToDelete);
			smas.forEach(smaRepository::delete);
			
			// Delete the profil picture
			storageService.deleteCardPicture(cardToDelete.getId());
			
			// Finnaly delete the card
			cardsRepository.delete(cardToDelete);
		}
		return "redirect:/card/";
	}

	@GetMapping(value = "/img/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
