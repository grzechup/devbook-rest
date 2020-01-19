package com.pk.thesis.devbook.repository;


import com.pk.thesis.devbook.models.ERole;
import com.pk.thesis.devbook.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
