package com.serviceflow.api.dto;

import com.serviceflow.api.entity.ServiceRequestStatus;

import jakarta.validation.constraints.NotNull;

public class ServiceRequestStatusRequest {

    @NotNull(message = "status: must not be null")
    private ServiceRequestStatus status;

    public ServiceRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceRequestStatus status) {
        this.status = status;
    }
}
