package com.miguelmendezz.mise.security;

import com.miguelmendezz.mise.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final JsonMapper jsonMapper;

    public CustomAuthenticationEntryPoint(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ErrorMessage error = new ErrorMessage(401, authException.getMessage());
        response.setStatus(401);
        response.setContentType("application/json");
        String jsonString = jsonMapper.writeValueAsString(error);
        response.getWriter().write(jsonString);
    }
}
