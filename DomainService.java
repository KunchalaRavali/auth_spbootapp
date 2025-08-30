package com.user.app.auth_spbootapp.service;

import com.user.app.auth_spbootapp.Repository.DomainRepository;
import com.user.app.auth_spbootapp.model.Domain;

public class DomainService {
 
	public Domain save(Domain domain) {
		
		return DomainRepository.save();
	}

	

}
