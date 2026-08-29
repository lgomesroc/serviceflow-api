package com.serviceflow.api.dto;

import com.serviceflow.api.entity.ServiceRequestStatus;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dados retornados pela API para uma solicitação de serviço")
public class ServiceRequestResponse {

    @Schema(
            description = "Identificador único da solicitação",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Título da solicitação de serviço",
            example = "Computador não liga"
    )
    private String title;

    @Schema(
            description = "Descrição detalhada do problema ou serviço solicitado",
            example = "O computador não apresenta nenhum sinal de energia."
    )
    private String description;

    @Schema(
            description = "Status atual da solicitação",
            example = "PENDING"
    )
    private ServiceRequestStatus status;

    @Schema(
            description = "Data e hora em que a solicitação foi criada",
            example = "2026-08-29T10:30:00"
    )
    private LocalDateTime createdAt;

    public ServiceRequestResponse(
            Long id,
            String title,
            String description,
            ServiceRequestStatus status,
            LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ServiceRequestStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}