package com.serviceflow.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados necessários para criar ou atualizar uma solicitação de serviço")
public class ServiceRequestRequest {

    @Schema(
            description = "Título da solicitação de serviço",
            example = "Computador não liga"
    )
    @NotBlank
    private String title;

    @Schema(
            description = "Descrição detalhada do problema ou serviço solicitado",
            example = "O computador não apresenta nenhum sinal de energia."
    )
    @NotBlank
    private String description;

    public ServiceRequestRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}