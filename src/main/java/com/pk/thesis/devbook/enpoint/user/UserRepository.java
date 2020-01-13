package com.pk.thesis.devbook.enpoint.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDAO, Long> {
    UserDAO findByUsername(String username);
}
