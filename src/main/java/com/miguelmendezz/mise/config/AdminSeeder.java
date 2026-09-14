package com.miguelmendezz.mise.config;

import com.miguelmendezz.mise.entity.enums.Role;
import com.miguelmendezz.mise.repository.UserRepository;
import com.miguelmendezz.mise.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;

    public AdminSeeder(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void run(String[] args) {
        if (!userRepository.existsByUsername("admin")) {
            userService.registerUser("admin", "admin123", Role.ADMIN);
        }
    }
}
