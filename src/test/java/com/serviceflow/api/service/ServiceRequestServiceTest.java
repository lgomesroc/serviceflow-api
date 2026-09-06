package com.serviceflow.api.service;

import com.serviceflow.api.dto.ServiceRequestRequest;
import com.serviceflow.api.dto.ServiceRequestResponse;
import com.serviceflow.api.dto.ServiceRequestStatusRequest;
import com.serviceflow.api.entity.ServiceRequest;
import com.serviceflow.api.entity.ServiceRequestStatus;
import com.serviceflow.api.exception.InvalidServiceRequestStateException;
import com.serviceflow.api.exception.ServiceRequestNotFoundException;
import com.serviceflow.api.repository.ServiceRequestRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceRequestServiceTest {

    @Mock
    private ServiceRequestRepository repository;

    private ServiceRequestService service;

    @BeforeEach
    void setUp() {
        service = new ServiceRequestService(repository);
    }

    @Test
    void shouldCreateServiceRequest() {
        ServiceRequestRequest request = new ServiceRequestRequest();

        request.setTitle("Notebook não liga");
        request.setDescription(
                "Equipamento não apresenta sinais de energia"
        );

        ServiceRequest savedRequest =
                createServiceRequest(ServiceRequestStatus.PENDING);

        when(repository.save(any(ServiceRequest.class)))
                .thenReturn(savedRequest);

        ServiceRequestResponse response =
                service.create(request);

        assertEquals(
                "Notebook não liga",
                response.getTitle()
        );

        assertEquals(
                "Equipamento não apresenta sinais de energia",
                response.getDescription()
        );

        assertEquals(
                ServiceRequestStatus.PENDING,
                response.getStatus()
        );

        verify(repository).save(any(ServiceRequest.class));
    }

    @Test
    void shouldUpdateServiceRequest() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.PENDING);

        ServiceRequestRequest updatedRequest =
                new ServiceRequestRequest();

        updatedRequest.setTitle("Notebook atualizado");
        updatedRequest.setDescription(
                "Descrição atualizada"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        when(repository.save(existingRequest))
                .thenReturn(existingRequest);

        ServiceRequestResponse response =
                service.update(1L, updatedRequest);

        assertEquals(
                "Notebook atualizado",
                response.getTitle()
        );

        assertEquals(
                "Descrição atualizada",
                response.getDescription()
        );

        verify(repository).findById(1L);
        verify(repository).save(existingRequest);
    }

    @Test
    void shouldUpdateServiceRequestStatus() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.PENDING);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.IN_PROGRESS);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        when(repository.save(existingRequest))
                .thenReturn(existingRequest);

        ServiceRequestResponse response =
                service.updateStatus(1L, statusRequest);

        assertEquals(
                ServiceRequestStatus.IN_PROGRESS,
                response.getStatus()
        );

        verify(repository).findById(1L);
        verify(repository).save(existingRequest);
    }

    @Test
    void shouldAllowTransitionFromPendingToCancelled() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.PENDING);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.CANCELLED);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        when(repository.save(existingRequest))
                .thenReturn(existingRequest);

        ServiceRequestResponse result =
                service.updateStatus(1L, statusRequest);

        assertEquals(
                ServiceRequestStatus.CANCELLED,
                result.getStatus()
        );

        verify(repository).save(existingRequest);
    }

    @Test
    void shouldAllowTransitionFromInProgressToCompleted() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.IN_PROGRESS);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.COMPLETED);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        when(repository.save(existingRequest))
                .thenReturn(existingRequest);

        ServiceRequestResponse result =
                service.updateStatus(1L, statusRequest);

        assertEquals(
                ServiceRequestStatus.COMPLETED,
                result.getStatus()
        );

        verify(repository).save(existingRequest);
    }

    @Test
    void shouldAllowTransitionFromInProgressToCancelled() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.IN_PROGRESS);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.CANCELLED);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        when(repository.save(existingRequest))
                .thenReturn(existingRequest);

        ServiceRequestResponse result =
                service.updateStatus(1L, statusRequest);

        assertEquals(
                ServiceRequestStatus.CANCELLED,
                result.getStatus()
        );

        verify(repository).save(existingRequest);
    }

    @Test
    void shouldRejectTransitionFromPendingToCompleted() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.PENDING);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.COMPLETED);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.updateStatus(1L, statusRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldRejectTransitionFromInProgressToPending() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.IN_PROGRESS);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.PENDING);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.updateStatus(1L, statusRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldRejectTransitionFromCompletedToPending() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.COMPLETED);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.PENDING);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.updateStatus(1L, statusRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldRejectTransitionFromCompletedToInProgress() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.COMPLETED);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.IN_PROGRESS);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.updateStatus(1L, statusRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldRejectTransitionFromCompletedToCancelled() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.COMPLETED);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.CANCELLED);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.updateStatus(1L, statusRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldRejectTransitionFromCancelledToPending() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.CANCELLED);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.PENDING);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.updateStatus(1L, statusRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldRejectTransitionFromCancelledToInProgress() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.CANCELLED);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.IN_PROGRESS);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.updateStatus(1L, statusRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldRejectTransitionFromCancelledToCompleted() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.CANCELLED);

        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.COMPLETED);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.updateStatus(1L, statusRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldRejectUpdateOfCompletedServiceRequest() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.COMPLETED);

        ServiceRequestRequest updatedRequest =
                new ServiceRequestRequest();

        updatedRequest.setTitle("Novo título");
        updatedRequest.setDescription("Nova descrição");

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.update(1L, updatedRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldRejectUpdateOfCancelledServiceRequest() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.CANCELLED);

        ServiceRequestRequest updatedRequest =
                new ServiceRequestRequest();

        updatedRequest.setTitle("Novo título");
        updatedRequest.setDescription("Nova descrição");

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        assertThrows(
                InvalidServiceRequestStateException.class,
                () -> service.update(1L, updatedRequest)
        );

        verify(repository).findById(1L);
        verify(repository, never()).save(existingRequest);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingStatusOfNonExistingServiceRequest() {
        ServiceRequestStatusRequest statusRequest =
                createStatusRequest(ServiceRequestStatus.IN_PROGRESS);

        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ServiceRequestNotFoundException.class,
                () -> service.updateStatus(999L, statusRequest)
        );

        verify(repository).findById(999L);
        verify(repository, never()).save(any(ServiceRequest.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingServiceRequest() {
        ServiceRequestRequest updatedRequest =
                new ServiceRequestRequest();

        updatedRequest.setTitle("Novo título");
        updatedRequest.setDescription("Nova descrição");

        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ServiceRequestNotFoundException.class,
                () -> service.update(999L, updatedRequest)
        );

        verify(repository).findById(999L);
        verify(repository, never()).save(any(ServiceRequest.class));
    }

    @Test
    void shouldThrowExceptionWhenFindingNonExistingServiceRequest() {
        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ServiceRequestNotFoundException.class,
                () -> service.findById(999L)
        );

        verify(repository).findById(999L);
    }

    @Test
    void shouldReturnPaginatedServiceRequests() {
        ServiceRequest firstRequest =
                createServiceRequest(ServiceRequestStatus.PENDING);

        firstRequest.setTitle("Primeiro chamado");
        firstRequest.setDescription("Primeira descrição");

        ServiceRequest secondRequest =
                createServiceRequest(ServiceRequestStatus.IN_PROGRESS);

        secondRequest.setTitle("Segundo chamado");
        secondRequest.setDescription("Segunda descrição");

        Pageable pageable = PageRequest.of(0, 2);

        Page<ServiceRequest> page = new PageImpl<>(
                List.of(firstRequest, secondRequest),
                pageable,
                2
        );

        when(repository.findAll(pageable))
                .thenReturn(page);

        Page<ServiceRequestResponse> result =
                service.findAll(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());

        assertEquals(
                "Primeiro chamado",
                result.getContent().get(0).getTitle()
        );

        assertEquals(
                "Primeira descrição",
                result.getContent().get(0).getDescription()
        );

        assertEquals(
                ServiceRequestStatus.PENDING,
                result.getContent().get(0).getStatus()
        );

        assertEquals(
                "Segundo chamado",
                result.getContent().get(1).getTitle()
        );

        assertEquals(
                "Segunda descrição",
                result.getContent().get(1).getDescription()
        );

        assertEquals(
                ServiceRequestStatus.IN_PROGRESS,
                result.getContent().get(1).getStatus()
        );

        verify(repository).findAll(pageable);
    }

    @Test
    void shouldKeepStatusWhenUpdatingServiceRequest() {
        ServiceRequest existingRequest =
                createServiceRequest(ServiceRequestStatus.PENDING);

        ServiceRequestRequest request =
                new ServiceRequestRequest();

        request.setTitle("Título atualizado");
        request.setDescription("Descrição atualizada");

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        when(repository.save(existingRequest))
                .thenReturn(existingRequest);

        ServiceRequestResponse response =
                service.update(1L, request);

        assertEquals(
                ServiceRequestStatus.PENDING,
                response.getStatus()
        );

        verify(repository).save(existingRequest);
    }

    private ServiceRequest createServiceRequest(
            ServiceRequestStatus status) {

        ServiceRequest serviceRequest = new ServiceRequest();

        serviceRequest.setTitle("Notebook não liga");
        serviceRequest.setDescription(
                "Equipamento não apresenta sinais de energia"
        );
        serviceRequest.setStatus(status);

        return serviceRequest;
    }

    private ServiceRequestStatusRequest createStatusRequest(
            ServiceRequestStatus status) {

        ServiceRequestStatusRequest request =
                new ServiceRequestStatusRequest();

        request.setStatus(status);

        return request;
    }
}