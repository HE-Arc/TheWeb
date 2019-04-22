package com.hearc.theweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hearc.theweb.dto.UserDTO;
import com.hearc.theweb.exception.UserExistsException;
import com.hearc.theweb.models.entites.User;
import com.hearc.theweb.services.UserDetailsService_TW;

@Controller
@RequestMapping(value = "register")
public class RegisterController {

	@Autowired
	UserDetailsService_TW userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	/**
	 * Return the register template with an empty UserDTO
	 * 
	 * @param request Get request to "/register/"
	 * @param model   model
	 * @return registration form
	 */
	@GetMapping(value="/")
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("user", userDTO);
		return "security/register";
	}

	@PostMapping(value = "/")
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDTO accountDto, BindingResult result,
			WebRequest request, Errors errors) {
		User registered = new User();
		if (!result.hasErrors()) {
			registered = createUserAccount(accountDto, result);
		}
		if (registered == null) {
			result.rejectValue("username", "message.regError");
		}
		if (result.hasErrors()) {
			return new ModelAndView("security/register", "user", accountDto);
		} else {
			return new ModelAndView("security/register-success", "user", registered);
		}
	}

	private User createUserAccount(UserDTO accountDTO, BindingResult result) {
		User registered = null;
		try {
			registered = userDetailsService.registerNewUserAccount(accountDTO);
		} catch (UserExistsException e) {
			return null;
		}
		return registered;
	}
}
