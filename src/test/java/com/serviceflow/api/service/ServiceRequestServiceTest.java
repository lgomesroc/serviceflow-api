package com.serviceflow.api.service;

import com.serviceflow.api.entity.ServiceRequest;
import com.serviceflow.api.exception.ServiceRequestNotFoundException;
import com.serviceflow.api.repository.ServiceRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceRequestServiceTest {

    @Mock
    private ServiceRequestRepository repository;

    @InjectMocks
    private ServiceRequestService service;

    @Test
    void shouldCreateServiceRequest() {
        ServiceRequest request = new ServiceRequest();

        when(repository.save(request)).thenReturn(request);

        ServiceRequest result = service.create(request);

        assertSame(request, result);
        verify(repository).save(request);
    }

    @Test
    void shouldUpdateServiceRequest() {
        ServiceRequest existingRequest = new ServiceRequest();
        existingRequest.setTitle("Notebook não liga");
        existingRequest.setDescription("Equipamento não apresenta sinais de energia");

        ServiceRequest updatedRequest = new ServiceRequest();
        updatedRequest.setTitle("Notebook não liga - atualizado");
        updatedRequest.setDescription("Equipamento continua sem apresentar sinais de energia");

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(existingRequest));
        when(repository.save(existingRequest)).thenReturn(existingRequest);

        ServiceRequest result = service.update(1L, updatedRequest);

        assertSame(existingRequest, result);
        assertEquals("Notebook não liga - atualizado", result.getTitle());
        assertEquals(
                "Equipamento continua sem apresentar sinais de energia",
                result.getDescription()
        );

        verify(repository).findById(1L);
        verify(repository).save(existingRequest);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingServiceRequest() {
        ServiceRequest request = new ServiceRequest();

        when(repository.findById(999L)).thenReturn(java.util.Optional.empty());

        assertThrows(
                ServiceRequestNotFoundException.class,
                () -> service.update(999L, request)
        );

        verify(repository).findById(999L);
    }

    @Test
    void shouldThrowExceptionWhenFindingNonExistingServiceRequest() {

        when(repository.findById(999L)).thenReturn(java.util.Optional.empty());

        assertThrows(
                ServiceRequestNotFoundException.class,
                () -> service.findById(999L)
        );

        verify(repository).findById(999L);
    }
}
