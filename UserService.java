package com.user.app.auth_spbootapp.service;



import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.app.auth_spbootapp.Repository.RoleRepository;
import com.user.app.auth_spbootapp.Repository.UserRepository;
import com.user.app.auth_spbootapp.model.Role;
import com.user.app.auth_spbootapp.model.User;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Create user
    public User saveUser(User dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(dto);
    }

    // ✅ Get all users
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // ✅ Get user by ID
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // ✅ Assign Role to User
    public void assignRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Role role = roleRepository.findByRole(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(role);
        userRepository.save(user);
    }

    // ✅ Get Roles of a User
    public Set<Role> getUserRoles(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getRoles();
    }
}
