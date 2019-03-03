package com.hearc.theweb.models.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SocialMediaAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany
	private SocialMedia socialMedia;

	@OneToMany
	private Card card;

	@Column
	private String link;

	public SocialMediaAccount() {

	}

	public SocialMediaAccount(SocialMedia socialMedia, Card card, String link) {
		super();
		this.socialMedia = socialMedia;
		this.card = card;
		this.link = link;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SocialMedia getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SocialMediaAccount [id=");
		builder.append(id);
		builder.append(", socialMedia=");
		builder.append(socialMedia);
		builder.append(", card=");
		builder.append(card);
		builder.append(", link=");
		builder.append(link);
		builder.append("]");
		return builder.toString();
	}

}
