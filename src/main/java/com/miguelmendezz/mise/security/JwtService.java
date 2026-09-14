package com.miguelmendezz.mise.security;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final String key = "at-least-32-characters-long-secret-key";
}
