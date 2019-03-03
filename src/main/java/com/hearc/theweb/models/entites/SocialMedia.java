package com.hearc.theweb.models.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public enum SocialMedia {
	FACEBOOK("Facebook", "https://www.facebook.com/"),
	TWITTER("Twitter", "https://twitter.com/"),
	INSTAGRAM("Instagram", "https://www.instagram.com/"),
	TUMBLR("Tumblr", "https://www.tumblr.com/"),
	SNAPCHAT("Snapchat", "https://www.snapchat.com/"),
	LINKEDIN("LinkedIn", "http://www.linkedin.com/");

	// Attributes
	@Id // obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nom;

	@Column
	private String adresse;

	// Constructors
	SocialMedia() {

	}

	SocialMedia(String nom, String adresse) {
		this.nom = nom;
		this.adresse = adresse;
	}

	// Getters/Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SocialMedia [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", adresse=");
		builder.append(adresse);
		builder.append("]");
		return builder.toString();
	}

}
