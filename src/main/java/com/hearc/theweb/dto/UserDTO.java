package com.hearc.theweb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.hearc.theweb.annotations.PasswordMatches;

/**
 * User Data Transfert Object
 * Use for register validation
 * @author kim.biloni
 */
@PasswordMatches
public class UserDTO {

	@NotNull
	@NotEmpty
	private String username;

	@NotNull
	@NotEmpty
	private String password;
	private String matchingPassword;

	// Getters & Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
}
