package com.user.app.auth_spbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.app.auth_spbootapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
