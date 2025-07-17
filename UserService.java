package com.user.app.auth_spbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.app.auth_spbootapp.model.User;
import com.user.app.auth_spbootapp.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void saveUser(User dto) {
		
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		userRepository.save(dto);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUserById(long id) {

		return userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
