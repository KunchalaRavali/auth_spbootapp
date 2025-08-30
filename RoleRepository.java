package com.user.app.auth_spbootapp.Repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user.app.auth_spbootapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);  // ✅ correct field name
}


