package com.serviceflow.api.dto;

import com.serviceflow.api.entity.ServiceRequestStatus;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados necessários para atualizar o status de uma solicitação")
public class ServiceRequestStatusRequest {

    @Schema(
            description = "Novo status da solicitação",
            example = "IN_PROGRESS",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "status: must not be null")
    private ServiceRequestStatus status;

    public ServiceRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceRequestStatus status) {
        this.status = status;
    }
}