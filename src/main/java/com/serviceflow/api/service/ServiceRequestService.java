package com.serviceflow.api.service;

import com.serviceflow.api.dto.ServiceRequestRequest;
import com.serviceflow.api.dto.ServiceRequestResponse;
import com.serviceflow.api.entity.ServiceRequest;
import com.serviceflow.api.exception.ServiceRequestNotFoundException;
import com.serviceflow.api.repository.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository repository;

    public ServiceRequestService(ServiceRequestRepository repository) {

        this.repository = repository;
    }

    public ServiceRequestResponse create(ServiceRequestRequest request) {

        ServiceRequest serviceRequest = new ServiceRequest();

        serviceRequest.setTitle(request.getTitle());
        serviceRequest.setDescription(request.getDescription());

        ServiceRequest savedRequest = repository.save(serviceRequest);

        return toResponse(savedRequest);
    }

    public List<ServiceRequestResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ServiceRequestResponse findById(Long id) {

        ServiceRequest serviceRequest = repository.findById(id)
                .orElseThrow(() -> new ServiceRequestNotFoundException(id));

        return toResponse(serviceRequest);
    }

    public ServiceRequestResponse update(
            Long id,
            ServiceRequestRequest request) {

        ServiceRequest existingRequest = repository.findById(id)
                .orElseThrow(() -> new ServiceRequestNotFoundException(id));

        existingRequest.setTitle(request.getTitle());
        existingRequest.setDescription(request.getDescription());

        ServiceRequest updatedRequest = repository.save(existingRequest);

        return toResponse(updatedRequest);
    }

    private ServiceRequestResponse toResponse(ServiceRequest serviceRequest) {

        return new ServiceRequestResponse(
                serviceRequest.getId(),
                serviceRequest.getTitle(),
                serviceRequest.getDescription(),
                serviceRequest.getStatus(),
                serviceRequest.getCreatedAt()
        );
    }
}
