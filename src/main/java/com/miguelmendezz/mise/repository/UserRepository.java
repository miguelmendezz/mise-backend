package com.miguelmendezz.mise.repository;

import com.miguelmendezz.mise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    boolean existsByUsername(String username);
}
