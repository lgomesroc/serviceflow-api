package com.serviceflow.api.controller;

import com.serviceflow.api.dto.LoginRequest;
import com.serviceflow.api.dto.LoginResponse;
import com.serviceflow.api.dto.RegisterRequest;
import com.serviceflow.api.service.AuthService;
import com.serviceflow.api.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            AuthService authService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(
                request.username(),
                request.password()
        );

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.username(),
                                request.password()
                        )
                );

        String token = jwtService.generateToken(
                authentication.getName()
        );

        return ResponseEntity.ok(
                new LoginResponse(token)
        );
    }
}
