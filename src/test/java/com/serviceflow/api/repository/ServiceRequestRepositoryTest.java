package com.serviceflow.api.repository;

import com.serviceflow.api.entity.ServiceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class ServiceRequestRepositoryTest {

    @Autowired
    private ServiceRequestRepository repository;

    @Test
    void shouldSaveServiceRequest() {
        ServiceRequest serviceRequest = new ServiceRequest();

        serviceRequest.setTitle("Teste de persistência");
        serviceRequest.setDescription("Teste para verificar a persistência no PostgreSQL.");

        ServiceRequest savedServiceRequest = repository.save(serviceRequest);

        assertNotNull(savedServiceRequest.getId());
    }
}
