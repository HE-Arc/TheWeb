package com.hearc.theweb.clts01.TS01;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hearc.theweb.dto.UserDTO;
import com.hearc.theweb.models.entites.User;
import com.hearc.theweb.models.repositories.UserRepository;
import com.hearc.theweb.properties.StorageProperties;
import com.hearc.theweb.security.SecurityConfig;
import com.hearc.theweb.services.UserDetailsService_TW;
import com.hearc.theweb.webconfiguration.WebConfiguration;

/**
 * CLTS01-TS01T1 User doesn't exist
 * 
 * @author kim.biloni
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfiguration.class, StorageProperties.class, SecurityConfig.class })
@SpringBootTest(classes = {UserDetailsService_TW.class, UserRepository.class})
@WithUserDetails
public class TS01T1 {

	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;

	@Autowired
	UserDetailsService  userDetailsServiceMock;
	// https://www.baeldung.com/java-spring-mockito-mock-mockbean

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	public void givenUsernameAndPassword_whenUserdoesntExist_thenUserIsCreated() throws Exception {
		// Given
		String username = "username";
		String password = "pass";
		String matchingPassword = "pass";

		User userAdded = new User();
		userAdded.setUsername(username);
		userAdded.setPassword(bCryptPasswordEncoder.encode(password));

		//when(userDetailsServiceMock.registerNewUserAccount(isA(UserDTO.class))).thenReturn(userAdded);

		// when
		mockMvc.perform(post("register")//
				.content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)//
				.param("username", username)//
				.param("password", password)//
				.param("matchinpassword", matchingPassword)//
				.sessionAttr("UserDTO", new UserDTO())).andExpect(status().isMovedTemporarily())//
				.andExpect(view().name("redirect:register-sucess"))//
				.andExpect(redirectedUrl("register"));

		ArgumentCaptor<UserDTO> formObjectArgument = ArgumentCaptor.forClass(UserDTO.class);
		//verify(userDetailsServiceMock, times(1)).registerNewUserAccount(formObjectArgument.capture());
		verifyNoMoreInteractions(userDetailsServiceMock);

		UserDTO formObject = formObjectArgument.getValue();

		assertThat(formObject.getUsername(), is(username));
		assertThat(formObject.getPassword(), is(password));
		assertThat(formObject.getMatchingPassword(), is(matchingPassword));
	}

}
