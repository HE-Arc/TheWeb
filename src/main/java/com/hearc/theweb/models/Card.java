package com.hearc.theweb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Card {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	private Person person;

	public Card() {

	}

	public Card(long id, Person person) {
		super();
		this.id = id;
		this.person = person;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card [id=");
		builder.append(id);
		builder.append(", person=");
		builder.append(person);
		builder.append("]");
		return builder.toString();
	}

}
