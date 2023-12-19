package com.spiritcoderz.prophiusassessmentprepup.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;

import java.io.IOException;

public interface JwtAuthenticationManager {
    void authenticate(@NonNull HttpServletRequest request,
                      @NonNull HttpServletResponse response,
                      @NonNull FilterChain filterChain) throws ServletException, IOException;
}
