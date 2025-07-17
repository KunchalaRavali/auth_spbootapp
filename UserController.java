package com.user.app.auth_spbootapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.user.app.auth_spbootapp.model.User;
import com.user.app.auth_spbootapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	@ResponseStatus(value = HttpStatus.CREATED) //201
	public void saveUser(@RequestBody User dto) {
		userService.saveUser(dto);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(value = HttpStatus.OK) //200
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	@ResponseStatus(value = HttpStatus.OK) //200
	public User getUserById(@PathVariable long id) {
		return userService.getUserById(id);
	}
	
}
