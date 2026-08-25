package com.serviceflow.api.controller;

import com.serviceflow.api.dto.ServiceRequestRequest;
import com.serviceflow.api.dto.ServiceRequestResponse;
import com.serviceflow.api.dto.ServiceRequestStatusRequest;
import com.serviceflow.api.service.ServiceRequestService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {

    private final ServiceRequestService service;

    public ServiceRequestController(ServiceRequestService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceRequestResponse create(
            @Valid @RequestBody ServiceRequestRequest request) {

        return service.create(request);
    }

    @GetMapping
    public List<ServiceRequestResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ServiceRequestResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ServiceRequestResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ServiceRequestRequest request) {

        return service.update(id, request);
    }

    @PatchMapping("/{id}/status")
    public ServiceRequestResponse updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody ServiceRequestStatusRequest request) {

        return service.updateStatus(id, request);
    }
}