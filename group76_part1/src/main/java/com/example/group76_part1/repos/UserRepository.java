package com.example.group76_part1.repos;

import com.example.group76_part1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Optional to handle any null pointers gracefully
    Optional<User> findByUsername(String username);
}
