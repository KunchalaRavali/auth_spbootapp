package com.user.app.auth_spbootapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.user.app.auth_spbootapp.Repository.RoleRepository;
import com.user.app.auth_spbootapp.Repository.UserRepository;
import com.user.app.auth_spbootapp.model.Role;
import com.user.app.auth_spbootapp.model.User;

import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // ✅ Constructor with both dependencies
    public RoleController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/assignRole")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('MANAGER')")
    public User assignMember(@RequestParam("user") String username,
                             @RequestParam("roleId") long roleId) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Role existingRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Set<Role> roles = user.getRoles();  // ✅ fixed Set<Role>
        roles.add(existingRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
