package com.serviceflow.api.security;

import com.serviceflow.api.entity.User;
import com.serviceflow.api.repository.UserRepository;
import com.serviceflow.api.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @BeforeEach
    void setUp() {

        if (userRepository.findByUsername("security-test").isEmpty()) {

            User user = new User(
                    "security-test",
                    passwordEncoder.encode("123456")
            );

            userRepository.save(user);
        }
    }

    @Test
    void shouldDenyAccessWhenJwtIsNotProvided() throws Exception {

        mockMvc.perform(
                        get("/api/service-requests")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldAllowAccessWhenValidJwtIsProvided() throws Exception {

        String token = jwtService.generateToken("security-test");

        mockMvc.perform(
                        get("/api/service-requests")
                                .accept(MediaType.APPLICATION_JSON)
                                .header(
                                        "Authorization",
                                        "Bearer " + token
                                )
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldDenyAccessWhenInvalidJwtIsProvided() throws Exception {

        mockMvc.perform(
                        get("/api/service-requests")
                                .accept(MediaType.APPLICATION_JSON)
                                .header(
                                        "Authorization",
                                        "Bearer token-invalido"
                                )
                )
                .andExpect(status().isForbidden());
    }
}
