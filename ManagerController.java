package com.user.app.auth_spbootapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.user.app.auth_spbootapp.model.Domain;
import com.user.app.auth_spbootapp.model.Role;
import com.user.app.auth_spbootapp.model.User;
import com.user.app.auth_spbootapp.Repository.DomainRepository;
import com.user.app.auth_spbootapp.Repository.UserRepository;
import com.user.app.auth_spbootapp.service.DomainService;
import com.user.app.auth_spbootapp.service.UserService;
import com.user.app.auth_spbootapp.Repository.UserRepository;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	private final DomainRepository domainRepository;
	private final UserRepository userRepository;
	private final DomainService domainService;
	private final UserService userService;
	
	public ManagerController(DomainRepository domainRepository, UserRepository userRepository, DomainService domainService, UserService userService) {
		this.domainRepository = domainRepository;
		this.userRepository = userRepository;
		this.domainService = domainService;
		this.userService = userService;
	}

	@PostMapping("/assign")
	@ResponseStatus(value = HttpStatus.CREATED)
	@PreAuthorize("hasRole('MANAGER')")
	public void assignMember(@RequestParam("member") String username,
							@RequestParam("domain") String domainName) {
		
		User member = userRepository.findByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		
		Domain domain = domainRepository.findByName(domainName)
						.orElseThrow(() -> new RuntimeException("Domain not found"));
		Set<User> team = domain.getTeamMembers();
		team.add(member);
		System.out.println("Set: data====> "+team);
		domain.setTeamMembers(team);
		
		domainService.save(domain);
	}
	
	@GetMapping("/team")
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("hasRole('MANAGER')")
	public Set<User> getTeam(@RequestParam("domain") String domainName) {
		Domain domain = domainRepository.findByName(domainName)
				.orElseThrow(() -> new RuntimeException("Domain not found"));
		Set<User> team = domain.getTeamMembers();
		
		return team;
	}
	
	@GetMapping("/teamleads")
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("hasRole('MANAGER')")
	public List<User> getTeamLeads() {
		
		List<User> usersList = userService.getUsers();
		
		List<User> leads = new ArrayList<User>();
		for(User user: usersList) {
			
			Set<Role> roles = user.getRoles();
			System.out.println(roles);
			System.out.println(user.getUsername());
			System.out.println("=========");
			for(Role r: roles) {
				if(r.getRole().equals("ROLE_TEAMLEAD")) {
					leads.add(user);
				}
			}
		}
		return leads;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}