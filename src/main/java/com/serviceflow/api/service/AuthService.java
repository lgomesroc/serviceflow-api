package com.serviceflow.api.service;

import com.serviceflow.api.entity.User;
import com.serviceflow.api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username, String password) {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException(
                    "Username já está em uso"
            );
        }

        String encodedPassword =
                passwordEncoder.encode(password);

        User user = new User(
                username,
                encodedPassword
        );

        userRepository.save(user);
    }
}
