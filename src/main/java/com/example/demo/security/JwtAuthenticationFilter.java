package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.example.demo.config.PropertiesConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import com.example.demo.controller.v1.request.user.AddUserRequest;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
private final AuthenticationManager authenticationManager;
private static PropertiesConfig propertiesConfig;

public JwtAuthenticationFilter(final AuthenticationManager authenticationManager){
    this.authenticationManager = authenticationManager;
}

    /* Trigger when we issue POST request to /login
    We also need to pass in {"username":"dan", "password":"dan123"} in the request body
     */

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {

        // Grab credentials and map them to login viewmodel
        AddUserRequest credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), AddUserRequest.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        // Create login token
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getEmail(),
                credentials.getPassword(),
                new ArrayList<>());

        // Authenticate user
        final Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        // Grab principal
        final UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

        // Create JWT Token
        final String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 24000))
                .sign(HMAC512(propertiesConfig.getConfigValue("SECRET").getBytes()));

        // Add token in response
        response.addHeader(propertiesConfig.getConfigValue("HEADER_STRING"), propertiesConfig.getConfigValue("TOKEN_PREFIX") +  token);
    }
}