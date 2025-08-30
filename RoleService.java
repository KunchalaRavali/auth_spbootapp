package com.user.app.auth_spbootapp.service;



import com.user.app.auth_spbootapp.Repository.RoleRepository;
import com.user.app.auth_spbootapp.model.Role;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}



