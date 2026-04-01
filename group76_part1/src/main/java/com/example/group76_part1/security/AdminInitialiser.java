package com.example.group76_part1.security;

import com.example.group76_part1.entities.User;
import com.example.group76_part1.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
// CommandLineRunner ensures this AdminInitialiser only runs once, when the application is loading in
public class AdminInitialiser implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitialiser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // Create a default HR account that can be used
        if (userRepository.findByUsername("hr_user").isEmpty()) {
            User hrUser = new User();
            hrUser.setUsername("hr_user");
            hrUser.setPassword(passwordEncoder.encode("password123"));
            hrUser.setRole("ROLE_HR");
            userRepository.save(hrUser);
            System.out.println("HR user created");
        }

        // Create a default Manager account that can be used
        if (userRepository.findByUsername("manager_user").isEmpty()) {
            User manager = new User();
            manager.setUsername("manager_user");
            manager.setPassword(passwordEncoder.encode("password123"));
            manager.setRole("ROLE_MANAGER");
            userRepository.save(manager);
            System.out.println("Manager user created");
        }
    }
}
