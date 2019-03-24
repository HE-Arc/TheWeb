package com.hearc.theweb.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hearc.theweb.models.entites.Card;
import com.hearc.theweb.models.entites.SocialMediaAccount;

public interface SocialMediaAccountRepository extends CrudRepository<SocialMediaAccount, Long> {
	List<SocialMediaAccount> findByCard(Card card);
}
