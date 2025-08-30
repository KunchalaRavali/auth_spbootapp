package com.user.app.auth_spbootapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.app.auth_spbootapp.model.Domain;
import com.user.app.auth_spbootapp.model.User;

import java.util.Optional;


public interface DomainRepository extends JpaRepository<Domain, Long> {

	Optional<Domain> findByName(String name);

	static Domain save() {
		
		return null;
	}

	

}

