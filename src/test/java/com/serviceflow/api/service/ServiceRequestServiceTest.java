package com.serviceflow.api.service;

import com.serviceflow.api.entity.ServiceRequest;
import com.serviceflow.api.repository.ServiceRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
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
}


