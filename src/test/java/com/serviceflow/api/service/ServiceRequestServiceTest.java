package com.serviceflow.api.service;

import com.serviceflow.api.dto.ServiceRequestRequest;
import com.serviceflow.api.dto.ServiceRequestResponse;
import com.serviceflow.api.dto.ServiceRequestStatusRequest;
import com.serviceflow.api.entity.ServiceRequest;
import com.serviceflow.api.entity.ServiceRequestStatus;
import com.serviceflow.api.exception.ServiceRequestNotFoundException;
import com.serviceflow.api.repository.ServiceRequestRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ServiceRequestServiceTest {

    @Mock
    private ServiceRequestRepository repository;

    @InjectMocks
    private ServiceRequestService service;

    @Test
    void shouldCreateServiceRequest() {
        ServiceRequestRequest request = new ServiceRequestRequest();

        request.setTitle("Notebook não liga");
        request.setDescription("Equipamento não apresenta sinais de energia");

        ServiceRequest savedRequest = new ServiceRequest();

        savedRequest.setTitle(request.getTitle());
        savedRequest.setDescription(request.getDescription());

        when(repository.save(org.mockito.ArgumentMatchers.any(ServiceRequest.class)))
                .thenReturn(savedRequest);

        ServiceRequestResponse result = service.create(request);

        assertEquals("Notebook não liga", result.getTitle());

        assertEquals(
                "Equipamento não apresenta sinais de energia",
                result.getDescription()
        );

        verify(repository).save(
                org.mockito.ArgumentMatchers.any(ServiceRequest.class)
        );
    }

    @Test
    void shouldUpdateServiceRequest() {
        ServiceRequest existingRequest = new ServiceRequest();

        existingRequest.setTitle("Notebook não liga");
        existingRequest.setDescription("Equipamento não apresenta sinais de energia");

        ServiceRequestRequest updatedRequest = new ServiceRequestRequest();

        updatedRequest.setTitle("Notebook não liga - atualizado");
        updatedRequest.setDescription(
                "Equipamento continua sem apresentar sinais de energia"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        when(repository.save(existingRequest))
                .thenReturn(existingRequest);

        ServiceRequestResponse result = service.update(1L, updatedRequest);

        assertEquals(
                "Notebook não liga - atualizado",
                result.getTitle()
        );

        assertEquals(
                "Equipamento continua sem apresentar sinais de energia",
                result.getDescription()
        );

        verify(repository).findById(1L);
        verify(repository).save(existingRequest);
    }

    @Test
    void shouldUpdateServiceRequestStatus() {
        ServiceRequest existingRequest = new ServiceRequest();

        existingRequest.setTitle("Notebook não liga");
        existingRequest.setDescription("Equipamento não apresenta sinais de energia");
        existingRequest.setStatus(ServiceRequestStatus.PENDING);

        ServiceRequestStatusRequest statusRequest =
                new ServiceRequestStatusRequest();

        statusRequest.setStatus(ServiceRequestStatus.IN_PROGRESS);

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingRequest));

        when(repository.save(existingRequest))
                .thenReturn(existingRequest);

        ServiceRequestResponse result =
                service.updateStatus(1L, statusRequest);

        assertEquals(
                ServiceRequestStatus.IN_PROGRESS,
                result.getStatus()
        );

        verify(repository).findById(1L);
        verify(repository).save(existingRequest);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingStatusOfNonExistingServiceRequest() {
        ServiceRequestStatusRequest statusRequest =
                new ServiceRequestStatusRequest();

        statusRequest.setStatus(ServiceRequestStatus.IN_PROGRESS);

        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ServiceRequestNotFoundException.class,
                () -> service.updateStatus(999L, statusRequest)
        );

        verify(repository).findById(999L);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingServiceRequest() {
        ServiceRequestRequest request = new ServiceRequestRequest();

        request.setTitle("Solicitação inexistente");
        request.setDescription("Solicitação que não existe");

        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ServiceRequestNotFoundException.class,
                () -> service.update(999L, request)
        );

        verify(repository).findById(999L);
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
}