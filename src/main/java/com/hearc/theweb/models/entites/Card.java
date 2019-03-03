package com.hearc.theweb.models.entites;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String firstname;
	private String birthdate;
	private String localisation;

	public Card() {

	}

	public Card(String name, String firstname, String birthdate, String localisation) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.birthdate = birthdate;
		this.localisation = localisation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", birthdate=");
		builder.append(birthdate);
		builder.append(", localisation=");
		builder.append(localisation);
		builder.append("]");
		return builder.toString();
	}
	
}
