package com.serviceflow.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(

        @NotBlank(message = "Username é obrigatório")
        String username,

        @NotBlank(message = "Password é obrigatória")
        String password

) {
}
