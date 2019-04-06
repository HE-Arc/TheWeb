package com.hearc.theweb.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hearc.theweb.dto.UserDTO;
import com.hearc.theweb.models.entites.Role;
import com.hearc.theweb.models.entites.User;
import com.hearc.theweb.models.repositories.RoleRepositoy;
import com.hearc.theweb.models.repositories.UserRepository;

/**
 * UserDetailService_TW class
 * 
 * The TW is a suffix for The Web that avoid the conflict between the project
 * class and the SpringSecutity class.
 * 
 * @author kim.biloni
 *
 */
@Service
public class UserDetailsService_TW implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepositoy roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}

	@Transactional
	public User registerNewUserAccount(UserDTO accountDTO) {
		User user = new User();
		user.setUsername(accountDTO.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));

		// Add roles
		Set<Role> roles = new HashSet<>();
		Role role_user = roleRepository.findByName("ROLE_USER");
		roles.add(role_user);
		user.setRoles(roles);
		
		return userRepository.save(user);
	}
}
