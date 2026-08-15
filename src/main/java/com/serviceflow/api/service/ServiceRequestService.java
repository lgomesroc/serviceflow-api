package com.serviceflow.api.service;

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

    public ServiceRequest create(ServiceRequest serviceRequest) {

        return repository.save(serviceRequest);
    }

    public List<ServiceRequest> findAll() {

        return repository.findAll();
    }

    public ServiceRequest findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ServiceRequestNotFoundException(id));
    }

    public ServiceRequest update(Long id, ServiceRequest serviceRequest) {

        ServiceRequest existingRequest = repository.findById(id)
                .orElseThrow(() -> new ServiceRequestNotFoundException(id));

        existingRequest.setTitle(serviceRequest.getTitle());
        existingRequest.setDescription(serviceRequest.getDescription());

        return repository.save(existingRequest);
    }

}
