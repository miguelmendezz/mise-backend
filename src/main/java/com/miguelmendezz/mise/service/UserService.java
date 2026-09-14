package com.miguelmendezz.mise.service;

import com.miguelmendezz.mise.entity.User;
import com.miguelmendezz.mise.entity.enums.Role;
import com.miguelmendezz.mise.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String rawPassword, Role role) {

        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("This username is already taken.");
        }
        String hashedPassword = passwordEncoder.encode(rawPassword);

        User user = new User(username, hashedPassword, role);

        return userRepository.save(user);
    }
}
