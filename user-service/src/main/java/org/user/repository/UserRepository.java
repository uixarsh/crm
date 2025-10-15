package org.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}