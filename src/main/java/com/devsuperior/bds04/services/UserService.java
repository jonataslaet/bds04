package com.devsuperior.bds04.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userByEmail = userRepository.findByEmail(email);
		if (userByEmail == null) {
			throw new EntityNotFoundException("User not found for email: " + email);
		}
		return userByEmail;
	}

}
