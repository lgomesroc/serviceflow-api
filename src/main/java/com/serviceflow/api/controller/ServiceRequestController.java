package com.serviceflow.api.controller;

import com.serviceflow.api.entity.ServiceRequest;
import com.serviceflow.api.service.ServiceRequestService;
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
    public ServiceRequest create(@RequestBody ServiceRequest serviceRequest) {

        return service.create(serviceRequest);
    }

    @GetMapping
    public List<ServiceRequest> findAll() {

        return service.findAll();
    }

    @GetMapping("/{id}")
    public ServiceRequest findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ServiceRequest update(
            @PathVariable Long id,
            @RequestBody ServiceRequest serviceRequest) {

        return service.update(id, serviceRequest);
    }

}

