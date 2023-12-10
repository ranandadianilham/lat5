package com.idstar.lat5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idstar.lat5.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
