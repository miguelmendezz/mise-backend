package com.miguelmendezz.mise.dto;

import com.miguelmendezz.mise.entity.enums.Role;

public record RegisterRequest(
        String username,
        String password,
        Role role
) {}
