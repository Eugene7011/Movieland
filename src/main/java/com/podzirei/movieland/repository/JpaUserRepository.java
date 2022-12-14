package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}