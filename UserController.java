package com.user.app.auth_spbootapp.controller;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.user.app.auth_spbootapp.model.Role;
import com.user.app.auth_spbootapp.model.User;
import com.user.app.auth_spbootapp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // ✅ Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    // ✅ Assign Role to User
    @PostMapping("/{username}/roles/{roleName}")
    public String assignRole(@PathVariable String username, @PathVariable String roleName) {
        userService.assignRoleToUser(username, roleName);
        return "Role assigned successfully";
    }

    // ✅ Get roles of a user
    @GetMapping("/{username}/roles")
    public Set<Role> getUserRoles(@PathVariable String username) {
        return userService.getUserRoles(username);
    }
}
