package com.serviceflow.api.service;

import com.serviceflow.api.dto.ServiceRequestRequest;
import com.serviceflow.api.dto.ServiceRequestResponse;
import com.serviceflow.api.dto.ServiceRequestStatusRequest;
import com.serviceflow.api.entity.ServiceRequest;
import com.serviceflow.api.entity.ServiceRequestStatus;
import com.serviceflow.api.exception.InvalidServiceRequestStateException;
import com.serviceflow.api.exception.ServiceRequestNotFoundException;
import com.serviceflow.api.repository.ServiceRequestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository repository;

    public ServiceRequestService(ServiceRequestRepository repository) {
        this.repository = repository;
    }

    public ServiceRequestResponse create(ServiceRequestRequest request) {
        ServiceRequest serviceRequest = toEntity(request);

        ServiceRequest savedRequest = repository.save(serviceRequest);

        return toResponse(savedRequest);
    }

    public Page<ServiceRequestResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toResponse);
    }

    public ServiceRequestResponse findById(Long id) {
        ServiceRequest serviceRequest = findEntityById(id);

        return toResponse(serviceRequest);
    }

    public ServiceRequestResponse update(
            Long id,
            ServiceRequestRequest request) {

        ServiceRequest existingRequest = findEntityById(id);

        validateEditableStatus(existingRequest);

        existingRequest.setTitle(request.getTitle());
        existingRequest.setDescription(request.getDescription());

        ServiceRequest updatedRequest = repository.save(existingRequest);

        return toResponse(updatedRequest);
    }

    public void delete(Long id) {
        ServiceRequest existingRequest = findEntityById(id);

        repository.delete(existingRequest);
    }

    public ServiceRequestResponse updateStatus(
            Long id,
            ServiceRequestStatusRequest request) {

        ServiceRequest existingRequest = findEntityById(id);

        validateStatusTransition(
                existingRequest.getStatus(),
                request.getStatus()
        );

        existingRequest.setStatus(request.getStatus());

        ServiceRequest updatedRequest = repository.save(existingRequest);

        return toResponse(updatedRequest);
    }

    private void validateStatusTransition(
            ServiceRequestStatus currentStatus,
            ServiceRequestStatus newStatus) {

        if (currentStatus == ServiceRequestStatus.COMPLETED
                || currentStatus == ServiceRequestStatus.CANCELLED) {

            throw new InvalidServiceRequestStateException(
                    "Não é possível alterar o status de uma solicitação finalizada."
            );
        }

        boolean validTransition =
                (currentStatus == ServiceRequestStatus.PENDING
                        && (newStatus == ServiceRequestStatus.IN_PROGRESS
                        || newStatus == ServiceRequestStatus.CANCELLED))
                        ||
                        (currentStatus == ServiceRequestStatus.IN_PROGRESS
                                && (newStatus == ServiceRequestStatus.COMPLETED
                                || newStatus == ServiceRequestStatus.CANCELLED));

        if (!validTransition) {
            throw new InvalidServiceRequestStateException(
                    "Transição de status inválida: "
                            + currentStatus
                            + " para "
                            + newStatus
                            + "."
            );
        }
    }

    private void validateEditableStatus(ServiceRequest serviceRequest) {
        ServiceRequestStatus status = serviceRequest.getStatus();

        if (status == ServiceRequestStatus.COMPLETED
                || status == ServiceRequestStatus.CANCELLED) {

            throw new InvalidServiceRequestStateException(
                    "Não é possível alterar uma solicitação finalizada."
            );
        }
    }

    private ServiceRequest findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServiceRequestNotFoundException(id));
    }

    private ServiceRequest toEntity(ServiceRequestRequest request) {
        ServiceRequest serviceRequest = new ServiceRequest();

        serviceRequest.setTitle(request.getTitle());
        serviceRequest.setDescription(request.getDescription());

        return serviceRequest;
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