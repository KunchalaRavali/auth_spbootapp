package com.user.app.auth_spbootapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.app.auth_spbootapp.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByUsername(String username);

}

