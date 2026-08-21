package com.serviceflow.api.dto;

import com.serviceflow.api.entity.ServiceRequestStatus;

import java.time.LocalDateTime;

public class ServiceRequestResponse {

    private Long id;
    private String title;
    private String description;
    private ServiceRequestStatus status;
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
